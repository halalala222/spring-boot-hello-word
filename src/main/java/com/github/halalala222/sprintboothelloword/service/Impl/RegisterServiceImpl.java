package com.github.halalala222.sprintboothelloword.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.dao.UserDao;
import com.github.halalala222.sprintboothelloword.dto.RegisterDTO;
import com.github.halalala222.sprintboothelloword.entity.User;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.service.RegisterService;
import com.github.halalala222.sprintboothelloword.utils.BcryptUtils;
import com.github.halalala222.sprintboothelloword.utils.CheckPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserDao userDao;

    @Autowired
    public RegisterServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Long register(RegisterDTO registerDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, registerDTO.getUsername());
        User user = userDao.getOne(queryWrapper);
        if (user != null) {
            throw new BaseException(ResponseCode.USER_EXITED);
        }
        if (!CheckPassword.check(registerDTO.getPassword())) {
            throw new BaseException(ResponseCode.PASSWORD_STRENGTH_ERROR);
        }
        User newUser = User.builder().
                name(registerDTO.getUsername()).
                password(BcryptUtils.encoded(registerDTO.getPassword())).build();
        boolean isSave = userDao.save(newUser);
        if (!isSave) {
            throw new BaseException(ResponseCode.SERVICE_ERROR);
        }
        return newUser.getId();

    }
}
