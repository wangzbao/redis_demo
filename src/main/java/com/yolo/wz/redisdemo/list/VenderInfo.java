package com.yolo.wz.redisdemo.list;

import lombok.Data;

@Data
public class VenderInfo {
    private String venderName;
    private Integer smsCount;
    private Integer ddCount;
}
