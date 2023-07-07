package com.github.halalala222.sprintboothelloword.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.halalala222.sprintboothelloword.constants.RedisConstants;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.dao.UserDao;
import com.github.halalala222.sprintboothelloword.dto.UserProfileDTO;
import com.github.halalala222.sprintboothelloword.entity.User;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.service.UserProfileService;
import com.github.halalala222.sprintboothelloword.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final RedisUtils redisUtils;
    private final UserDao userDao;

    @Autowired
    public UserProfileServiceImpl(RedisUtils redisUtils, UserDao userDao) {
        this.redisUtils = redisUtils;
        this.userDao = userDao;
    }

    @Override
    public UserProfileDTO getUserProfile(Long userId) throws BaseException {
        Object userRedisProfile = redisUtils.get(
                RedisConstants.getFullKey(
                        RedisConstants.USER_PROFILE_KEY_PREFIX,
                        userId.toString()
                )
        );
        UserProfileDTO userProfile;
        if (userRedisProfile != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            userProfile = objectMapper.convertValue(userRedisProfile, UserProfileDTO.class);
            return userProfile;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, userId);
        User user = userDao.getOne(queryWrapper);
        if (user == null) {
            throw new BaseException(ResponseCode.USER_NOT_FOUND_ERROR);
        }
        userProfile = new UserProfileDTO(user.getName(), user.getId(), user.getSignature(), user.getEmail());
        redisUtils.set(
                RedisConstants.getFullKey(RedisConstants.USER_PROFILE_KEY_PREFIX, userId.toString()),
                userProfile,
                RedisConstants.USER_PROFILE_TTL
        );
        return userProfile;
    }

    @Override
    public void updateUserProfile(UserProfileDTO userProfileDTO) throws BaseException {
        LambdaUpdateWrapper<User> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.eq(User::getId, userProfileDTO.getId()).
                set(User::getName, userProfileDTO.getName()).
                set(User::getSignature, userProfileDTO.getSignature());
        boolean isUpdated = userDao.update(userUpdateWrapper);
        if (!isUpdated) {
            throw new BaseException(ResponseCode.SERVICE_ERROR);
        }
        redisUtils.delete(
                RedisConstants.getFullKey(
                        RedisConstants.USER_PROFILE_KEY_PREFIX,
                        userProfileDTO.getId().toString()
                )
        );
    }
}
