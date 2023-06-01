package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.handler.BaseException;
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
    public Response<String> LoginController(@RequestBody User user) throws BaseException {
        if (user.getUserName() == null || user.getPassword() == null) {
            throw new BaseException(ResponseCode.REQUEST_DATA_ERROR);
        }
        if (!(user.getUserName().equals("liooooo") && user.getPassword().equals("admin"))) {
            throw new BaseException(ResponseCode.USER_NOT_FOUND_ERROR);
        }
        return Response.successWithoutData();
    }
}
