package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.dao.DiaryDao;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@RestController
@RequestMapping("")
public class DiaryController {

    private final DiaryDao diaryDao;
    private final JwtUtils jwtUtils;

    @Autowired
    public DiaryController(DiaryDao diaryDao, JwtUtils jwtUtils) {
        this.diaryDao = diaryDao;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/diary")
    public Response<Void> createDiary(@RequestBody @Validated CreateDiary createDiary, HttpServletRequest request) throws BaseException {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(request));
        Diary diary = Diary.builder().content(createDiary.getContent()).UserId(userId).build();
        if (!diaryDao.save(diary)) {
            throw new BaseException(ResponseCode.SERVICE_ERROR);
        }
        return Response.successWithoutData();
    }

    @GetMapping("/diaries")
    public Response<Map<String, List<DiaryDTO>>> getAllDiaries() throws BaseException {
        List<DiaryDTO> diaries = diaryDao.getDiaries();
        Map<String, List<DiaryDTO>> responseData = new HashMap<>();
        responseData.put("diaries", diaries);
        return Response.successWithData(responseData);
    }
}

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
class CreateDiary {
    @NotEmpty(message = "content 不能为空")
    private String content;
}
