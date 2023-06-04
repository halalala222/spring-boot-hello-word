package com.github.halalala222.sprintboothelloword.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
public class BcryptUtils {
    public static String encoded(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(String password, String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }
}
