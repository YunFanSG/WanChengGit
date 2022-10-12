package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Segment;
import com.example.wanchengdemo.service.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/segment")
public class SegmentController {

    @Autowired
    private SegmentService segmentService;

    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String segrange){
        Page pagInfo = new Page(page,pagesize);
        LambdaQueryWrapper<Segment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(segrange),Segment::getSegrange,segrange);
        queryWrapper.orderByAsc(Segment::getSegrange);

        segmentService.page(pagInfo);

        return R.success(pagInfo);



    }


}
