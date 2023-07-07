package com.github.halalala222.sprintboothelloword.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("diary")
public class Diary {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableId(type = IdType.AUTO)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField("content")
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField("user_id")
    private Long userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(fill = FieldFill.INSERT, value = "created_at")
    private Date createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "updated_at")
    private Date updatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableLogic
    private Date deletedAt;
}
