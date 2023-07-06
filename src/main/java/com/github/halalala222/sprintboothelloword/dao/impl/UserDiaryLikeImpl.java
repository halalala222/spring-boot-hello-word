package com.github.halalala222.sprintboothelloword.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.halalala222.sprintboothelloword.entity.UserDiaryLike;
import com.github.halalala222.sprintboothelloword.mapper.UserDiaryLikeMapper;
import com.github.halalala222.sprintboothelloword.dao.UserDiaryLikeService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class UserDiaryLikeImpl extends ServiceImpl<UserDiaryLikeMapper, UserDiaryLike> implements UserDiaryLikeService {

    public void recoveryLogicDelete(Long userId, Long diaryId) {
        this.baseMapper.recoverLogicDelete(userId, diaryId);
    }
}
