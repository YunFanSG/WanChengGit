package com.example.wanchengdemo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Test;

import java.util.List;

public class CsvTest {
    public static void main(String[] args) {
        //指定路径和编码
        CsvWriter writer = CsvUtil.getWriter("D:\\OutStoreg\\testWrite.csv", CharsetUtil.CHARSET_UTF_8);
        //按行写出
        writer.write(
                new String[] {"a1", "b1", "c1"},
                new String[] {"a2", "b2", "c2"},
                new String[] {"a3", "b3", "c3"}
        );
    }

    @Test
    public void csvRead(){
        CsvReader reader = CsvUtil.getReader();
        CsvData data    = reader.read(FileUtil.file("D:\\OutStoreg\\testWrite.csv"));
        List<CsvRow> rows = data.getRows();

        for (CsvRow row : rows) {
            Console.log(row.getRawList());
        }
    }

    @Test
    public void outTest(){
        //指定路径和编码
        CsvWriter writer = CsvUtil.getWriter("D:\\OutStoreg\\testWrite.csv", CharsetUtil.CHARSET_GBK);
        String[] title = {"检测点id","检测点码","车道","左侧初读数","左侧终读数","左侧弯沉值","左侧初读数","右侧终读数","右侧弯沉值",
                "左侧支点修正系数","右侧支点修正系数","温度修正","备注","外键","更新人","更新时间","创建时间"};
        System.out.println(title.length);
        //按行写出
        writer.write(
                title
        );
    }


}
