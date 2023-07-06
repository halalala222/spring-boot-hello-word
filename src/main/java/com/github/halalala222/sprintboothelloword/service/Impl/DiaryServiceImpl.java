package com.github.halalala222.sprintboothelloword.service.Impl;

import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.dao.DiaryDao;
import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryDao diaryDao;

    @Autowired
    public DiaryServiceImpl(DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
    }

    @Override
    public void createDiary(Diary diary) throws BaseException {
        if (!diaryDao.save(diary)) {
            throw new BaseException(ResponseCode.SERVICE_ERROR);
        }
    }

    @Override
    public List<DiaryDTO> getAllDiaries() {
        return diaryDao.getDiaries();
    }
}
