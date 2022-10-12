package com.example.wanchengdemo.entity;


public class Project {

  private long pid;
  private String pname;
  private long ponwerid;
  private String powner;


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public long getPonwerid() {
    return ponwerid;
  }

  public void setPonwerid(long ponwerid) {
    this.ponwerid = ponwerid;
  }


  public String getPowner() {
    return powner;
  }

  public void setPowner(String powner) {
    this.powner = powner;
  }

}
