package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.handler.ResponseCode;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@RestController
@RequestMapping("/login")
public class Login {
    @PostMapping
    public Response<String> LoginController(@RequestBody User user) {
        if (!(user.getUserName().equals("liooooo") && user.getPassword().equals("admin"))) {
            return Response.errorWithCode(ResponseCode.USER_UN_AUTHORIZATION);
        }
        return Response.successWithoutData();
    }
}
