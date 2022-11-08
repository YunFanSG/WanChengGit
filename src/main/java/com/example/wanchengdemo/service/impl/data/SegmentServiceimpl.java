package com.example.wanchengdemo.service.impl.data;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.wanchengdemo.entity.data.Segment;
import com.example.wanchengdemo.mapper.data.SegmentMapper;
import com.example.wanchengdemo.service.data.SegmentService;
import org.springframework.stereotype.Service;

@Service
public class SegmentServiceimpl extends ServiceImpl<SegmentMapper, Segment> implements SegmentService {
}
