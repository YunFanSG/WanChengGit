package com.example.wanchengdemo.service.impl.data;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.wanchengdemo.entity.data.Site;
import com.example.wanchengdemo.mapper.data.SiteMapper;
import com.example.wanchengdemo.service.data.SiteService;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceimpl extends ServiceImpl<SiteMapper, Site> implements SiteService {
}
