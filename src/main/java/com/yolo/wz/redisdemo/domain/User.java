package com.yolo.wz.redisdemo.domain;

import lombok.Data;

@Data
public class User {
    private String name;
    private String like;
    private Integer age;
    private Integer page;
    private Integer pageNum;

}
