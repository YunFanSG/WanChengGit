package com.example.wanchengdemo.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 7021223076084300328L;
    private String uid;

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String pwd;
    private String department;
    private String phone;
    private Date createTime;
    private Date updateTime;
}
