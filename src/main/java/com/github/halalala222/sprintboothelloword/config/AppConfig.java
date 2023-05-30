package com.github.halalala222.sprintboothelloword.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Configuration
@Getter
public class AppConfig {
    @Value("${person.name}")
    private String name;

    @Value("${jwt.expire-time}")
    private int expireTime;

    @Value("${jwt.secret}")
    private String secret;
}
