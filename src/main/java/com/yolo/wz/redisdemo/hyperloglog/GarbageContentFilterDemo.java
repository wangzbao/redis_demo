package com.yolo.wz.redisdemo.hyperloglog;

import redis.clients.jedis.Jedis;

/**
 * 垃圾内容过滤
 */
public class GarbageContentFilterDemo {
    private Jedis jedis = new Jedis("127.0.0.1");


    /**
     * 判断当前内容是否是垃圾内容
     */
    public boolean isGarbage(String content) {
        return jedis.pfadd("hyperloglog_content", content) == 0;
    }

    public static void main(String[] args) {
        GarbageContentFilterDemo demo = new GarbageContentFilterDemo();
        String content = "正常内容1";
        System.out.println("是否是垃圾内容" + (demo.isGarbage(content) ? "是" : "否"));

        String content1 = "垃圾内容1";
        System.out.println("是否是垃圾内容" + (demo.isGarbage(content1) ? "是" : "否"));

        String content2 = "垃圾内容1";
        System.out.println("是否是垃圾内容" + (demo.isGarbage(content2) ? "是" : "否"));
    }
}
