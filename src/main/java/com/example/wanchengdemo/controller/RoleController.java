package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.commom.pageR;
import com.example.wanchengdemo.domain.Role;
import com.example.wanchengdemo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;

@RestController
@RequestMapping("/test/role")

public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("/page")
    public R page(pageR pageR, Role role){
        return R.success(roleService.page(pageR.toPage(), Wrappers.lambdaQuery(role)));
    }
    @GetMapping("/list")
    public R list(Role role){
        return R.success(roleService.list(Wrappers.lambdaQuery(role)));
    }
    @PostMapping("/add")
    public R add(@RequestBody Role role) {
        //雪花算法生成id
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();
        return R.success(roleService.save(role));
    }

    IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
 long snowflakeId = idGetSnowflake.snowflakeId();

    @PutMapping("/edit")
    public R edit(@RequestBody Role role) {
        return R.success(roleService.updateById(role));
    }
    @DeleteMapping("/delete/{roleids}")
    public R delete(@PathVariable Integer[] roleids) {
        return R.success(roleService.removeByIds(Arrays.asList(roleids)));
    }
    @GetMapping(value = "/detail/{roleid}")
    public R detail(@PathVariable("roleid") Integer roleid) {
        return R.success(roleService.getById(roleid));
    }
}

