package com.example.wanchengdemo.controller.data;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.wanchengdemo.commom.R;
import com.example.wanchengdemo.entity.data.Site;
import com.example.wanchengdemo.service.data.SegmentService;
import com.example.wanchengdemo.service.data.SiteService;
import com.example.wanchengdemo.util.data.ExportUtil;
import lombok.extern.slf4j.Slf4j;
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
    private SegmentService segmentService;

    @Autowired
    private SiteService siteService;

    @GetMapping("/file")
    public R<String> download(HttpServletResponse response,String segmentid, String siteid) {
        LambdaQueryWrapper<Site> siteLambdaQueryWrapper = new LambdaQueryWrapper<>();
        siteLambdaQueryWrapper.eq(Site::getSiteid,siteid);

        List<Map<String, Object>> dataList = null;
        List<Site> logList = siteService.list(siteLambdaQueryWrapper);// 查询到要导出的信息
        if (logList.size() == 0) {
            R.error("无数据导出");
        }
        String sTitle = "检测点id,桩号,车道,左侧初读数,左侧终读数,左侧弯沉值,右侧初读数,右侧终读数,右侧弯沉值,左侧支点修正系数,右侧支点修正系数,温度修正,备注,外键,更新人,更新时间,创建时间";
        String fName = "log_";
        String mapKey = "siteid,sitecode,sitelane,lamx,lmin,deflection1,rmax,rmin,deflection2,lfindex,rlfindex,tindex,siteremark,modifiebdy,createDate";
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