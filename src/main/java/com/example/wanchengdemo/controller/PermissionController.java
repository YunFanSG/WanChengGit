package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.commom.pageR;
import com.example.wanchengdemo.domain.Permission;
import com.example.wanchengdemo.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/test/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @GetMapping("/page")
    public R page(pageR pageR, Permission permission){
        return R.success(permissionService.page(pageR.toPage(), Wrappers.lambdaQuery(permission)));
    }
    @GetMapping("/list")
    public R list(Permission permission){
        return R.success(permissionService.list(Wrappers.lambdaQuery(permission)));
    }
    @PostMapping("/add")
    public R add(@RequestBody Permission permission) {
        //雪花算法生成id
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();
        return R.success(permissionService.save(permission));

    }
    @PutMapping("/edit")
    public R edit(@RequestBody Permission permission) {
        return R.success(permissionService.updateById(permission));
    }
    @DeleteMapping("/delete/{permissionids}")
    public R delete(@PathVariable Integer[] permissionids) {
        return R.success(permissionService.removeByIds(Arrays.asList(permissionids)));
    }
    @GetMapping(value = "/detail/{permissionid}")
    public R detail(@PathVariable("permissionid") Integer permissionid) {
        return R.success(permissionService.getById(permissionid));
    }
}
