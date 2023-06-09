package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.entity.Diary;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.handler.Response;
import com.github.halalala222.sprintboothelloword.service.DiaryService;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public Response<Void> createDiary(@RequestBody @Validated CreateDiary createDiary) throws BaseException {
        Diary diary = Diary.builder().content(createDiary.getContent()).build();
        if (diaryService.save(diary)) {
            throw new BaseException(ResponseCode.SERVICE_ERROR);
        }
        return Response.successWithoutData();
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
