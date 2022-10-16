package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.User;
import com.example.wanchengdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public R<String> login(HttpServletRequest request, @RequestBody User user){
        // 1.将密码进行MD5加密

        String pwd = user.getPwd();
        //     pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());

        //2.根据页面提交的用户名来查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User ump = userService.getOne(queryWrapper);

        //3.如果没有查询到则返回失败结果
        if ( ump == null){
            log.info("查无此人");
            return R.error("登录失败");
        }

        //4.密码比对，不一致则返回登录失败结果
        if (!ump.getPwd().equals(pwd)){
            log.info("密码错误");
            return R.error("登录失败");

        }


        //5.登录成功，将用户id存入Session并返回登录成功结果   返回token
        String token = TokenUtil.generateToken(ump);

        request.getSession().setAttribute("user",ump.getUid());
        return R.success(token);


    }

    @PostMapping("/register")
    public R<User> register(HttpServletRequest request,@RequestBody User user){

        //1.根据页面提交的用户名来查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User ump = userService.getOne(queryWrapper);

        //3.如果没有查询到则继续
        if ( ump == null){
            log.info("开始注册");
            System.out.println(user);
            userService.save(user);
            return null;
        }else {
            return R.error("用户名已注册");
        }



    }

    @GetMapping("/page")
    public R<Page> page(int page, int pagesize, String username){
        log.info("page = {} ,pagesize = {} ,username = {}",page , pagesize, username);

        //构造分页控制器
        Page pageInfo = new Page(page,pagesize);

        //构造条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(username),User::getUsername,username);
        //添加一个排序条件
        queryWrapper.orderByAsc(User::getDepartment);

        //执行查询
        userService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }




}
