package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.Project;
import com.example.wanchengdemo.entity.User;
import com.example.wanchengdemo.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    //分页查询
    /*
    * 1.页面发送ajax请求，将分页参数(page,pagesize,name等)提交到服务端
    * 2.服务端controller 接收页面提交的数据并调用数据库
    * 3.Service 调用 Mapper 操作数据库，查询分页数据，
    * 4.Controller 将查询到的数据响应给页面
    * 5.页面展示数据
    * */
    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String pname){
        //构造分页控制器
        Page pageInfo = new Page(page,pagesize);

        //构造条件构造器
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(pname),Project::getPname,pname);
        //添加一个排序条件
        queryWrapper.orderByAsc(Project::getPname);

        //执行查询
        projectService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);

    }


    //新增数据
    @PostMapping
    public R<String> insert(HttpServletRequest request, @RequestBody Project project){
        log.info("增加数据 ");
        projectService.save(project);
        return R.success("添加成功");
    }

    //根据id删除数据 ***
    @DeleteMapping
    public R<String> delete(@RequestBody Project project){
        log.info("删除数据，删除id 为：{} ", project.getPid());

        LambdaQueryWrapper<Project> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Project::getPid,project.getPid());

        projectService.remove(lambdaQueryWrapper);
        return R.success("删除成功");
    }

    //修改数据

    @PutMapping
    public R<String> updata(@RequestBody Project project){
        LambdaQueryWrapper<Project> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Project::getPid,project.getPid());

        log.info("pid {}",project.getPid());
        projectService.update(project,lambdaQueryWrapper);
        return R.success("修改成功");
    }


}
