package com.github.halalala222.sprintboothelloword.handler;

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
    JWT_ERROR(1001, "token error");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
