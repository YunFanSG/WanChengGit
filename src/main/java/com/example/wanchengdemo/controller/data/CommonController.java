package com.example.wanchengdemo.controller.data;

import com.example.wanchengdemo.commom.IdGetSnowflake;

import com.example.wanchengdemo.entity.data.Project;
import com.example.wanchengdemo.entity.data.Section;
import com.example.wanchengdemo.entity.data.Segment;
import com.example.wanchengdemo.entity.data.Site;
import com.example.wanchengdemo.service.data.SectionService;
import com.example.wanchengdemo.service.data.SegmentService;
import com.example.wanchengdemo.service.data.SiteService;

import com.example.wanchengdemo.util.data.TxtFileRead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    SectionService sectionService;

    @Autowired
    SegmentService segmentService;

    @Autowired
    SiteService siteService;


    /*
    * 文件上传
    *
    */
    //值在application.yml 中， 此 为文件上传 后保存路径
    @Value("${file.upload.path}")
    private String path;

    @GetMapping("/")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String create(@RequestPart MultipartFile file, Project project, Section section, Segment segment, Site site) throws IOException {
        //将文件保存至本地
        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;

        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());

        //读取本地文件
        String sourceData = TxtFileRead.txt2String(dest);

        //清洗 section 数据,传入pid
        //预设pid = 1584087247165063168
        project.setPid("1584087247165063168");
        Section sectionClean = TxtFileRead.sectionClean(sourceData);
        sectionClean.setSpid(project.getPid());

        //清洗 segment 数据
        Segment segmentClean = TxtFileRead.segmentClean(sourceData);

        //清洗 site 数据
        List siteClean = TxtFileRead.siteClean(sourceData);
        //System.out.println(siteClean);  //显示返回site信息



        //插入数据库


        //section导入,随后返回sectionid
        //雪花生成section主键
        IdGetSnowflake idGetSnowflake = new IdGetSnowflake();

        sectionClean.setSid(String.valueOf(idGetSnowflake.snowflakeId()));
        sectionService.save(sectionClean);

        //返回sectionId
        String sectionId = sectionClean.getSid();

        //segment导入,雪花生成id
        segmentClean.setSegid(String.valueOf(idGetSnowflake.snowflakeId()));
        segmentClean.setSegsid(sectionId);
        segmentService.save(segmentClean);

        //返回segmentId
        String segmentId = segmentClean.getSegid();

        //插入 site 数据
        for (Object object : siteClean) {
            //遍历返回的list,

            //转为String方便分割
            String objectString = String.valueOf(object);
            String[] siteData = objectString.split("\t");
            //计时器
            int i = 1;
            //System.out.println(objectString);

            site.setSiteid(String.valueOf(idGetSnowflake.snowflakeId()));
            //插入数据库
            for (String sitedata : siteData) {
                System.out.println("第"+i+"次遍历"+"值为："+sitedata);
                if (i == 1){
                    site.setSitecode(sitedata);
                }
                if (i == 2){
                    site.setSitelane(sitedata);
                }
                if (i == 3){
                    site.setLmax(Long.valueOf(sitedata));
                }
                if (i == 4){
                    site.setLmin(Long.valueOf(sitedata));
                }
                if (i == 5){
                    site.setDeflectio1(Long.valueOf(sitedata));
                }
                if (i == 6){
                    site.setRmax(Long.valueOf(sitedata));
                }
                if (i == 7){
                    site.setRmin(Long.valueOf(sitedata));
                }
                if (i == 8){
                    site.setDeflectio2(Long.valueOf(sitedata));
                }
                if (i == 9 ){
                    site.setSiteremark(sitedata);
                }
                i++;

                //截至lfindex
                /*if (i == 9){
                    site.setLfindex(Long.valueOf(sitedata));
                }
                if (i == 10){
                    site.setRfindex(Long.valueOf(sitedata));
                }*/

            }


            siteService.save(site);
        }


        return "Upload file success : " + dest.getAbsolutePath();
    }

    @RequestMapping("/download")
    public void Download(){

    }




    /*
    * 文件导入数据库
    * */
/*    @PostMapping("/up2Mysql")
    public R<String> fileUp2Mysql(String pid, Section section, Segment segment, Site site){

        String filePaht = "";
        File file = new File(filePaht);
        String txt2String = FileRead.txt2String(file);



        return null;
    }*/
}
