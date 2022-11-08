package com.example.wanchengdemo.service.impl.data;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.wanchengdemo.entity.data.Project;
import com.example.wanchengdemo.mapper.data.ProjectMapper;
import com.example.wanchengdemo.service.data.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceimpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
}
