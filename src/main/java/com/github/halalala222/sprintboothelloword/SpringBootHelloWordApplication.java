package com.github.halalala222.sprintboothelloword;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@MapperScan("com.github.halalala222.sprintboothelloword.mapper")
@EnableTransactionManagement
public class SpringBootHelloWordApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloWordApplication.class, args);
    }


}

