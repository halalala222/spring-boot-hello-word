package com.github.halalala222.sprintboothelloword.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.entity.DiaryDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public interface DiaryService extends IService<Diary> {
    List<DiaryDTO> getDiaries();
}
