package com.example.wanchengdemo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wanchengdemo.domain.User;
import com.example.wanchengdemo.entity.vo.UserVo;
import com.example.wanchengdemo.mapper.UserMapper;
import com.example.wanchengdemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public IPage<User> selectPage(UserVo userVO) {
        return null;
    }

    @Override
    public User selectByUsername(String username) {
        return null;
    }

    @Override
    public Integer countByUsername(String username) {
        return null;
    }

    @Override
    public List<String> selectChild(String id, Boolean bool) {
        return null;
    }
}
