package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.dao.UserDao;
import com.github.halalala222.sprintboothelloword.dto.UserProfileDTO;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.UserProfileService;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import com.github.halalala222.sprintboothelloword.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {
    private final JwtUtils jwtUtils;
    private final UserProfileService userProfileService;

    @Autowired
    public UserController(JwtUtils jwtUtils, UserDao userDao, RedisUtils redisUtils, UserProfileService userProfileService) {
        this.jwtUtils = jwtUtils;
        this.userProfileService = userProfileService;
    }


    @GetMapping("/profile")
    public Response<Map<String, UserProfileDTO>> getUserProfile(HttpServletRequest request) throws BaseException {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(request));
        UserProfileDTO userProfileDTO = userProfileService.getUserProfile(userId);
        Map<String, UserProfileDTO> responseData = new HashMap<>();
        responseData.put("profile", userProfileDTO);
        return Response.successWithData(responseData);
    }

    @PutMapping("/profile")
    public Response<Void> updateUserProfile(@RequestBody @Validated UpdateUserProfile updateUserProfile, HttpServletRequest request) throws BaseException {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(request));
        userProfileService.updateUserProfile(UserProfileDTO.builder().
                id(userId).
                signature(updateUserProfile.getSignature()).
                name(updateUserProfile.getName()).
                build());
        return Response.successWithoutData();
    }
}

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
class UpdateUserProfile {
    @NotEmpty(message = "签名不能为空")
    private String signature;
    @NotEmpty(message = "姓名不能为空")
    private String name;
}