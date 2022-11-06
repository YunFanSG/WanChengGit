package com.example.wanchengdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wanchengdemo.domain.User;
import com.example.wanchengdemo.entity.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {
    IPage<User> selectPage(UserVo userVO);

    User selectByUsername(String username);

    Integer countByUsername(String username);

    /**
     * 查询用户及下级用户的id集合
     *
     * @param id   用户id
     * @param bool 是否包含自己
     * @return
     */
    List<String> selectChild(String id, Boolean bool);
}
