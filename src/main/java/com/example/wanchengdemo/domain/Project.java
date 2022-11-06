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
@TableName(value = "project")
public class Project{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;
    private String modifiedby;
    @TableId(type = IdType.ID_WORKER_STR)
    private String pid;
    private String pname;
    private String powner;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;
    
    public Project(Project project) {
        if (Objects.nonNull(project)) {
            this.CreateTime=project.CreateTime;
            this.modifiedby=project.modifiedby;
            this.pid=project.pid;
            this.pname=project.pname;
            this.powner=project.powner;
            this.UpdateTime=project.UpdateTime;
        }
    }
}
