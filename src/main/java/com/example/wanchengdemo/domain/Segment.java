package com.example.wanchengdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "segment")
public class Segment{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateTime;
    private String modifiedby;
    private String roadhandle;
    private String roadway;
    private String segdate;
    private String segdesign;
    @TableId(type = IdType.ID_WORKER_STR)
    private String segid;
    private String segrange;

    private String segsid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateTime;
    
    public Segment(Segment segment) {
        if (Objects.nonNull(segment)) {
            this.CreateTime=segment.CreateTime;
            this.modifiedby=segment.modifiedby;
            this.roadhandle=segment.roadhandle;
            this.roadway=segment.roadway;
            this.segdate=segment.segdate;
            this.segdesign=segment.segdesign;
            this.segid=segment.segid;
            this.segrange=segment.segrange;
            this.segsid=segment.segsid;
            this.UpdateTime=segment.UpdateTime;
        }
    }
}
