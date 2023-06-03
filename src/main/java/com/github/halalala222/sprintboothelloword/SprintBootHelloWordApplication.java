package com.github.halalala222.sprintboothelloword;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.github.halalala222.sprintboothelloword.mapper")
public class SprintBootHelloWordApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintBootHelloWordApplication.class, args);
    }


}

