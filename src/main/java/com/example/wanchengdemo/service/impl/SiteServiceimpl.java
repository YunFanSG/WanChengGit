package com.example.wanchengdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wanchengdemo.entity.Site;
import com.example.wanchengdemo.mapper.SiteMapper;
import com.example.wanchengdemo.service.SiteService;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceimpl extends ServiceImpl<SiteMapper, Site> implements SiteService {
}
