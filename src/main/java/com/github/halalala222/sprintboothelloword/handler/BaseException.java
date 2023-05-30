package com.github.halalala222.sprintboothelloword.handler;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Getter
public class BaseException extends RuntimeException {
    private final ResponseCode code;

    public BaseException(ResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

    @Override
    public String toString() {
        return "Exception{" + "code =" + this.code + ",message = " + this.getMessage() + '}';
    }
}
