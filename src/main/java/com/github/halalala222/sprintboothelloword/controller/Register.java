package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.dto.RegisterDTO;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.RegisterService;
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
    private final RegisterService registerService;
    private final JwtUtils jwtUtils;

    public Register(RegisterService registerService, JwtUtils jwtUtils) {
        this.registerService = registerService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public Response<Map<String, String>> userRegister(@Validated @RequestBody RegisterBody registerBody) throws BaseException {
        Long userId = registerService.register(RegisterDTO.builder().
                username(registerBody.getUsername()).
                password(registerBody.getPassword()).build());
        String token = jwtUtils.generateToken(userId);
        HashMap<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("token", token);
        return Response.successWithData(tokenResponse);

    }
}

@Data
class RegisterBody {
    @NotEmpty(message = "username 不能为空")
    private String username;
    @NotEmpty(message = "password 不能为空")
    private String password;
}
