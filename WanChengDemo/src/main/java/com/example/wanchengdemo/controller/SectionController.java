package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Section;
import com.example.wanchengdemo.service.ProjectService;
import com.example.wanchengdemo.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String name) {
        Page pageInfo = new Page();
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Section::getSname,name);
        lambdaQueryWrapper.orderByAsc(Section::getSid);
        sectionService.page(pageInfo,lambdaQueryWrapper);

        return R.success(pageInfo);
    }
}
