package com.github.halalala222.sprintboothelloword.handler;

import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import lombok.Getter;
import lombok.Setter;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Getter
@Setter
public class Response<T> {
    private int code;
    private String message;
    private T data;

    private Response() {
    }

    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> of(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    public static <T> Response<T> successWithoutData() {
        return Response.of(ResponseCode.OK.getCode(), ResponseCode.OK.getMessage(), null);
    }


    public static <T> Response<T> successWithData(T data) {
        return Response.of(ResponseCode.OK.getCode(), ResponseCode.OK.getMessage(), data);
    }

    public static <T> Response<T> serviceError() {
        return Response.of(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMessage(), null);
    }

    public static <T> Response<T> errorWithCode(ResponseCode code) {
        return Response.of(code.getCode(), code.getMessage(), null);
    }
}
