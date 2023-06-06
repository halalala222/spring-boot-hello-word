package com.github.halalala222.sprintboothelloword.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.halalala222.sprintboothelloword.constants.RedisConstants;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.entity.User;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.UserService;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import com.github.halalala222.sprintboothelloword.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final RedisUtils redisUtils;

    @Autowired
    public UserController(JwtUtils jwtUtils, UserService userService, RedisUtils redisUtils) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.redisUtils = redisUtils;
    }


    @GetMapping("/profile")
    public Response<Map<String, UserProfile>> getUserProfile(HttpServletRequest request) throws BaseException {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(request));
        Object userRedisProfile = redisUtils.get(RedisConstants.getFullKey(RedisConstants.USER_PROFILE_KEY_PREFIX, userId.toString()));
        Map<String, UserProfile> responseData = new HashMap<>();

        if (userRedisProfile == null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(User.getIdFiled(), userId);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                throw new BaseException(ResponseCode.USER_NOT_FOUND_ERROR);
            }
            UserProfile userProfile = new UserProfile(user.getName(), user.getId(), user.getSignature(), user.getEmail());
            redisUtils.set(RedisConstants.getFullKey(RedisConstants.USER_PROFILE_KEY_PREFIX, userId.toString()), userProfile, RedisConstants.USER_PROFILE_TTL);
            responseData.put("profile", userProfile);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            UserProfile userProfile = objectMapper.convertValue(userRedisProfile, UserProfile.class);
            responseData.put("profile", userProfile);
        }

        return Response.successWithData(responseData);
    }
}

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
class UserProfile {
    private String name;
    private Long id;
    private String signature;
    private String email;
}