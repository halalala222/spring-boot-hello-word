package com.github.halalala222.sprintboothelloword.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> error(Exception e) {
        e.printStackTrace();
        return Response.serviceError();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> error(NullPointerException e) {
        e.printStackTrace();
        return Response.serviceError();
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Response<Void>> error(BaseException e) {
        e.printStackTrace();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseCode code = e.getCode();
        if (code.getCode() == ResponseCode.JWT_ERROR.getCode()) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        if (code.getCode() == ResponseCode.SERVICE_ERROR.getCode()) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(Response.errorWithCode(code));
    }
}
