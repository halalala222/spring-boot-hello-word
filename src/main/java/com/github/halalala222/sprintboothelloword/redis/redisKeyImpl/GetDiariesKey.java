package com.github.halalala222.sprintboothelloword.redis.redisKeyImpl;

import com.github.halalala222.sprintboothelloword.constants.RedisConstants;
import com.github.halalala222.sprintboothelloword.redis.RedisKey;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Component
public class GetDiariesKey implements RedisKey {
    @Override
    public String getKey() {
        return RedisConstants.getFullKey(
                RedisConstants.DIARIES_KEY_PREFIX,
                null
        );
    }
}
