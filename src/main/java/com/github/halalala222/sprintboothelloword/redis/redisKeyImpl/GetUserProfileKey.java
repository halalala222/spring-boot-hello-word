package com.github.halalala222.sprintboothelloword.redis.redisKeyImpl;

import com.github.halalala222.sprintboothelloword.constants.RedisConstants;
import com.github.halalala222.sprintboothelloword.redis.RedisKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public class GetUserProfileKey implements RedisKey {
    @Override
    public String getUniqueKey(Object unique) {
        return RedisConstants.getFullKey(
                RedisConstants.DIARIES_KEY_PREFIX,
                unique
        );
    }
}
