package com.example.wanchengdemo.controller.data;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.data.Project;
import com.example.wanchengdemo.entity.data.Section;
import com.example.wanchengdemo.entity.data.Segment;
import com.example.wanchengdemo.entity.data.Site;
import com.example.wanchengdemo.service.data.ProjectService;
import com.example.wanchengdemo.service.data.SectionService;
import com.example.wanchengdemo.service.data.SegmentService;
import com.example.wanchengdemo.service.data.SiteService;
import com.example.wanchengdemo.util.data.ExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class DownloadFileController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private SiteService siteService;


    /**
     *
     * @param response  response
     * @param segmentid 检测段落                                                                                                                                                 id
     * @param siteid    检测点id
     * @return
     */

    @GetMapping("/file")
    public R<String> download(HttpServletResponse response,String projectidList ,String sectionid,String segmentid, String siteid) {

/*        for (String projectid : projectidList) {
            if (StringUtils.isNotEmpty(projectid)){
                LambdaQueryWrapper<Project> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
                projectLambdaQueryWrapper.eq(Project::getPid,projectid);

                String pid = projectService.getOne(projectLambdaQueryWrapper).getPid();
                log.info(pid);
            }
        }*/

        List<Map<String, Object>> dataList = null;

        //section条件构造
        LambdaQueryWrapper<Section> sectionLambdaQueryWrapper = new LambdaQueryWrapper<>();

        //segment条件构造
        LambdaQueryWrapper<Segment> segmentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        segmentLambdaQueryWrapper.eq(StringUtils.isNotEmpty(sectionid),Segment::getSegsid,sectionid);   //将 sid 作为查询条件
        segmentLambdaQueryWrapper.eq(StringUtils.isNotEmpty(segmentid),Segment::getSegid,segmentid);    //将segmentid作为查询条件

        //site条件构造
        LambdaQueryWrapper<Site> siteLambdaQueryWrapper = new LambdaQueryWrapper<>();
        siteLambdaQueryWrapper.eq(StringUtils.isNotEmpty(siteid),Site::getSiteid,siteid);




//------section-导出---------------------------------------------------------------------------------------------------------------------------------------------

        List<Site> Flist = new ArrayList<Site>();    //:存放循环内site数据
        if (StringUtils.isNotEmpty(sectionid)){
            sectionLambdaQueryWrapper.eq(Section::getSid,sectionid);    //条件构造
            //查询section下属segment
            List<Segment> segmentList = segmentService.list(segmentLambdaQueryWrapper);
            for (Segment segment : segmentList) {
                //获得segmentID
                String segmentFId = segment.getSegid();
                //查询所属site
                Site siteServiceOne = null;

                siteLambdaQueryWrapper.eq(Site::getSitesid,segmentFId); //添加循环内segmentId条件
                List<Site> FsiteList = siteService.list(siteLambdaQueryWrapper);


                Flist.addAll(FsiteList);

            }

        }


//        Section section = sectionService.getOne(sectionLambdaQueryWrapper);
//        List<Section> sectionList = sectionService.list(sectionLambdaQueryWrapper);

//------segment-导出--------------------------------------------------------------------------------------------------------------------------------------------
        List<Segment> list = segmentService.list(segmentLambdaQueryWrapper);



//------site-数据-导出--------------------------------------------------------------------------------------------------------------------------------------------

        //当传入参数包含segmentid时，导出相应检测段下所有检测点数据
        siteLambdaQueryWrapper.eq(StringUtils.isNotEmpty(segmentid),Site::getSitesid,segmentid);
        //传入单独siteid时，仅导出该检测点
        List<Site> logList = siteService.list(siteLambdaQueryWrapper);
        //当传入参数包含sectionid时，导出相应合同段下所有检测点数据
        if (Flist  != null){
            logList = Flist;

        }else {
            log.info("无section数据");
        }


        //site数据写入

        //       List<Map<String, Object>> dataList = null;

//        List<Site> logList = siteService.list(siteLambdaQueryWrapper);// 查询到要导出的信息  site

        if (logList.size() == 0) {
            R.error("无数据导出");
        }

        String sTitle = "检测点id,桩号,车道,左侧初读数,左侧终读数,左侧弯沉值,右侧初读数,右侧终读数,右侧弯沉值,左侧支点修正系数,右侧支点修正系数,温度修正,备注,外键,更新人,更新时间,创建时间";
        String fName = "log_";
        String mapKey = "siteid,sitecode,sitelane,lamx,lmin,deflection1,rmax,rmin,deflection2,lfindex,rlfindex,tindex,siteremark,sitesid,modifiebdy,update_time,createDate";
        dataList = new ArrayList<>();
        Map<String, Object> map = null;
        for (Site order : logList) {
            map = new HashMap<>();
            map.put("siteid", order.getSiteid());
            map.put("sitecode", order.getSitecode());
            map.put("sitelane", order.getSitelane());
            map.put("lamx", order.getLmax());
            map.put("lmin", order.getLmin());
            map.put("deflection1", order.getDeflectio1());
            map.put("rmax", order.getRmax());
            map.put("rmin", order.getRmin());
            map.put("deflection2", order.getDeflectio2());
            map.put("lfindex", order.getLfindex());
            map.put("rlfindex", order.getRfindex());
            map.put("tindex", order.getTindex());
            map.put("siteremark", order.getSiteremark());
            map.put("sitesid", order.getSitesid());
            map.put("modifiebdy", order.getModifiedby());
            map.put("update_time", order.getUpdateTime());
            map.put("createDate", DateFormatUtils.format(order.getCreateTime(), "yyyy/MM/dd HH:mm"));
            dataList.add(map);
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(fName, response);
            ExportUtil.doExport(dataList, sTitle, mapKey, os);
            return null;
        } catch (Exception e) {
            log.error("生成csv文件失败", e);
        }
        return R.error("数据导出出错");
    }
}