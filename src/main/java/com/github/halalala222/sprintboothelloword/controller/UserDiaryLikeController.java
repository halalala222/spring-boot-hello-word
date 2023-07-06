package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.dto.UserLikeDiaryDTO;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.UserLikeDiaryService;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@RestController
@RequestMapping("/user/diary")
public class UserDiaryLikeController {
    private final UserLikeDiaryService userLikeDiaryService;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserDiaryLikeController(UserLikeDiaryService userLikeDiaryService, JwtUtils jwtUtils) {
        this.userLikeDiaryService = userLikeDiaryService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("")
    public Response<Void> userLikeDiary(HttpServletRequest httpServletRequest,
                                        @RequestBody @Validated UserLikeDiary userLikeDiary)
            throws BaseException {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(httpServletRequest));
        userLikeDiaryService.UserLikeDiary(
                UserLikeDiaryDTO.
                        builder().diaryId(userLikeDiary.getDiaryId()).
                        userId(userId)
                        .build()
        );

        return Response.successWithoutData();
    }
}

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class UserLikeDiary {
    @NotNull(message = "diaryId 不能为空")
    private Long diaryId;
}