package com.example.wanchengdemo.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wanchengdemo.domain.Userrole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface UserroleMapper extends BaseMapper<Userrole> {
}
