package com.example.wanchengdemo.controller.data;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.data.Section;
import com.example.wanchengdemo.entity.data.Segment;
import com.example.wanchengdemo.entity.data.Site;
import com.example.wanchengdemo.service.data.SectionService;
import com.example.wanchengdemo.service.data.SegmentService;
import com.example.wanchengdemo.service.data.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/segment")
public class SegmentController {

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    SiteService siteService;

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
    //为segment 查询主要信息:date,检测单位,检测段落,路幅，桩号处理，验收弯沉值，路面温度  与该segment下相关site数据　
    //segdate,section.scons,segrange,roadway,roadhandle,segdesign,pavment_tp
//sql1:SELECT segment.segid,segment.segdate,section.scons,segment.segrange,segment.roadway,segment.roadhandle,segment.segdesign FROM section,segment WHERE segment.segsid=section.sid;
    @GetMapping("/info")
    public R<List> getInfo(Section section, Segment segment){
        //segment表条件构造器
        LambdaQueryWrapper<Segment> queryWrapper = new LambdaQueryWrapper<>();
        //segment条件segid相同　　
        queryWrapper.eq(Segment::getSegid,segment.getSegid());
        //查询segment
        Segment segmentServiceOne = segmentService.getOne(queryWrapper);


        //section表条件构造器
        LambdaQueryWrapper<Section> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Section::getSid,section.getSid());
        lambdaQueryWrapper.eq(Section::getSid,segmentServiceOne.getSegsid());
        //根据返回sgesid 查询section
        Section sectionServiceOne = sectionService.getOne(lambdaQueryWrapper);

        //返回结果
        //segdate,section.scons,segrange,roadway,roadhandle,segdesign,pavment_tp
        List<String> result = new ArrayList<>();
        result.add(segmentServiceOne.getSegdate());
        result.add(sectionServiceOne.getScons());
        result.add(segmentServiceOne.getSegrange());
        result.add(segmentServiceOne.getRoadway());
        result.add(segmentServiceOne.getRoadhandle());
        result.add(segmentServiceOne.getSegdesign());
        result.add(segmentServiceOne.getPavement_tp());


        //根据segid.id返回　所有相关检测点信息
        LambdaQueryWrapper<Site> siteLambdaQueryWrapper= new LambdaQueryWrapper<>();
        siteLambdaQueryWrapper.eq(Site::getSitesid,segmentServiceOne.getSegid());

        List<Site> data = siteService.list(siteLambdaQueryWrapper);
        return R.success("查询成功",result,data);
    }


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




}
