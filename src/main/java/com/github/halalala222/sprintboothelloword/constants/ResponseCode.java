package com.github.halalala222.sprintboothelloword.constants;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Getter
public enum ResponseCode {
    OK(1, "ok"),
    SERVICE_ERROR(2, "internal error"),
    JWT_ERROR(1001, "token error"),
    USER_NOT_FOUND_ERROR(1002, "user not found"),
    USER_UN_AUTHORIZATION(1003, "auth error"),
    REQUEST_DATA_ERROR(1004, "请求参数错误"),
    USER_EXITED(1005, "用户名已经注册过"),
    USER_PASSWORD_ERROR(1006, "用户账号或密码错误"),
    REQUEST_PARAM_ERROR(1007, "请求参数有误"),
    PASSWORD_STRENGTH_ERROR(1008, "密码强度不够");
    private final int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseCode getResponseCodeSetMessage(String message) {
        this.message = message;
        return this;
    }
}
