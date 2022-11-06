package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.domain.Segment;
import com.example.wanchengdemo.service.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.List;


@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
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

    //查询所有
    @GetMapping
    public R<List> getAll(Segment segment){
        //条件构造器
        LambdaQueryWrapper<Segment> queryWrapper = new LambdaQueryWrapper();
        //按检测范围
        queryWrapper.like(StringUtils.isNotEmpty(segment.getSegrange()),Segment::getSegrange,segment.getSegrange());
        //根据id
        queryWrapper.eq(StringUtils.isNotEmpty(segment.getSegid()),Segment::getSegid,segment.getSegid());
        //按设计弯沉值
        queryWrapper.like(StringUtils.isNotEmpty(segment.getSegdesign()),Segment::getSegdesign,segment.getSegdesign());
        //按时间
        queryWrapper.like(StringUtils.isNotEmpty(segment.getSegdate()),Segment::getSegdate,segment.getSegdate());


        //按segsid
        queryWrapper.eq(StringUtils.isNotEmpty(segment.getSegsid()),Segment::getSegsid,segment.getSegsid());


        List<Segment> list = segmentService.list(queryWrapper);
        return R.success(list);
    }

    //segment infomation
    //为segment 查询主要信息:date,检测单位,检测段落,


    //增加数据

    @PostMapping
    public R<String> insert(@RequestBody Segment segment){
        LambdaQueryWrapper<Segment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Segment::getSegid,segment.getSegid());
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();

        segment.setSegid(String.valueOf(snowflakeId));
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

    @GetMapping("/vo/page")
    public R pageVo(PageEntity pageEntity, Segment segment){
        return R.success(segmentService.pageVo(pageEntity.toPage(), segment));
    }
    @GetMapping("/vo/list")
    public R listVo(Segment segment){
        return R.success(segmentService.listVo(segment));
    }
    @GetMapping(value = "/vo/detail/{segid}")
    public R detailVo(@PathVariable("segid") String segid) {
        return R.success(segmentService.getOneVo(segid));
    }


}
