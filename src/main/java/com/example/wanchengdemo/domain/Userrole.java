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
@TableName(value = "userrole")
public class Userrole{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;
    private String modifiedby;

    private String roleid;
    private String rolename;
    private String uid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;
    private String username;
    
    public Userrole(Userrole userrole) {
        if (Objects.nonNull(userrole)) {
            this.CreateTime=userrole.CreateTime;
            this.modifiedby=userrole.modifiedby;
            this.roleid=userrole.roleid;
            this.rolename=userrole.rolename;
            this.uid=userrole.uid;
            this.UpdateTime=userrole.UpdateTime;
            this.username=userrole.username;
        }
    }
}
