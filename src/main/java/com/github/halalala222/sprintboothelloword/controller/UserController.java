package com.github.halalala222.sprintboothelloword.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.halalala222.sprintboothelloword.entity.User;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.UserService;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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

    @Autowired
    public UserController(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }


    @GetMapping
    public Response<Map<String, UserProfile>> getUserProfile(HttpServletRequest request) {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(request));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(User.getIdFiled(), userId);
        User user = userService.getOne(queryWrapper);
        Map<String, UserProfile> responseData = new HashMap<>();
        responseData.put("profile", new UserProfile(user.getName(), user.getId(), user.getSignature(), user.getEmail()));
        return Response.successWithData(responseData);
    }
}

@AllArgsConstructor
class UserProfile {
    private String name;
    private Long id;
    private String signature;
    private String email;
}