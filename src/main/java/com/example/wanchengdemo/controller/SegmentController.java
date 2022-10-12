package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Section;
import com.example.wanchengdemo.entity.Segment;
import com.example.wanchengdemo.service.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //增加数据

    @PostMapping
    public R<String> insert(@RequestBody Segment segment){
        LambdaQueryWrapper<Segment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Segment::getSegid,segment.getSegid());

        segmentService.save(segment);
        return R.success("添加成功");
    }

    //删除数据

    @DeleteMapping
    public R<String> delete(@RequestBody Segment segment){
        LambdaQueryWrapper<Segment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Segment::getSegid,segment.getSegid());

        segmentService.remove(lambdaQueryWrapper);
        return R.success("添加成功");
    }

    //修改数据

    @PutMapping
    public R<String> update(@RequestBody Segment segment){
        LambdaQueryWrapper<Segment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Segment::getSegid,segment.getSegid());

        segmentService.update(segment,lambdaQueryWrapper);
        return R.success("修改成功");
    }




}
