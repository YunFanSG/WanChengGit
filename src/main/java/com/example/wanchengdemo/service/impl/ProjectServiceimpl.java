package com.example.wanchengdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wanchengdemo.domain.Project;
import com.example.wanchengdemo.mapper.ProjectMapper;
import com.example.wanchengdemo.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceimpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
}
