package com.github.halalala222.sprintboothelloword.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.entity.DiaryDTO;
import com.github.halalala222.sprintboothelloword.mapper.DiaryMapper;
import com.github.halalala222.sprintboothelloword.service.DiaryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class DiaryServiceImp extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {
    public List<DiaryDTO> getDiaries() {
        return this.baseMapper.getDiaries();
    }
}
