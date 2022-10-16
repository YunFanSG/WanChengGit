package com.example.wanchengdemo.entity;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

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


  public String getToken(User user) {
    return JWT.create().withAudience(user.getUsername())
            .sign(Algorithm.HMAC256(user.getPwd()));
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
