package com.github.halalala222.sprintboothelloword.constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public class RedisConstants {
    public static String BASIC_PREFIX = "spring-boot-hello-world";
    public static String USER_PROFILE_KEY_PREFIX = "user-profile";

    public static String DIARIES_KEY_PREFIX = "diaries";
    public static long USER_PROFILE_TTL = 60 * 60 * 5;

    public static long DIARIES_TTL = 60 * 60 * 5;

    public static String getFullKey(String key, String unique) {
        if (unique == null) {
            return BASIC_PREFIX + "-" + key;
        }
        return BASIC_PREFIX + "-" + key + "-" + unique;
    }
}
