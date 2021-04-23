package com.yolo.wz.redisdemo.set;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 网站每日访问的用户量
 */
public class UVdemo {

    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加用户访问记录
     */
    public void addUserAccess(long userId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());
        jedis.sadd("user_access_set" + today, String.valueOf(userId));
    }

    /**
     * 获取当天UV的值
     */
    public Long getUV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());
        Long scard = jedis.scard("user_access_set" + today);
        return scard;
    }

    public static void main(String[] args) {
        UVdemo demo = new UVdemo();
        for (int i = 0; i < 188; i++) {
            long userId = i + 1;
            //每人访问十次
            for (int j = 0; j < 10; j++) {
                demo.addUserAccess(userId);
            }
        }

        Long uv = demo.getUV();
        System.out.println("今天访问了" + uv + "次");
    }
}
