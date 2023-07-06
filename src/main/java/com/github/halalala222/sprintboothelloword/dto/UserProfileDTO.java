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
@Builder
@NoArgsConstructor
public class UserProfileDTO {
    private String name;
    private Long id;
    private String signature;
    private String email;
}
