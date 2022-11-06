package com.example.wanchengdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName(value = "permission")
public class Permission{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;
    private String modifiedby;
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String permissionname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;
    
    public Permission(Permission permission) {
        if (Objects.nonNull(permission)) {
            this.CreateTime=permission.CreateTime;
            this.modifiedby=permission.modifiedby;
            this.id=permission.id;
            this.permissionname=permission.permissionname;
            this.UpdateTime=permission.UpdateTime;
        }
    }
}
