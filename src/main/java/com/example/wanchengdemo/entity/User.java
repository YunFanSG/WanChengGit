package com.example.wanchengdemo.entity;


public class User {

  private long uid;
  private String username;
  private String pwd;
  private String department;


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "User{" +
            "uid=" + uid +
            ", username='" + username + '\'' +
            ", pwd='" + pwd + '\'' +
            ", department='" + department + '\'' +
            '}';
  }
}
