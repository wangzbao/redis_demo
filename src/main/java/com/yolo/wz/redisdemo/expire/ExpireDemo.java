package com.yolo.wz.redisdemo.expire;

import redis.clients.jedis.Jedis;

/**
 * 自动过期的案例
 */
public class ExpireDemo {
    private static Jedis jedis = new Jedis("127.0.0.1");


    public static void main(String[] args) throws InterruptedException {
        jedis.set("task_key", "task_value");
        jedis.expire("task_key", 10);
        System.out.println(jedis.get("task_key"));
        Thread.sleep(12 * 1000);

        String task_key = jedis.get("task_key");
        System.out.println("数据是否过期？" + ((task_key == null || task_key.equals("")) ? "是" : "否"));
    }
}
