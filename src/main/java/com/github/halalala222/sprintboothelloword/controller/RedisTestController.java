package com.github.halalala222.sprintboothelloword.controller;

import com.github.halalala222.sprintboothelloword.handler.Response;
import org.springframework.data.redis.core.RedisTemplate;
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
@RequestMapping("/redis")
public class RedisTestController {

    private final RedisTemplate<String, String> redisTemplate;

    RedisTestController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public Response<Void> save() {
        redisTemplate.opsForValue().set("test", "test");
        return Response.successWithoutData();
    }
}
