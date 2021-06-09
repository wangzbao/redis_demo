package com.yolo.wz.redisdemo;

import com.sun.corba.se.spi.ior.ObjectId;
import com.yolo.wz.redisdemo.domain.Parent;
import com.yolo.wz.redisdemo.domain.Result;
import com.yolo.wz.redisdemo.domain.Son;
import org.springframework.beans.BeanUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("测试git上传");
        String key = "key is core data {0}";
        String format = MessageFormat.format(key,123456);
        HashMap<Object, String> map = new HashMap<>();
        System.out.println(map.get("key"));
        System.out.println(format);
    }
}
