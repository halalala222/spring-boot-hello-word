package com.github.halalala222.sprintboothelloword.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.halalala222.sprintboothelloword.entity.User;
import com.github.halalala222.sprintboothelloword.mapper.UserMapper;
import com.github.halalala222.sprintboothelloword.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
