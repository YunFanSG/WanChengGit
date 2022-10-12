package com.example.wanchengdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wanchengdemo.entity.User;
import com.example.wanchengdemo.mapper.UserMapper;
import com.example.wanchengdemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl extends ServiceImpl<UserMapper, User> implements UserService {
}
