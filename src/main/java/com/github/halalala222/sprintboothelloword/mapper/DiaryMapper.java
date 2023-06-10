package com.github.halalala222.sprintboothelloword.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.entity.DiaryDTO;
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
}
