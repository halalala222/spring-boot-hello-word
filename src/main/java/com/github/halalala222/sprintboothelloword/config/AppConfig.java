package com.github.halalala222.sprintboothelloword.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Configuration
public class AppConfig {
    @Value("${person.name}")
    private String name;

}
