package com.example.wanchengdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wanchengdemo.domain.Userrole;
import com.example.wanchengdemo.mapper.UserroleMapper;
import com.example.wanchengdemo.service.IUserroleService;
import org.springframework.stereotype.Service;

@Service
public class UserroleServiceImpl extends ServiceImpl<UserroleMapper, Userrole> implements IUserroleService {
}
