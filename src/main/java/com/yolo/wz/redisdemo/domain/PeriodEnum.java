package com.yolo.wz.redisdemo.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PeriodEnum {
    PERIOD_ONE(1, "24小时"),
    PERIOD_SEVEN(2, "7天"),
    PERIOD_FIFTEEN(3, "15天");

    private final Integer label;
    private final String desc;

    public Integer getLabel() {
        return label;
    }

    public String getDesc() {
        return desc;
    }
}
