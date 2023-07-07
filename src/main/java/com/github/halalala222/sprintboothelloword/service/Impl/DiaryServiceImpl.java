package com.github.halalala222.sprintboothelloword.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.halalala222.sprintboothelloword.constants.RedisConstants;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.dao.DiaryDao;
import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.service.DiaryService;
import com.github.halalala222.sprintboothelloword.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryDao diaryDao;

    private final RedisUtils redisUtils;

    @Autowired
    public DiaryServiceImpl(DiaryDao diaryDao, RedisUtils redisUtils) {
        this.diaryDao = diaryDao;
        this.redisUtils = redisUtils;
    }

    @Override
    public void createDiary(Diary diary) throws BaseException {
        if (!diaryDao.save(diary)) {
            throw new BaseException(ResponseCode.SERVICE_ERROR);
        }
        redisUtils.delete(
                RedisConstants.getFullKey(
                        RedisConstants.DIARIES_KEY_PREFIX,
                        null
                ));
    }

    @Override
    public List<DiaryDTO> getAllDiaries() {
        Object diaries = redisUtils.get(
                RedisConstants.getFullKey(
                        RedisConstants.DIARIES_KEY_PREFIX, null
                )
        );
        List<DiaryDTO> diaryDTOS;
        if (diaries != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<DiaryDTO>> typeReference = new TypeReference<>() {
            };
            diaryDTOS = objectMapper.convertValue(diaries, typeReference);
        } else {
            diaryDTOS = diaryDao.getDiaries();

            redisUtils.set(
                    RedisConstants.getFullKey(
                            RedisConstants.DIARIES_KEY_PREFIX,
                            null
                    ),
                    diaryDTOS,
                    RedisConstants.DIARIES_TTL
            );
        }

        return diaryDTOS.stream().peek((diaryDTO) -> {
            Long likeCount = diaryDao.getDiaryCount(diaryDTO.getId());
            diaryDTO.setCount(likeCount);
        }).collect(Collectors.toList());

    }
}
