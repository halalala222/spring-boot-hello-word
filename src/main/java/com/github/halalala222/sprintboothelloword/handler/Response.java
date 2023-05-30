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
@Setter
public class Response<T> {
    private int code;
    private String message;
    private T data;

    private int httpStatusCode;

    private Response() {
    }

    private Response(int httpStatusCode, int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.httpStatusCode = httpStatusCode;
    }

    public static <T> Response<T> of(int httpStatusCode, int code, String message, T data) {
        return new Response<>(httpStatusCode, code, message, data);
    }

    public static Response<Void> successWithoutData() {
        return Response.of(ResponseHttpStatusCode.STATUS_OK.getHttpStatusCode(), ResponseCode.OK.getCode(), ResponseCode.OK.getMessage(), null);
    }


    public static <T> Response<T> successWithData(T data) {
        return Response.of(ResponseHttpStatusCode.STATUS_OK.getHttpStatusCode(), ResponseCode.OK.getCode(), ResponseCode.OK.getMessage(), data);
    }

    public static Response<Void> serviceError() {
        return Response.of(ResponseHttpStatusCode.STATUS_INTERNAL_ERROR.getHttpStatusCode(), ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMessage(), null);
    }

    public static Response<Void> errorWithCode(ResponseCode code) {
        Response<Void> errorResponse = new Response<>();
        if (code.getCode() == ResponseCode.JWT_ERROR.getCode()) {
            errorResponse.setHttpStatusCode(ResponseHttpStatusCode.STATUS_AUTH_ERROR.getHttpStatusCode());
        } else if (code.getCode() == ResponseCode.SERVICE_ERROR.getCode()) {
            errorResponse.setHttpStatusCode(ResponseHttpStatusCode.STATUS_INTERNAL_ERROR.getHttpStatusCode());
        } else {
            errorResponse.setHttpStatusCode(ResponseHttpStatusCode.STATUS_BAD_REQUEST_ERROR.getHttpStatusCode());
        }
        errorResponse.setCode(code.getCode());
        errorResponse.setMessage(code.getMessage());
        errorResponse.setData(null);
        return errorResponse;
    }
}
