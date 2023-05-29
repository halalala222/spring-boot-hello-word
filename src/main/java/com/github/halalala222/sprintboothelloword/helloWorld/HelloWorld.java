package com.github.halalala222.sprintboothelloword.helloWorld;

import com.github.halalala222.sprintboothelloword.config.AppConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 * @Project : sprint-boot-hello-word
 * @Package : com.github.halalala222.sprintboothelloword.helloWorld
 * @ClassName : HelloWorld.java
 * @createTime : 2023/5/28 22:59
 * @Email : 1741196223@qq.com
 * @Description :
 */

@RestController
@RequestMapping("/hello")

public class HelloWorld {
    private final AppConfig config;

    public HelloWorld(AppConfig config) {
        this.config = config;
    }

    @GetMapping
    public String hello() {
        return config.getName();
    }
}
