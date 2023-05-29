package com.github.halalala222.sprintboothelloword.handler;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Getter
public enum ResponseHttpStatusCode {

    STATUS_OK(200, "success"),
    STATUS_AUTH_ERROR(401, "auth error"),
    STATUS_FORBIDDEN_ERROR(403, "forbidden"),
    STATUS_REQUEST_ERROR(400, "request error"),
    STATUS_INTERNAL_ERROR(500, "internal error");

    private final int httpStatusCode;

    private final String message;

    ResponseHttpStatusCode(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

}
