package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.dto.DiaryDTO;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.DiaryService;
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

    private final DiaryService diaryService;
    private final JwtUtils jwtUtils;

    @Autowired
    public DiaryController(DiaryService diaryService, JwtUtils jwtUtils) {
        this.diaryService = diaryService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/diary")
    public Response<Void> createDiary(@RequestBody @Validated CreateDiary createDiary, HttpServletRequest request) throws BaseException {
        Long userId = jwtUtils.getUserIdFromToken(jwtUtils.getTokenFromRequestHeader(request));
        Diary diary = Diary.builder().content(createDiary.getContent()).UserId(userId).build();
        if (!diaryService.save(diary)) {
            throw new BaseException(ResponseCode.SERVICE_ERROR);
        }
        return Response.successWithoutData();
    }

    @GetMapping("/diaries")
    public Response<Map<String, List<DiaryDTO>>> getAllDiaries() throws BaseException {
        List<DiaryDTO> diaries = diaryService.getDiaries();
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
