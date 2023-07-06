package com.github.halalala222.sprintboothelloword.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.halalala222.sprintboothelloword.dao.UserDiaryLikeDao;
import com.github.halalala222.sprintboothelloword.dto.UserLikeDiaryDTO;
import com.github.halalala222.sprintboothelloword.entity.UserDiaryLike;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.service.UserLikeDiaryService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class UserLikeDiaryServiceImpl implements UserLikeDiaryService {
    private final UserDiaryLikeDao userDiaryLikeDao;

    public UserLikeDiaryServiceImpl(UserDiaryLikeDao userDiaryLikeDao) {
        this.userDiaryLikeDao = userDiaryLikeDao;
    }

    @Override
    public void UserLikeDiary(UserLikeDiaryDTO userLikeDiaryDTO) throws BaseException {
        UserDiaryLike userDiaryLike = UserDiaryLike.builder().
                diaryId(userLikeDiaryDTO.getDiaryId()).
                userId(userLikeDiaryDTO.getUserId())
                .build();
        try {
            userDiaryLikeDao.save(userDiaryLike);
        } catch (DuplicateKeyException e) {
            LambdaQueryWrapper<UserDiaryLike> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.
                    eq(UserDiaryLike::getUserId, userLikeDiaryDTO.getUserId()).
                    eq(UserDiaryLike::getDiaryId, userLikeDiaryDTO.getDiaryId());
            UserDiaryLike data = userDiaryLikeDao.getOne(queryWrapper);
            if (data != null) {
                userDiaryLikeDao.remove(queryWrapper);
            } else {
                userDiaryLikeDao.recoveryLogicDelete(
                        userLikeDiaryDTO.getUserId(),
                        userLikeDiaryDTO.getDiaryId()
                );
            }
        }
    }
}
