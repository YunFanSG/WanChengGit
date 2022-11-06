package com.example.wanchengdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;


;
import com.example.wanchengdemo.util.BeanCopyUtils;
import com.example.wanchengdemo.util.EntityUtils;
import xin.altitude.cms.common.util.SpringUtils;
import com.example.wanchengdemo.domain.Section;
import com.example.wanchengdemo.domain.Segment;
import com.example.wanchengdemo.entity.vo.SegmentVo;



import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SegmentService extends IService<Segment> {
    SectionService sectionService= SpringUtils.getBean(SectionService.class);

    /**查询用户实体类Vo*/
    default SegmentVo getOneVo(String sid) {
        SegmentVo segmentVo = EntityUtils.toObj(getById(sid), SegmentVo::new);
        Section section = sectionService.getById(segmentVo.getSegsid());
        BeanCopyUtils.copyProperties(section, segmentVo);
        return segmentVo;
    }
    /**查询实体类Vo列表*/
    default List<SegmentVo> listVo(Segment segment) {
        List<SegmentVo> segmentVoList = EntityUtils.toList(list(Wrappers.lambdaQuery(segment)), SegmentVo::new);
        Set<String> sid = EntityUtils.toSet(segmentVoList, SegmentVo::getSegsid);
        Map<String, Section> map = EntityUtils.toMap(sectionService.listByIds(sid), Section::getSid, e -> e);
        segmentVoList.forEach(e -> BeanCopyUtils.copyProperties(map.get(e.getSegsid()), e));
        return segmentVoList;
    }
    /**分页查询实体类Vo*/
    default IPage<SegmentVo> pageVo(IPage<Segment> page, Segment segment) {
        IPage<SegmentVo> segmentVoPage = EntityUtils.toPage(page(page, Wrappers.lambdaQuery(segment)), SegmentVo::new);
        Set<String> sid = EntityUtils.toSet(segmentVoPage.getRecords(), SegmentVo::getSegsid);
        Map<String, Section> map = EntityUtils.toMap(sectionService.listByIds(sid), Section::getSid, e -> e);
        segmentVoPage.getRecords().forEach(e -> BeanCopyUtils.copyProperties(map.get(e.getSegsid()), e));
        return segmentVoPage;
    }




}
