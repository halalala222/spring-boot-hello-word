package com.github.halalala222.sprintboothelloword.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user_diary_like")
public class UserDiaryLike {
    @TableField("user_id")
    Long userId;
    @TableField("dairy_id")
    Long diaryId;
    @TableField(fill = FieldFill.INSERT, value = "created_at")
    private Date createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "updated_at")
    private Date updatedAt;
    private Date deletedAt;
}
