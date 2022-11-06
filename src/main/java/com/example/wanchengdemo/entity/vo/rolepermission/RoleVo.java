package com.example.wanchengdemo.entity.vo.rolepermission;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


import com.example.wanchengdemo.domain.Role;
import com.example.wanchengdemo.entity.bo.rolepermission.PermissionBo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleVo extends Role {
    private List<PermissionBo> permissionBoList;

    public RoleVo(Role role) {
        super(role);
    }
}
