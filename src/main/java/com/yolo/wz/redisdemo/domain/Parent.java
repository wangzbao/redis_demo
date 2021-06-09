package com.yolo.wz.redisdemo.domain;

import lombok.Data;

@Data
public class Parent {
    private String p1;
    private String p2;
    private Son son;
}
