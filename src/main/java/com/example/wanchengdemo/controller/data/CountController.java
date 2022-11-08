package com.example.wanchengdemo.controller.data;
/*
* 统计接口，提供统计数据信息
* */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.data.Project;
import com.example.wanchengdemo.entity.data.Section;
import com.example.wanchengdemo.service.data.ProjectService;
import com.example.wanchengdemo.service.data.SectionService;
import com.example.wanchengdemo.service.data.SegmentService;
import com.example.wanchengdemo.service.data.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/count")
public class CountController {
    @Autowired
    ProjectService projectService;

    @Autowired
    SectionService sectionService;

    @Autowired
    SegmentService segmentService;

    @Autowired
    SiteService siteService;


    LambdaQueryWrapper<Project> lambdaQueryWrapper = new LambdaQueryWrapper<>();

    LambdaQueryWrapper<Section> sectionLambdaQueryWrapper = new LambdaQueryWrapper<>();

    //project统计
    @GetMapping
    public R<String> count(Project project){
        //根据所有人id统计所有项目总数，合同段总数，检测段总数，检测点总数......

        int count = 0;              //统计数量
        List<Project> list = new ArrayList<>();     //相关数据
        List<Section> sectionlist = new ArrayList<>();     //相关数据
        List    result = new ArrayList();
        Dictionary endResult = new Hashtable();

        //项目总数-------------测试正常
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(project.getOwnerid()),Project::getOwnerid,project.getOwnerid());
        if (StringUtils.isNotEmpty(project.getOwnerid())){
            count = projectService.count(lambdaQueryWrapper);
            result = projectService.list(lambdaQueryWrapper);

            return R.success("统计成功，结果为：",count,result);
        }
        //合同段总数
/*        if (result != null){
            for (Project projectSection : list){
                //where project.pid == section.pid
                sectionLambdaQueryWrapper.eq(Section::getSpid,projectSection.getPid());
                log.info("++++++++++++++++++++++++++++");
                result = sectionService.list(sectionLambdaQueryWrapper);
                count =+ sectionService.count(sectionLambdaQueryWrapper);
            }
            return R.success("统计成功，结果为：",count,result);
        }*/

        //
        return R.error("服务器繁忙，请稍后重试");
    }
}
