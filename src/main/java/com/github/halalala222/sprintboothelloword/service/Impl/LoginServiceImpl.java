package com.github.halalala222.sprintboothelloword.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.dao.UserDao;
import com.github.halalala222.sprintboothelloword.dto.LoginDTO;
import com.github.halalala222.sprintboothelloword.entity.User;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.service.LoginService;
import com.github.halalala222.sprintboothelloword.utils.BcryptUtils;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final UserDao userDao;

    @Autowired
    public LoginServiceImpl(UserDao userDao, JwtUtils jwtUtils) {
        this.userDao = userDao;
    }

    @Override
    public Long Login(LoginDTO loginDTO) throws BaseException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, loginDTO.getUserName());
        User user = userDao.getOne(queryWrapper);
        if (user == null) {
            throw new BaseException(ResponseCode.USER_NOT_FOUND_ERROR);
        }
        if (!BcryptUtils.check(user.getPassword(), user.getPassword())) {
            throw new BaseException(ResponseCode.USER_PASSWORD_ERROR);
        }
        return user.getId();
    }
}
