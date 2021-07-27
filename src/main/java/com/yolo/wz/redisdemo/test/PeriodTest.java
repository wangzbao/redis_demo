package com.yolo.wz.redisdemo.test;

import com.yolo.wz.redisdemo.domain.PeriodEnum;
import com.yolo.wz.redisdemo.domain.User;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class PeriodTest {

    public static void main(String[] args) {
//        Integer period = 9;
//        PeriodEnum[] values = PeriodEnum.values();
//        Set<PeriodEnum> collect = Arrays.stream(values).filter(value -> value
//                .getLabel().equals(period)).collect(Collectors.toSet());
//        if (CollectionUtils.isEmpty(collect)) {
//            System.out.println("抛出异常");
//        } else {
//            System.out.println("正常");
//        }
//        User user = new User();
//        System.out.println(user.getAge());
//        if (0 == user.getAge()) {
//            System.out.println("111");
//        }
//        List<User> list = new ArrayList<>();
//        Map<Integer, List<String>> map = list.stream().collect(
//                Collectors.groupingBy(obj -> obj.getAge(), Collectors.mapping(obj -> obj.getName(), Collectors.toList())));
        System.out.println(500 % 500);
        System.out.println(999 % 500);

        int i = 0;
        Integer n = null;
        Integer m = null;
        User user = new User();
        Integer j = null == user.getAge() && null == user.getPage() ? null : user.getAge() + user.getPage();
//        int x = m + n;
        System.out.println(
                "1".equals(user.getLike())
                        ? "是" : "否");
        System.out.println("1".equals(user.getName()));
        System.out.println(user.getName().equals("1"));

        Integer total = null;
        Integer item = null;
    }
}
