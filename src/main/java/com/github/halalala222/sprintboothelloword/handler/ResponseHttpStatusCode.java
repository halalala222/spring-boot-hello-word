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

    STATUS_OK(200),
    STATUS_AUTH_ERROR(401),
    STATUS_FORBIDDEN_ERROR(403),
    STATUS_BAD_REQUEST_ERROR(400),
    STATUS_INTERNAL_ERROR(500);

    private final int httpStatusCode;


    ResponseHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

}
