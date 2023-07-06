package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.dto.LoginDTO;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.LoginService;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
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
    private final LoginService loginService;

    public Login(JwtUtils jwtUtils, LoginService loginService) {
        this.jwtUtils = jwtUtils;
        this.loginService = loginService;
    }

    @PostMapping
    public Response<Map<String, String>> LoginController(@RequestBody @Validated LoginUser loginUser) throws BaseException {
        Long userId = loginService.Login(new LoginDTO(loginUser.getUserName(), loginUser.getPassword()));
        HashMap<String, String> tokenResponse = new HashMap<>();
        String token = jwtUtils.generateToken(userId);
        tokenResponse.put("token", token);
        return Response.successWithData(tokenResponse);
    }
}

@Data
class LoginUser {
    @NotBlank(message = "username 不能为空")
    private String userName;
    @NotEmpty(message = "password 不能为空")
    private String password;
}