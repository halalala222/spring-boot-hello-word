package com.github.halalala222.sprintboothelloword.service;

import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;
import com.github.halalala222.sprintboothelloword.entity.Diary;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public interface DiaryService {
    void createDiary(Diary diaryDTO);

    List<DiaryDTO> getAllDiaries();
}
