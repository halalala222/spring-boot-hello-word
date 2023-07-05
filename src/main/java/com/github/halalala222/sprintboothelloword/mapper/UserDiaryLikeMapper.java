package com.github.halalala222.sprintboothelloword.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.halalala222.sprintboothelloword.entity.UserDiaryLike;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Component
public interface UserDiaryLikeMapper extends BaseMapper<UserDiaryLike> {
    @Update("UPDATE user_diary_like SET deleted_at = NULL WHERE user_id = #{user_id} AND diary_id = #{diary_id}")
    void recoverLogicDelete(@Param("user_id") Long userId, @Param("diary_id") Long diaryId);
}
