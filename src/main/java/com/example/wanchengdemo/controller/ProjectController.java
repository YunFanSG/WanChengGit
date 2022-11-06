package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.domain.Project;
import com.example.wanchengdemo.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
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
        queryWrapper.orderByAsc(Project::getPid);

        //执行查询
//报错        projectService.page(pageInfo,queryWrapper);
        projectService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

    }

    //查询所有 或模糊查询
    @GetMapping
    public R<List> getAll(Project project){
        //条件构造器
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper();

        log.info(String.valueOf(project.getPid()));

        //按pid进行精准查询
        queryWrapper.eq(StringUtils.isNotEmpty(project.getPid()),Project::getPid,project.getPid());

        //按项目名
        queryWrapper.like(StringUtils.isNotEmpty(project.getPname()),Project::getPname,project.getPname());
        //按项目所有人
        queryWrapper.like(StringUtils.isNotEmpty(project.getPowner()),Project::getPowner,project.getPowner());
        //按id

        //无参则查询所有
        List<Project> list = projectService.list(queryWrapper);
        return R.success(list);
    }




    //新增数据
    @PostMapping
    public R<String> insert(HttpServletRequest request, @RequestBody Project project){
        //雪花算法生成id
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();

        project.setPid(String.valueOf(snowflakeId));
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
        lambdaQueryWrapper.eq(Project::getPname,project.getPname());

        log.info("pid {}",project.getPid());
        projectService.update(project,lambdaQueryWrapper);
        return R.success("修改成功");
    }



    //模糊查询  待测试
  /*  @GetMapping("/getLike")
    public R<List> getLike(@RequestBody Project project){
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper();

        //按项目名
        queryWrapper.like(StringUtils.isNotEmpty(project.getPname()),Project::getPname,project.getPname());
//
        //按项目所有人
        queryWrapper.like(StringUtils.isNotEmpty(project.getPowner()),Project::getPowner,project.getPowner());

        List<Project> list = projectService.list(queryWrapper);

        return R.success(list);
    }
*/


}
