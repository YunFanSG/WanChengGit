package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Project;
import com.example.wanchengdemo.entity.Site;
import com.example.wanchengdemo.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
/*@RestController
@RequestMapping("/site")*/
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String sitecode){
        //构造分页控制器
        Page pageInfo = new Page(page,pagesize);

        //构造条件构造器
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper();
        /*//添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(sitecode), Site::getSitecode,sitecode);*/
        //添加一个排序条件
//        queryWrapper.orderByAsc();

        //执行查询
        siteService.page(pageInfo);
        return R.success(pageInfo);

    }
}
