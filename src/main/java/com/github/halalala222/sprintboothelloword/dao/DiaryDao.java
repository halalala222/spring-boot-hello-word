package com.github.halalala222.sprintboothelloword.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public interface DiaryDao extends IService<Diary> {
    List<DiaryDTO> getDiaries();

    Long getDiaryCount(Long diaryId);
}
