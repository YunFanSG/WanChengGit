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
@TableName(value = "section")
public class Section{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;
    private String modifiedby;
    private String scons;
    @TableId(type = IdType.ID_WORKER_STR)
    private String sid;
    private String sname;
    private String spid;
    private String stesting;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;
    
    public Section(Section section) {
        if (Objects.nonNull(section)) {
            this.CreateTime=section.CreateTime;
            this.modifiedby=section.modifiedby;
            this.scons=section.scons;
            this.sid=section.sid;
            this.sname=section.sname;
            this.spid=section.spid;
            this.stesting=section.stesting;
            this.UpdateTime=section.UpdateTime;
        }
    }
}
