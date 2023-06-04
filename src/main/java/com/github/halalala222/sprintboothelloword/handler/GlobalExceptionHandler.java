package com.github.halalala222.sprintboothelloword.handler;

import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import com.github.halalala222.sprintboothelloword.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public <T> Response<T> error(Exception e) {
        log.error("[run time error : ]", e);
        return Response.serviceError();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> Response<T> error(NullPointerException e) {
        log.error("[null pointer error : ]", e);
        return Response.serviceError();
    }

    @ExceptionHandler(BaseException.class)
    public <T> ResponseEntity<Response<T>> error(BaseException e) {
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public <T> Response<T> info(HttpRequestMethodNotSupportedException e) {
        log.info("[http method not found error : ]", e);
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> Response<T> info(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrorList = e.getFieldErrors();
        List<String> collect = fieldErrorList.stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage
        ).toList();
        String unValidMessage = collect.toString();
        return Response.errorWithCode(ResponseCode.REQUEST_PARAM_ERROR.getResponseCodeSetMessage(unValidMessage.substring(1, unValidMessage.length() - 1)));
    }
}
