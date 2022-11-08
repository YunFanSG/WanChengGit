package com.example.wanchengdemo.entity.data;


import java.sql.Timestamp;

public class Project {

  private String pid;
  private String pname;
  private String powner;
  private String ownerid;
  private String modifiedby;


  private Timestamp UpdateTime;

  private Timestamp CreateTime;


  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public String getPowner() {
    return powner;
  }

  public void setPowner(String powner) {
    this.powner = powner;
  }


  public String getOwnerid() {
    return ownerid;
  }

  public void setOwnerid(String ownerid) {
    this.ownerid = ownerid;
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
