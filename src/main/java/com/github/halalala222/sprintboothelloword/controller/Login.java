package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
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
    JwtUtils jwtUtils;

    public Login(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public Response<Map<String, String>> LoginController(@RequestBody LoginUser user) throws BaseException {
        if (user.getUserName() == null || user.getPassword() == null) {
            throw new BaseException(ResponseCode.REQUEST_DATA_ERROR);
        }
        if (!(user.getUserName().equals("liooooo") && user.getPassword().equals("admin"))) {
            throw new BaseException(ResponseCode.USER_NOT_FOUND_ERROR);
        }
        String token = jwtUtils.generateToken(1L);
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