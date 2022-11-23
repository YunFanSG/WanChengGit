package com.example.wanchengdemo.entity.data;


import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;

public class Site {
  @TableId
  private String siteid;
  private String sitecode;
  private String sitelane;
  private long lmax;
  private long lmin;
  private long deflectio1;
  private long rmax;
  private long rmin;
  private long deflectio2;
  private double lfindex;
  private double rfindex;
  private double tindex;
  private String siteremark;
  private String sitesid;
  private String modifiedby;

  private Timestamp UpdateTime;

  private Timestamp CreateTime;

  public String getSiteid() {
    return siteid;
  }

  public void setSiteid(String siteid) {
    this.siteid = siteid;
  }


  public String getSitecode() {
    return sitecode;
  }

  public void setSitecode(String sitecode) {
    this.sitecode = sitecode;
  }


  public String getSitelane() {
    return sitelane;
  }

  public void setSitelane(String sitelane) {
    this.sitelane = sitelane;
  }


  public long getLmax() {
    return lmax;
  }

  public void setLmax(long lmax) {
    this.lmax = lmax;
  }


  public long getLmin() {
    return lmin;
  }

  public void setLmin(long lmin) {
    this.lmin = lmin;
  }


  public long getDeflectio1() {
    return deflectio1;
  }

  public void setDeflectio1(long deflectio1) {
    this.deflectio1 = deflectio1;
  }


  public long getRmax() {
    return rmax;
  }

  public void setRmax(long rmax) {
    this.rmax = rmax;
  }


  public long getRmin() {
    return rmin;
  }

  public void setRmin(long rmin) {
    this.rmin = rmin;
  }


  public long getDeflectio2() {
    return deflectio2;
  }

  public void setDeflectio2(long deflectio2) {
    this.deflectio2 = deflectio2;
  }


  public double getLfindex() {
    return lfindex;
  }

  public void setLfindex(double lfindex) {
    this.lfindex = lfindex;
  }


  public double getRfindex() {
    return rfindex;
  }

  public void setRfindex(double rfindex) {
    this.rfindex = rfindex;
  }


  public double getTindex() {
    return tindex;
  }

  public void setTindex(double tindex) {
    this.tindex = tindex;
  }


  public String getSiteremark() {
    return siteremark;
  }

  public void setSiteremark(String siteremark) {
    this.siteremark = siteremark;
  }


  public String getSitesid() {
    return sitesid;
  }

  public void setSitesid(String sitesid) {
    this.sitesid = sitesid;
  }



  public String getModifiedby() {
    return modifiedby;
  }

  public void setModifiedby(String modifiedby) {
    this.modifiedby = modifiedby;
  }

  public Timestamp getUpdateTime() {
    return UpdateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    UpdateTime = updateTime;
  }

  public Timestamp getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(Timestamp createTime) {
    CreateTime = createTime;
  }
}
