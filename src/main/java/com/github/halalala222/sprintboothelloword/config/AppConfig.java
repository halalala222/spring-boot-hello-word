package com.github.halalala222.sprintboothelloword.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 * @Project : sprint-boot-hello-word
 * @Package : com.github.halalala222.sprintboothelloword.config
 * @ClassName : .java
 * @createTime : 2023/5/29 9:37
 * @Email : 1741196223@qq.com
 * @Description :
 */
@Configuration
public class AppConfig {
    @Value("${person.name}")
    private String name;

    public String getName() {
        return name;
    }
}
