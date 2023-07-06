package com.github.halalala222.sprintboothelloword.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;
import com.github.halalala222.sprintboothelloword.mapper.DiaryMapper;
import com.github.halalala222.sprintboothelloword.dao.DiaryDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class DiaryDaoImp extends ServiceImpl<DiaryMapper, Diary> implements DiaryDao {
    public List<DiaryDTO> getDiaries() {
        return this.baseMapper.getDiaries();
    }
}
