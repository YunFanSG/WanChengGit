package com.example.wanchengdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wanchengdemo.entity.Section;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SectionMapper extends BaseMapper<Section> {
}
