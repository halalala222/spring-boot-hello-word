package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.entity.UserDiaryLike;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.UserDiaryLikeService;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.halalala222.sprintboothelloword.constants.ResponseCode.SERVICE_ERROR;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@RestController
@RequestMapping("/user/diary")
public class UserDiaryLikeController {
    private final UserDiaryLikeService userDiaryLikeService;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserDiaryLikeController(UserDiaryLikeService userDiaryLikeService, JwtUtils jwtUtils) {
        this.userDiaryLikeService = userDiaryLikeService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("")
    public Response<Void> userLikeDiary(HttpServletRequest httpServletRequest,
                                        @RequestBody @Validated UserLikeDiary userLikeDiary)
            throws BaseException {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(httpServletRequest));
        UserDiaryLike userDiaryLike = UserDiaryLike.builder().
                diaryId(userLikeDiary.getDiaryId()).
                userId(userId).build();
        if (!userDiaryLikeService.save(userDiaryLike)) {
            throw new BaseException(SERVICE_ERROR);
        }
        return Response.successWithoutData();
    }
}

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class UserLikeDiary {
    private Long diaryId;
}