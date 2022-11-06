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
@TableName(value = "role")
public class Role{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;
    private String modifiedby;
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String rolename;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;
    
    public Role(Role role) {
        if (Objects.nonNull(role)) {
            this.CreateTime=role.CreateTime;
            this.modifiedby=role.modifiedby;
            this.id=role.id;
            this.rolename=role.rolename;
            this.UpdateTime=role.UpdateTime;
        }
    }
}
