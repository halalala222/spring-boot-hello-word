package com.github.halalala222.sprintboothelloword.helloWorld;

import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */

@RestController
@RequestMapping("/hello")

public class HelloWorld {
    @GetMapping
    public String hello() {
        throw new BaseException(ResponseCode.SERVICE_ERROR);
    }
}
