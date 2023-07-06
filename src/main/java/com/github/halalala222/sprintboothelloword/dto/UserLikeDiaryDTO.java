package com.github.halalala222.sprintboothelloword.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLikeDiaryDTO {
    private Long diaryId;
    private Long userId;
}
