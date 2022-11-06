package com.example.wanchengdemo.commom;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import javax.annotation.PostConstruct;

public class IdGetSnowflake {

    /*
    * 雪花算法，生成不重复id
    *
    * */

    //机器码
    private long workId = 0;
    //数据中心码
    private long datacenterId = 0;
    private Snowflake snowflake = IdUtil.createSnowflake(workId,datacenterId);


    @PostConstruct
    public void  init(){
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            System.out.println("当前机器workId"+workId);
        }catch (Exception e){
            workId = NetUtil.getLocalhostStr().hashCode();
            e.printStackTrace();
        }


    }

    public synchronized long snowflakeId (){
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();
        for (int i = 0;i<50;i++){
            System.out.println(idGetSnowflake.snowflake.nextId());
        }
    }





}
