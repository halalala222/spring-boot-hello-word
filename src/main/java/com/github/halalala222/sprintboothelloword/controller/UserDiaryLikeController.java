package com.github.halalala222.sprintboothelloword.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.halalala222.sprintboothelloword.entity.UserDiaryLike;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.dao.UserDiaryLikeService;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        try {
            userDiaryLikeService.save(userDiaryLike);
        } catch (DuplicateKeyException e) {
            LambdaQueryWrapper<UserDiaryLike> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.
                    eq(UserDiaryLike::getUserId, userId).
                    eq(UserDiaryLike::getDiaryId, userLikeDiary.getDiaryId());
            UserDiaryLike data = userDiaryLikeService.getOne(queryWrapper);
            if (data != null) {
                userDiaryLikeService.remove(queryWrapper);
            } else {
                userDiaryLikeService.recoveryLogicDelete(userId, userLikeDiary.getDiaryId());
            }

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