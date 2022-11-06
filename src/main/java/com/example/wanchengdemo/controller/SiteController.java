package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.domain.Site;
import com.example.wanchengdemo.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
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

    //查询所有
    @GetMapping
    public R<List> getAll(Site site){
        LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();

        //按id
        queryWrapper.eq(StringUtils.isNotEmpty(site.getSiteid()),Site::getSiteid,site.getSiteid());

        //按sitesid
        queryWrapper.eq(StringUtils.isNotEmpty(site.getSitesid()),Site::getSitesid,site.getSitesid());

        //按桩号查询
       queryWrapper.like(StringUtils.isNotEmpty(site.getSitecode()),Site::getSitecode,site.getSitecode());
        //按车道
        queryWrapper.like(StringUtils.isNotEmpty(site.getSitelane()),Site::getSitelane,site.getSitelane());
//        //按左侧弯沉值
        if (site.getDeflectio1() > 0){
            queryWrapper.like(StringUtils.isNotEmpty(String.valueOf(site.getDeflectio1())),Site::getDeflectio1,site.getDeflectio1());

        }
        //按右侧弯沉值
        if (site.getDeflectio2() > 0){
            queryWrapper.like(StringUtils.isNotEmpty(String.valueOf(site.getDeflectio2())),Site::getDeflectio2,site.getDeflectio2());

        }












        List<Site> list = siteService.list(queryWrapper);
        return R.success(list);
    }

    //增加数据

    @PostMapping
    public R<String> insert(@RequestBody Site site){
        LambdaQueryWrapper<Site> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Site::getSiteid,site.getSiteid());
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();

        site.setSiteid(String.valueOf(snowflakeId));
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
