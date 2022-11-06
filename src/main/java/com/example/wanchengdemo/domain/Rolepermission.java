package com.example.wanchengdemo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "rolepermission")
public class Rolepermission{
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
    
    public Rolepermission(Rolepermission rolepermission) {
        if (Objects.nonNull(rolepermission)) {
            this.CreateTime=rolepermission.CreateTime;
            this.modifiedby=rolepermission.modifiedby;
            this.permissioname=rolepermission.permissioname;
            this.permissionid=rolepermission.permissionid;
            this.roleid=rolepermission.roleid;
            this.rolename=rolepermission.rolename;
            this.UpdateTime=rolepermission.UpdateTime;
        }
    }
}
