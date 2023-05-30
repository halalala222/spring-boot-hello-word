package com.github.halalala222.sprintboothelloword.handler;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> error(Exception e) {
        log.error("[run time error : ]", e);
        return Response.serviceError();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> error(NullPointerException e) {
        log.error("[null pointer error : ]", e);
        return Response.serviceError();
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Response<Void>> error(BaseException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseCode code = e.getCode();
        if (code.getCode() == ResponseCode.JWT_ERROR.getCode()) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        if (code.getCode() == ResponseCode.SERVICE_ERROR.getCode()) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.error("[internal service error : ]", e);
        }
        return ResponseEntity.status(httpStatus).body(Response.errorWithCode(code));
    }
}
