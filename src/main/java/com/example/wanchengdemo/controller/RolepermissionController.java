package com.example.wanchengdemo.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.commom.pageR;
import com.example.wanchengdemo.domain.Permission;
import com.example.wanchengdemo.domain.Role;
import com.example.wanchengdemo.domain.Rolepermission;
import com.example.wanchengdemo.domain.Segment;
import com.example.wanchengdemo.service.IRolepermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.PageEntity;


import java.util.Arrays;

@RestController
@RequestMapping("/test/rolepermission")
public class RolepermissionController {
    @Autowired
    private IRolepermissionService rolepermissionService;
    @GetMapping("/page")
    public R page(pageR pageR, Rolepermission rolepermission){
        return R.success(rolepermissionService.page(pageR.toPage(), Wrappers.lambdaQuery(rolepermission)));
    }
    @GetMapping("/list")
    public R list(Rolepermission rolepermission){
        return R.success(rolepermissionService.list(Wrappers.lambdaQuery(rolepermission)));
    }
    @PostMapping("/add")
    public R add(@RequestBody Rolepermission rolepermission) {
        //雪花算法生成id
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();
        return R.success(rolepermissionService.save(rolepermission));
    }
    @PutMapping("/edit")
    public R edit(@RequestBody Rolepermission rolepermission) {
        return R.success(rolepermissionService.updateById(rolepermission));
    }
    @DeleteMapping("/delete/{permissionids}")
    public R delete(@PathVariable Integer[] roleparmissionids) {
        return R.success(rolepermissionService.removeByIds(Arrays.asList(roleparmissionids)));
    }
    @GetMapping(value = "/detail/{permissionid}")
    public R detail(@PathVariable("permissionid") Integer roleparmissionid) {
        return R.success(rolepermissionService.getById(roleparmissionid));


    }



    @GetMapping("/permission/page")
    public R pageVo(PageEntity pageEntity, Permission permission){
        return R.success(rolepermissionService.pageVo(pageEntity.toPage(), permission));
    }
    @GetMapping("/permission/list")
    public R listVo(Permission permission){
        return R.success(rolepermissionService.listVo(permission));
    }

    @GetMapping(value = "/permission/detail/{id}")
    public R detailPermissionlVo(@PathVariable("id") String id) {
        return R.success(rolepermissionService.getPermissionVo( id));
    }


    @GetMapping("/role/page")
    public R pageVo(PageEntity pageEntity, Role role){
        return R.success(rolepermissionService.pageVo(pageEntity.toPage(), role));
    }
    @GetMapping("/role/list")
    public R listVo(Role role){
        return R.success(rolepermissionService.listVo(role));
    }

    @GetMapping(value = "/role/detail/{id}")
    public R detailRolelVo(@PathVariable("id") String id) {
        return R.success(rolepermissionService.getPermissionVo( id));
    }


}
