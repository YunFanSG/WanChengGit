package com.example.wanchengdemo.service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;

import com.example.wanchengdemo.domain.*;
import com.example.wanchengdemo.entity.bo.rolepermission.PermissionBo;
import com.example.wanchengdemo.entity.bo.rolepermission.RoleBo;
import com.example.wanchengdemo.entity.vo.rolepermission.PermissionVo;
import com.example.wanchengdemo.entity.vo.rolepermission.RoleVo;
import com.google.common.collect.Table;
import xin.altitude.cms.common.util.BeanCopyUtils;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.common.util.TableUtils;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface IRolepermissionService extends IService<Rolepermission> {


    IRoleService RoleService= SpringUtils.getBean(IRoleService.class);
    IPermissionService PermissionService= SpringUtils.getBean(IPermissionService.class);


    default PermissionVo getPermissionVo(String permissionId) {
        PermissionVo permissionVo = EntityUtils.toObj(PermissionService.getById(permissionId), PermissionVo::new);
        List<Rolepermission> rolepermissions = list(Wrappers.lambdaQuery(Rolepermission.class).eq(Rolepermission::getPermissionid, permissionId));
        Set<String> roleIds = EntityUtils.toSet(rolepermissions, Rolepermission::getRoleid);
        if (permissionVo != null && roleIds .size() > 0) {
            LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery(Role.class).in(Role::getId, roleIds );
            List<RoleBo> subBoList = EntityUtils.toList(RoleService.list(queryWrapper), RoleBo::new);
            Table<String, String, Rolepermission> table = TableUtils.createHashTable(rolepermissions, Rolepermission::getPermissionid, Rolepermission::getRoleid);
            subBoList.forEach(e -> BeanCopyUtils.copyProperties(table.get(permissionId, e.getId()), e));
            permissionVo.setRoleBoList(subBoList);
        }
        return permissionVo;
    }
    default List<PermissionVo> listVo(Permission permission) {
        List<PermissionVo> permissionVoList = EntityUtils.toList(PermissionService.list(Wrappers.lambdaQuery(permission)), PermissionVo::new);
        Set<String> permissionIds = EntityUtils.toSet(permissionVoList, Permission::getId);
        if (permissionIds.size() == 0) {
            return permissionVoList;
        }
        List<Rolepermission> rolepermissions = list(Wrappers.lambdaQuery(Rolepermission .class).in(Rolepermission::getPermissionid,  permissionIds));
        Set<String> roleIds = EntityUtils.toSet(rolepermissions, Rolepermission::getRoleid);
        Map<String, List<String>> map = rolepermissions.stream().collect(Collectors.groupingBy(Rolepermission::getPermissionid, Collectors.mapping(Rolepermission::getRoleid, Collectors.toList())));
        if (roleIds.size() == 0) {
            return permissionVoList;
        }
        Table<String, String, Rolepermission> table = TableUtils.createHashTable(rolepermissions, Rolepermission::getPermissionid, Rolepermission::getRoleid);
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery(Role.class).in(Role::getId, roleIds);
        List<RoleBo> roleBoList = EntityUtils.toList(RoleService.list(queryWrapper), RoleBo::new);
        for (PermissionVo permissionVo : permissionVoList) {
            List<RoleBo> list = roleBoList.stream().filter(e -> map.get(permissionVo.getId()) != null && map.get(permissionVo.getId()).contains(e.getId())).collect(Collectors.toList());
            list.forEach(e -> BeanCopyUtils.copyProperties(table.get(permissionVo.getId(), e.getId()), e));
            permissionVo.setRoleBoList(list);
        }
        return permissionVoList;
    }
    default IPage<PermissionVo> pageVo(IPage<Permission> page, Permission permission) {
        IPage<PermissionVo> permissionVoPage = EntityUtils.toPage(PermissionService.page(page, Wrappers.lambdaQuery(permission)), PermissionVo::new);
        Set<String> permissionIds = EntityUtils.toSet(permissionVoPage.getRecords(), Permission::getId);
        if (permissionIds.size() == 0) {
            return permissionVoPage;
        }
        List<Rolepermission> rolepermissions = list(Wrappers.lambdaQuery(Rolepermission.class).in(Rolepermission::getPermissionid,  permissionIds));
        Set<String> roleIds = EntityUtils.toSet(rolepermissions, Rolepermission::getRoleid);
        if ( roleIds.size() == 0) {
            return permissionVoPage;
        }
        Table<String, String, Rolepermission> table = TableUtils.createHashTable(rolepermissions, Rolepermission::getPermissionid, Rolepermission::getRoleid);
        Map<String, List<String>> map = rolepermissions.stream().collect(Collectors.groupingBy(Rolepermission::getPermissionid, Collectors.mapping(Rolepermission::getRoleid, Collectors.toList())));
        List<RoleBo> roleBoList = EntityUtils.toList(RoleService.list(Wrappers.lambdaQuery(Role.class).in(Role::getId,  roleIds)), RoleBo::new);
        for (PermissionVo permissionVo : permissionVoPage.getRecords()) {
            List<RoleBo> list = roleBoList.stream().filter(e -> map.get(permissionVo.getId()) != null && map.get(permissionVo.getId()).contains(e.getId())).collect(Collectors.toList());
            list.forEach(e -> BeanCopyUtils.copyProperties(table.get(permissionVo.getId(), e.getId()), e));
            permissionVo.setRoleBoList(list);
        }
        return permissionVoPage;
    }




    default RoleVo getRoleVo(String roleId) {
        RoleVo roleVo = EntityUtils.toObj(RoleService.getById(roleId), RoleVo::new);
        List<Rolepermission> rolepermissions = list(Wrappers.lambdaQuery(Rolepermission.class).eq(Rolepermission::getRoleid, roleId));
        Set<String> tbPassengerIds = EntityUtils.toSet(rolepermissions, Rolepermission::getPermissionid);
        if (roleVo != null && rolepermissions.size() > 0) {
            LambdaQueryWrapper<Permission> queryWrapper = Wrappers.lambdaQuery(Permission.class).in(Permission::getId, tbPassengerIds);
            List<PermissionBo> subBoList = EntityUtils.toList(PermissionService.list(queryWrapper), PermissionBo::new);
            Table<String, String, Rolepermission> table = TableUtils.createHashTable(rolepermissions, Rolepermission::getRoleid, Rolepermission::getPermissionid);
            subBoList.forEach(e -> BeanCopyUtils.copyProperties(table.get(roleId, e.getId()), e));
            roleVo.setPermissionBoList(subBoList);
        }
        return roleVo;
    }
    default List<RoleVo> listVo(Role role) {
        List<RoleVo> roleVoList = EntityUtils.toList(RoleService.list(Wrappers.lambdaQuery( role)), RoleVo::new);
        Set<String> roleIds = EntityUtils.toSet(roleVoList, Role::getId);
        if (roleIds.size() == 0) {
            return roleVoList;
        }
        List<Rolepermission> rolepermissions = list(Wrappers.lambdaQuery(Rolepermission.class).in(Rolepermission::getRoleid, roleIds));
        Set<String> permissionIds = EntityUtils.toSet(rolepermissions, Rolepermission::getPermissionid);
        Map<String, List<String>> map = rolepermissions.stream().collect(Collectors.groupingBy(Rolepermission::getRoleid, Collectors.mapping(Rolepermission::getPermissionid, Collectors.toList())));
        if (permissionIds.size() == 0) {
            return roleVoList;
        }
        Table<String, String, Rolepermission> table = TableUtils.createHashTable(rolepermissions, Rolepermission::getRoleid, Rolepermission::getPermissionid);
        LambdaQueryWrapper<Permission> queryWrapper = Wrappers.lambdaQuery(Permission.class).in(Permission::getId, permissionIds);
        List<PermissionBo> permissopnBoList = EntityUtils.toList(PermissionService.list(queryWrapper), PermissionBo::new);
        for (RoleVo roleVo : roleVoList) {
            List<PermissionBo> list = permissopnBoList.stream().filter(e -> map.get(roleVo.getId()) != null && map.get(roleVo.getId()).contains(e.getId())).collect(Collectors.toList());
            list.forEach(e -> BeanCopyUtils.copyProperties(table.get(roleVo.getId(), e.getId()), e));
            roleVo.setPermissionBoList(list);
        }
        return roleVoList;
    }
    default IPage<RoleVo> pageVo(IPage<Role> page, Role role) {
        IPage<RoleVo> roleVoPage = EntityUtils.toPage(RoleService.page(page, Wrappers.lambdaQuery(role)), RoleVo::new);
        Set<String> roleIds = EntityUtils.toSet(roleVoPage.getRecords(), Role::getId);
        if (roleIds.size() == 0) {
            return roleVoPage;
        }
        List<Rolepermission> rolepermissions = list(Wrappers.lambdaQuery(Rolepermission.class).in(Rolepermission::getRoleid, roleIds));
        Set<String> permissionIds = EntityUtils.toSet(rolepermissions, Rolepermission::getPermissionid);
        if (permissionIds.size() == 0) {
            return roleVoPage;
        }
            Table<String, String, Rolepermission> table = TableUtils.createHashTable(rolepermissions, Rolepermission::getPermissionid, Rolepermission::getPermissionid);
            Map<String, List<String>> map = rolepermissions.stream().collect(Collectors.groupingBy(Rolepermission::getRoleid, Collectors.mapping(Rolepermission::getPermissionid, Collectors.toList())));
            List<PermissionBo> permiswsionBoList = EntityUtils.toList(PermissionService.list(Wrappers.lambdaQuery(Permission.class).in(Permission::getId, permissionIds)), PermissionBo::new);
            for (RoleVo roleVo : roleVoPage.getRecords()) {
                List<PermissionBo> list = permiswsionBoList.stream().filter(e -> map.get(roleVo.getId()) != null && map.get(roleVo.getId()).contains(e.getId())).collect(Collectors.toList());
                list.forEach(e -> BeanCopyUtils.copyProperties(table.get(roleVo.getId(), e.getId()), e));
                roleVo.setPermissionBoList(list);
            }
        return roleVoPage;
        }


    }