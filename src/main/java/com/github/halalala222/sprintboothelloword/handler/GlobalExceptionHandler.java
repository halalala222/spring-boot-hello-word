package com.github.halalala222.sprintboothelloword.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Response<Void> error(Exception e) {
        e.printStackTrace();
        return Response.serviceError();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Response<Void> error(NullPointerException e) {
        e.printStackTrace();
        return Response.serviceError();
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Response<Void> error(BaseException e) {
        e.printStackTrace();
        return Response.errorWithCode(e.getCode());
    }
}
