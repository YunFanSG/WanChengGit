package com.example.wanchengdemo.service.impl.data;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.wanchengdemo.entity.data.Section;
import com.example.wanchengdemo.mapper.data.SectionMapper;
import com.example.wanchengdemo.service.data.SectionService;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceimpl extends ServiceImpl<SectionMapper, Section> implements SectionService {
}
