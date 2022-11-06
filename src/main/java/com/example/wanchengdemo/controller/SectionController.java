package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.domain.Section;
import com.example.wanchengdemo.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String name) {
        Page pageInfo = new Page();
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Section::getSname,name);
        lambdaQueryWrapper.orderByAsc(Section::getSid);
        sectionService.page(pageInfo,lambdaQueryWrapper);

        return R.success(pageInfo);
    }

    //查询所有
    @GetMapping
    public R<List> getAll(Section section){
        //条件构造器
        LambdaQueryWrapper<Section> queryWrapper = new LambdaQueryWrapper();
        //按项合同名
        queryWrapper.like(StringUtils.isNotEmpty(section.getSname()),Section::getSname,section.getSname());
        //按检测单位
        queryWrapper.like(StringUtils.isNotEmpty(section.getStesting()),Section::getStesting,section.getStesting());
        //按施工单位
        queryWrapper.like(StringUtils.isNotEmpty(section.getScons()),Section::getScons,section.getScons());

        //按id
        queryWrapper.eq(StringUtils.isNotEmpty(section.getSid()),Section::getSid,section.getSid());

        //按spid
        queryWrapper.eq(StringUtils.isNotEmpty(section.getSpid()),Section::getSpid,section.getSpid());



        List<Section> list = sectionService.list(queryWrapper);
        return R.success(list);
    }

    //增加数据

    @PostMapping
    public R<String> insert(@RequestBody Section section){
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Section::getSid,section.getSid());

        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();

        section.setSid(String.valueOf(snowflakeId));
        sectionService.save(section);
        return R.success("添加成功");
    }

    //删除数据

    @DeleteMapping
    public R<String> delete(@RequestBody Section section){
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Section::getSid,section.getSid());

        sectionService.remove(lambdaQueryWrapper);
        return R.success("删除成功");
    }

    //根据sid修改数据

    @PutMapping
    public R<String> updata(@RequestBody Section section){
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Section::getSid,section.getSid());

        sectionService.update(section,lambdaQueryWrapper);
        return R.success("添加成功");
    }

 }
