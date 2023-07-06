package com.github.halalala222.sprintboothelloword.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.halalala222.sprintboothelloword.dao.UserDao;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.utils.BcryptUtils;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import com.github.halalala222.sprintboothelloword.entity.User;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@RestController
@RequestMapping("/login")
public class Login {
    private final JwtUtils jwtUtils;
    private final UserDao userDao;

    public Login(JwtUtils jwtUtils, UserDao userDao) {
        this.jwtUtils = jwtUtils;
        this.userDao = userDao;
    }

    @PostMapping
    public Response<Map<String, String>> LoginController(@RequestBody LoginUser loginUser) throws BaseException {
        if (loginUser.getUserName() == null || loginUser.getPassword() == null) {
            throw new BaseException(ResponseCode.REQUEST_DATA_ERROR);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, loginUser.getUserName());
        User user = userDao.getOne(queryWrapper);
        if (user == null) {
            throw new BaseException(ResponseCode.USER_NOT_FOUND_ERROR);
        }
        if (!BcryptUtils.check(user.getPassword(), user.getPassword())) {
            throw new BaseException(ResponseCode.USER_PASSWORD_ERROR);
        }
        String token = jwtUtils.generateToken(user.getId());
        HashMap<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("token", token);
        return Response.successWithData(tokenResponse);
    }
}

@Data
class LoginUser {
    private String userName;
    private String password;
}