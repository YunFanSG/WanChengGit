package com.example.wanchengdemo.entity.data;


import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;

public class Segment {
@TableId
  private String segid;
  private String segrange;
  private String segdesign;
  private String segdate;
  private String segsid;
  private String roadway;
  private String roadhandle;
  private String modifiedby;
  private String PavementTp;
//  private String scons;


  private Timestamp UpdateTime;

  private Timestamp CreateTime;


  public String getSegid() {
    return segid;
  }

  public void setSegid(String segid) {
    this.segid = segid;
  }

  public String getPavement_tp() {
    return PavementTp;
  }

  public void setPavement_tp(String pavement_tp) {
    this.PavementTp = pavement_tp;
  }

  public String getSegrange() {
    return segrange;
  }

  public void setSegrange(String segrange) {
    this.segrange = segrange;
  }


  public String getSegdesign() {
    return segdesign;
  }

  public void setSegdesign(String segdesign) {
    this.segdesign = segdesign;
  }


  public String getSegdate() {
    return segdate;
  }

  public void setSegdate(String segdate) {
    this.segdate = segdate;
  }


  public String getSegsid() {
    return segsid;
  }

  public void setSegsid(String segsid) {
    this.segsid = segsid;
  }


  public String getRoadway() {
    return roadway;
  }

  public void setRoadway(String roadway) {
    this.roadway = roadway;
  }


  public String getRoadhandle() {
    return roadhandle;
  }

  public void setRoadhandle(String roadhandle) {
    this.roadhandle = roadhandle;
  }

/*  public String getScons() {
    return scons;
  }

  public void setScons(String scons) {
    this.scons = scons;
  }*/

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
