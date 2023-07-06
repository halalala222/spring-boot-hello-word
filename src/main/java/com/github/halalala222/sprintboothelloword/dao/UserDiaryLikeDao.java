package com.github.halalala222.sprintboothelloword.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.halalala222.sprintboothelloword.entity.UserDiaryLike;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public interface UserDiaryLikeDao extends IService<UserDiaryLike> {
    void recoveryLogicDelete(Long userId, Long diaryId);
}
