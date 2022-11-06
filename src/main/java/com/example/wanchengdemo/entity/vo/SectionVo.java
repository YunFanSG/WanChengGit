package com.example.wanchengdemo.entity.vo;
import com.example.wanchengdemo.domain.Section;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.example.wanchengdemo.domain.Segment;

@Data
public class SectionVo extends Section {
    private static final long serialVersionUID = 1L;
    private List<Segment> segmentList;

    public SectionVo() {
    }

    public SectionVo(Section section) {
        super(section);
    }




}
