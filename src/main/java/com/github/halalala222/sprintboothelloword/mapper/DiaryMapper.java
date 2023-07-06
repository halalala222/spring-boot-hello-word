package com.github.halalala222.sprintboothelloword.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Component
public interface DiaryMapper extends BaseMapper<Diary> {
    @Select("SELECT diary.content,diary.id,user.name AS user_name FROM diary LEFT JOIN user ON diary.user_id = user.id")
    List<DiaryDTO> getDiaries();

    @Select("SELECT COUNT(id) FROM user_diary_like WHERE diary_id = #{diary_id}")
    Long getDiaryCount(@Param("diary_id") Long diaryId);
}
