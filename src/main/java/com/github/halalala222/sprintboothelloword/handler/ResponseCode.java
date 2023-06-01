package com.github.halalala222.sprintboothelloword.handler;

import lombok.Getter;
import lombok.Setter;

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
    USER_UN_AUTHORIZATION(1003,"auth error");
    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
