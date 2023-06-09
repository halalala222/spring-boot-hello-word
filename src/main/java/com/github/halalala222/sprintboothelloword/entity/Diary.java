package com.github.halalala222.sprintboothelloword.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("content")
    private String content;
    @TableField("user_id")
    private Long UserId;
    @TableField(fill = FieldFill.INSERT, value = "created_at")
    private Date createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "updated_at")
    private Date updatedAt;
    private Date deletedAt;
}
