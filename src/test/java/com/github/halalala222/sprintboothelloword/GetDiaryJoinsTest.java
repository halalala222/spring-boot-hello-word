package com.github.halalala222.sprintboothelloword;

import com.github.halalala222.sprintboothelloword.entity.DiaryDTO;
import com.github.halalala222.sprintboothelloword.mapper.DiaryMapper;
import com.github.halalala222.sprintboothelloword.service.DiaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@SpringBootTest
public class GetDiaryJoinsTest {
    @Autowired
    private DiaryMapper diaryMapper;
    @Autowired
    private DiaryService diaryService;

    @Test
    void TestGetDiaries() {
        List<DiaryDTO> diaryDTOList = diaryMapper.getDiaries();
        diaryDTOList.forEach(System.out::println);
    }

    @Test
    void TestService() {
        List<DiaryDTO> diaryDTOList = diaryService.getDiaries();
        diaryDTOList.forEach(System.out::println);
    }
}
