package com.yolo.wz.redisdemo.hyperloglog;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于hyperloglog实现uv统计
 */
public class HyperloglogDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 初始化UV数据
     */
    public void initUVData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());

        for (int i = 0; i < 1256; i++) {
            jedis.pfadd("hyperloglog_uv_" + today, String.valueOf(i + 1));
        }
    }

    /**
     * 获取UV的值
     */
    public long getUV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());
        return jedis.pfcount("hyperloglog_uv_" + today);
    }

    public static void main(String[] args) {
        HyperloglogDemo demo = new HyperloglogDemo();
        demo.initUVData();

        System.out.println("UV是：" + demo.getUV());
    }
}
