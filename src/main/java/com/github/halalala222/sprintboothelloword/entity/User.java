package com.github.halalala222.sprintboothelloword.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @TableField("signature")
    private String signature;
    @TableField("email")
    private String email;
    @TableField("password")
    private String password;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static String getIdFiled() {
        return "id";
    }

    public static String getNameFiled() {
        return "name";
    }

    public static String getSignatureFiled() {
        return "signature";
    }

    public static String getEmailFiled() {
        return "email";
    }

    public static String getPasswordFiled() {
        return "password";
    }
}
