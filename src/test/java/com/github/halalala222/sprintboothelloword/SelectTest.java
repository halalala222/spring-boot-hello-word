package com.github.halalala222.sprintboothelloword;

import com.github.halalala222.sprintboothelloword.entity.User;
import com.github.halalala222.sprintboothelloword.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */

@SpringBootTest
public class SelectTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectTest() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
