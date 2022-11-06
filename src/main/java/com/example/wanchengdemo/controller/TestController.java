package com.example.wanchengdemo.controller;

import com.example.wanchengdemo.entity.vo.ResultVO;
import com.example.wanchengdemo.util.JwtUtil;
import com.example.wanchengdemo.util.ResultVOUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
public class TestController {
    @RequestMapping("/login")
    public ResultVO<Object> login() throws IOException {
        // 生成token，token有效时间为30分钟
        String token = JwtUtil.createJWT(String.valueOf(new Date()), "user", 3600000L);
        // 将用户户名和token返回
        return ResultVOUtil.success(token);
    }

    @RequestMapping("/token/admin")
    public ResultVO<Object> token() {
        return ResultVOUtil.success("需要token才可以访问的接口");
    }
}