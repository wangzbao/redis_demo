package com.yolo.wz.redisdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
//@JsonIgnoreProperties(value = "{like}")
public class User {
    private String name;
    @JsonIgnore
    private String like;
    private Integer age;
    private Integer page;
    private Integer pageNum;
    private PeriodEnum periodEnum;

}
