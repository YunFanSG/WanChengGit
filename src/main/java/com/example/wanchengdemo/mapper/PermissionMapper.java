package com.example.wanchengdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wanchengdemo.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface PermissionMapper extends BaseMapper<Permission> {
}
