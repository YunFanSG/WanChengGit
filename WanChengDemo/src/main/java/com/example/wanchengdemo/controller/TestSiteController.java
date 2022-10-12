package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Project;
import com.example.wanchengdemo.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/site")
public class TestSiteController {
    @Autowired
    private SiteService siteService;


    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String sitecode ){
        Page pagInfo = new Page(page,pagesize);

        siteService.page(pagInfo);
        return R.success(pagInfo);
    }
}
