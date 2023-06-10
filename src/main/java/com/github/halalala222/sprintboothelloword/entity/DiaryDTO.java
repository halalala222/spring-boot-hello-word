package com.github.halalala222.sprintboothelloword.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Data
public class DiaryDTO {
    private Long id;
    private String content;
    private String UserName;
}
