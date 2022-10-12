package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Project;
import com.example.wanchengdemo.entity.Segment;
import com.example.wanchengdemo.entity.Site;
import com.example.wanchengdemo.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private SiteService siteService;

    // 分页查询
    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String sitecode){
        Page pagInfo = new Page(page,pagesize);
        LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(sitecode),Site::getSitecode,sitecode);
        queryWrapper.orderByAsc(Site::getSitesid);

        siteService.page(pagInfo,queryWrapper);
        return R.success(pagInfo);
    }

    //增加数据

    @PostMapping
    public R<String> insert(@RequestBody Site site){
        LambdaQueryWrapper<Site> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Site::getSiteid,site.getSiteid());

        siteService.save(site);
        return R.success("添加成功");
    }

    //删除数据

    @DeleteMapping
    public R<String> delete(@RequestBody Site site){
        LambdaQueryWrapper<Site> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Site::getSiteid,site.getSiteid());

        siteService.remove(lambdaQueryWrapper);
        return R.success("删除成功");
    }

    //修改数据

    @PutMapping
    public R<String> change(@RequestBody Site site){
        LambdaQueryWrapper<Site> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Site::getSiteid,site.getSiteid());

        siteService.update(site,lambdaQueryWrapper);
        return R.success("修改成功");
    }
}
