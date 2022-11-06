package com.example.wanchengdemo.entity.bo.rolepermission;
import com.example.wanchengdemo.domain.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor


public class RoleBo extends Role {
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;

    private String modifiedby;
    private String permissioname;
    private String permissionid;
    private String roleid;
    private String rolename;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;

    public RoleBo() {
    }

    public RoleBo(Role role) {
        super(role);
    }


}
