package com.example.wanchengdemo.entity.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.example.wanchengdemo.domain.Segment;

@Data
public class SegmentVo extends Segment {
    private static final long serialVersionUID = 1L;
    private String sname;
    private String spid;
    private String scons;

    public SegmentVo() {
    }


    public SegmentVo(Segment segment) {super(segment);
    }
}
