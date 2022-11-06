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
@TableName(value = "site")
public class Site{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;
    private Integer deflectio1;
    private Integer deflectio2;
    private Float lfindex;
    private Integer lmax;
    private Integer lmin;
    private String modifiedby;
    private Float rfindex;
    private Integer rmax;
    private Integer rmin;
    private String sitecode;
    @TableId(type = IdType.ID_WORKER_STR)
    private String siteid;
    private String sitelane;
    private String siteremark;
    private String sitesid;
    private Float tindex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;
    
    public Site(Site site) {
        if (Objects.nonNull(site)) {
            this.CreateTime=site.CreateTime;
            this.deflectio1=site.deflectio1;
            this.deflectio2=site.deflectio2;
            this.lfindex=site.lfindex;
            this.lmax=site.lmax;
            this.lmin=site.lmin;
            this.modifiedby=site.modifiedby;
            this.rfindex=site.rfindex;
            this.rmax=site.rmax;
            this.rmin=site.rmin;
            this.sitecode=site.sitecode;
            this.siteid=site.siteid;
            this.sitelane=site.sitelane;
            this.siteremark=site.siteremark;
            this.sitesid=site.sitesid;
            this.tindex=site.tindex;
            this.UpdateTime=site.UpdateTime;
        }
    }
}
