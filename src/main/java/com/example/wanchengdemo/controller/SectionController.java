package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Section;
import com.example.wanchengdemo.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    //增加数据

    @PostMapping
    public R<String> insert(@RequestBody Section section){
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Section::getSid,section.getSid());

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
