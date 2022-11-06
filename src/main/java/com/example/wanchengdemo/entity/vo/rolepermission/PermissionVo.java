package com.example.wanchengdemo.entity.vo.rolepermission;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.example.wanchengdemo.entity.bo.rolepermission.RoleBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.wanchengdemo.domain.Permission;



@Data
@AllArgsConstructor
public class PermissionVo extends Permission {
    private List<RoleBo> RoleBoList;

    public PermissionVo(Permission permission) {
        super(permission);
    }
}
