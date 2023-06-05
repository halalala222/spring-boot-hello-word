package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.UserService;
import com.github.halalala222.sprintboothelloword.utils.BcryptUtils;
import com.github.halalala222.sprintboothelloword.utils.CheckPassword;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/register")
public class Register {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    public Register(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public Response<Map<String, String>> userRegister(@Validated @RequestBody RegisterBody registerBody) throws BaseException {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(User.getNameFiled(), registerBody.getUsername());
        User user = userService.getOne(userQueryWrapper);
        if (user == null) {
            if (!CheckPassword.check(registerBody.getPassword())) {
                throw new BaseException(ResponseCode.PASSWORD_STRENGTH_ERROR);
            }
            User newUser = User.builder().
                    name(registerBody.getUsername()).
                    password(BcryptUtils.encoded(registerBody.getPassword())).build();
            boolean isSave = userService.save(newUser);
            if (!isSave) {
                throw new BaseException(ResponseCode.SERVICE_ERROR);
            }
            String token = jwtUtils.generateToken(newUser.getId());
            HashMap<String, String> tokenResponse = new HashMap<>();
            tokenResponse.put("token", token);
            return Response.successWithData(tokenResponse);
        }

        throw new BaseException(ResponseCode.USER_EXITED);
    }
}

@Data
class RegisterBody {
    @NotEmpty(message = "username 不能为空")
    private String username;
    @NotEmpty(message = "password 不能为空")
    private String password;
}
