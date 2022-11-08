package com.example.wanchengdemo.mapper.data;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wanchengdemo.entity.data.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
