package com.example.wanchengdemo.util.data;



import com.example.wanchengdemo.entity.data.Section;
import com.example.wanchengdemo.entity.data.Segment;
import com.example.wanchengdemo.entity.data.Site;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TxtFileRead {
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
/*    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args){
//        File file = new File("C:\\Users\\young\\OneDrive\\桌面\\弯沉\\测试数据\\赫六6标.txt");
          File file = new File("D:\\歌词\\江湖流.txt");
        System.out.println(txt2String(file));
    }*/
    //读取文件
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    //section清洗
    public static Section sectionClean(String sourceData){

        Section section = new Section();
        Site site    = new Site();
        //正则表达式

        //获得检测合同
        String regSection = "文件名.*弯沉测试数据.(.*).txt";
        //获得施工单位
        String regScons   ="施工单位：(.*)";
        //获得检测单位
        String regTesting= "检测人员：(.*)";

        //section清洗
        String[] sectionReg = {regSection,regScons,regTesting};
        //计时器
        int i = 1;

        for (String reg : sectionReg) {


            Pattern pattern = Pattern.compile (reg);
            Matcher matcher = pattern.matcher (sourceData);

            while (matcher.find()){
                System.out.println(matcher.group(1));

                //i = 1时，将 合同段 填入section对象， i = 2时，填入 施工单位 ,i = 3时，填入 检测单位
                if (i==1){
                    section.setSname(matcher.group(1));

                }
                if(i==2){
                    section.setScons(matcher.group(1));
                }
                if (i == 3){
                    section.setStesting(matcher.group(1));
                }
                i++;
            }
        }

        return section;
    }

    //segment清洗
    public static Segment segmentClean(String sourceData){

        Segment segment = new Segment();

        //获得检测段落
        String regRange= "检测段落：([a-zA-Z0-9+-]*)";
        //获得检测日期
        String regDate= "检测日期：(.*)";
        //获得路幅
        String regRoadWay = "路幅：([左右]幅)";
        //获得桩号处理
        String regRoadHandle = "桩号处理：([顺逆]桩号)";
        //获得交工验收弯沉值
        String regDesign= "交工验收弯沉值（0.01mm）：([0-9]*)";
        //获得路面温度
        String regPavement_tp = "路面温度（°C）：([0-9]*)";

        String[] regSegment = {regRange,regDate,regRoadWay,regRoadHandle,regDesign,regPavement_tp};
        //计时器
        int i = 1;

        for (String reg : regSegment) {
            Pattern pattern = Pattern.compile (reg);
            Matcher matcher = pattern.matcher (sourceData);

            while (matcher.find()){

                //i = 1时，将 检测段 填入 segment 对象， i = 2时，填入 检测日期 ,i = 3时，填入 路幅, i = 4 时，填入 桩号处理 .... 交工验收弯沉值 路面温度

                if (i == 1){
                    segment.setSegrange(matcher.group(1));
                }
                if (i ==2 ){
                    segment.setSegdate(matcher.group(1));
                }
                if (i ==3 ){
                    segment.setRoadway(matcher.group(1));
                }
                if (i == 4){
                    segment.setRoadhandle(matcher.group(1));
                }
                if (i ==5 ){
                    segment.setSegdesign(matcher.group(1));
                }
                if (i == 6){
                    segment.setPavement_tp(matcher.group(1));
                }
                i++;
            }
        }


        return segment;
    }


    //site清洗
    public static List siteClean(String sourceData){
        //获得弯沉数据
        String regData= "([A-Z][0-9]{4}[+][0-9]{3}\t[m]\t[0-9]*\t[0-9]*\t[0-9]*\t[0-9]*\t[0-9]*\t[0-9]*)";

        Pattern pattern = Pattern.compile (regData);
        Matcher matcher = pattern.matcher (sourceData);

        List siteList = new ArrayList();

        while (matcher.find()) {

            System.out.println(matcher.group(1));


            siteList.add(matcher.group(1));

        }


        return siteList;
    }


    public static void main(Site site){
        File file = new File("D:\\Project\\source\\赫六5标2.txt");

        String data = txt2String(file);


        //获得检测合同
        String regSection = "文件名.*弯沉测试数据.(.*).txt";
        //获得检测日期
        String regDate= "检测日期：(.*)";
        //获得检测人员
        String regMan= "检测人员：(.*)";
        //获得检测段落
        String regSegment= "检测段落：([a-zA-Z0-9+-]*)";
        //获得路幅
        String regRoadWay = "路幅：([左右]幅)";
        //获得桩号处理
        String regRoadHandle = "桩号处理：([顺逆]桩号)";
        //获得交工验收弯沉值
        String regDesign= "交工验收弯沉值（0.01mm）：([0-9]*)";
        //获得路面温度
        String regPavement_tp = "路面温度（°C）：([0-9]*)";
        //获得弯沉数据
        String regData= "([A-Z][0-9]{4}[+][0-9]{3}\t[m]\t[0-9]*\t[0-9]*\t[0-9]*\t[0-9]*\t[0-9]*\t[0-9]*)";

        String[] regArgs = {regSection,regDate,regMan,regSegment,regRoadWay,regRoadHandle,regDesign,regPavement_tp};

        for (String reg : regArgs) {
            Pattern pattern = Pattern.compile (reg);
            Matcher matcher = pattern.matcher (data);

            while (matcher.find()){
                System.out.println(matcher.group(1));

            }
        }


    }




}

