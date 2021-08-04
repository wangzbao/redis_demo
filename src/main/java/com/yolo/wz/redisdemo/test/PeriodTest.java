package com.yolo.wz.redisdemo.test;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.RoundingMode;

public class PeriodTest {

    public static void main(String[] args) {
        //980元5毛2分
//        Long dividend = 98052L;
        Long dividend = 4L;
        Integer divisor = 3;
        double chu = 3;
        long l = 0 == divisor ? 0 : NumberUtils.toScaledBigDecimal(dividend / divisor * 1d, 1, RoundingMode.HALF_EVEN).longValue();
        long l1 = 0 == divisor ? 0 : NumberUtils.toScaledBigDecimal(dividend / chu , 2, RoundingMode.HALF_DOWN).longValue();
        System.out.println(l);
        System.out.println(l1);
    }
}
