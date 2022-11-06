package com.example.wanchengdemo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wanchengdemo.commom.IdGetSnowflake;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.commom.pageR;
import com.example.wanchengdemo.domain.Userrole;
import com.example.wanchengdemo.service.IUserroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/test/userrole")
public class UserroleController {
    @Autowired
    private IUserroleService userroleService;
    @GetMapping("/page")
    public R page(pageR pageR, Userrole userrole){
        return R.success(userroleService.page(pageR.toPage(), Wrappers.lambdaQuery(userrole)));
    }
    @GetMapping("/list")
    public R list(Userrole userrole){
        return R.success(userroleService.list(Wrappers.lambdaQuery(userrole)));
    }
    @PostMapping("/add")
    public R add(@RequestBody Userrole userrole) {
        //雪花算法生成id
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        long snowflakeId = idGetSnowflake.snowflakeId();
        return R.success(userroleService.save(userrole));
    }
    @PutMapping("/edit")
    public R edit(@RequestBody Userrole userrole) {
        return R.success(userroleService.updateById(userrole));
    }
    @DeleteMapping("/delete/{roleids}")
    public R delete(@PathVariable Integer[] roleids) {
        return R.success(userroleService.removeByIds(Arrays.asList(roleids)));
    }
    @GetMapping(value = "/detail/{roleid}")
    public R detail(@PathVariable("roleid") Integer roleid) {
        return R.success(userroleService.getById(roleid));
    }
}
