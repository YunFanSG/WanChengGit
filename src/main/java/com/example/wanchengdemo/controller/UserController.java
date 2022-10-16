package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wanchengdemo.annotation.PassToken;
import com.example.wanchengdemo.annotation.UserLoginToken;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.User;
import com.example.wanchengdemo.service.UserService;
import com.example.wanchengdemo.util.TestJwt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    
    /*@PostMapping("/login")
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


        //5.登录成功，将用户id存入Session并返回登录成功结果
        request.getSession().setAttribute("user",ump.getUid());
        return R.success("登录成功");


    }*/

    @PassToken
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
        String token = TestJwt.generateToken(ump);

        return R.success(token);




    }

    /*
     * 用户退出
     * */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中保存的的当前用户 id
        //清理redis中的token
        request.getSession().removeAttribute("user");
        return R.success("退出登录");
    }

    // 超级管理员新增用户
    @UserLoginToken
    @PostMapping
    public R<String> register(HttpServletRequest request,@RequestBody User user){

        //1.根据页面提交的用户名来查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User ump = userService.getOne(queryWrapper);

        //3.如果没有查询到则继续
        if ( ump == null){
            log.info("开始新增用户");
            System.out.println(user);
            userService.save(user);
            return R.success("添加用户成功");
        }else {
            return R.error("用户名已被使用");
        }



    }

    @GetMapping("/page")
    public R<Page> page(int page,int pagesize,String username){
        //构造分页控制器
        Page pageInfo = new Page(page,pagesize);

        //构造条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(username),User::getUsername,username);
        //添加一个排序条件
        queryWrapper.orderByAsc(User::getUid);

        //执行查询
        userService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);

    }

    //查询所有 或 条件查询
    @GetMapping
    public R<List> getAll(User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(user.getUid() != 0,User::getUid,user.getUid());
        queryWrapper.like(StringUtils.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());
        queryWrapper.like(StringUtils.isNotEmpty(user.getDepartment()),User::getDepartment,user.getDepartment());


        List<User> list = userService.list(queryWrapper);
        return R.success(list);
    }

    //getuser 获得用户信息



    //模糊查询  待测试 第一测试正常
/*    @GetMapping("/getLike")
    public R<List> getLike(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(user.getUid() != 0,User::getUid,user.getUid());
        queryWrapper.like(StringUtils.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());
        queryWrapper.like(StringUtils.isNotEmpty(user.getDepartment()),User::getDepartment,user.getDepartment());
        List<User> list = userService.list(queryWrapper);
        return R.success(list);
    }*/


    //根据id删除用户
    @DeleteMapping
    public R<String> delete(@RequestBody User user){
        log.info("删除数据，删除id 为：{} ", user.getUid());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUid,user.getUid());

        userService.remove(lambdaQueryWrapper);
        return R.success("删除成功");
    }

    //根据id更新用户
    @PutMapping
    public R<String> change(@RequestBody User user){
        log.info("更新用户信息 操作id 为：{} ", user.getUid());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUid,user.getUid());

        userService.update(user,lambdaQueryWrapper);
        return R.success("更新成功");
    }


    @UserLoginToken
        @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }



        



}
