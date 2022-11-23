package com.example.wanchengdemo.entity.data;


import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;

public class Section {
  @TableId
  private String sid;
  private String sname;
  private String stesting;
  private String scons;
  private String spid;

  private String modifiedby;

  private Timestamp UpdateTime;

  private Timestamp CreateTime;


  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }


  public String getSname() {
    return sname;
  }

  public void setSname(String sname) {
    this.sname = sname;
  }


  public String getStesting() {
    return stesting;
  }

  public void setStesting(String stesting) {
    this.stesting = stesting;
  }


  public String getScons() {
    return scons;
  }

  public void setScons(String scons) {
    this.scons = scons;
  }


  public String getSpid() {
    return spid;
  }

  public void setSpid(String spid) {
    this.spid = spid;
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
