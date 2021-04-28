package com.yolo.wz.redisdemo.expire;

import redis.clients.jedis.Jedis;

/**
 * 支持超时的分布式锁
 */
public class TimeOutDistributedLockDemo {
    private static Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 加锁
     */
    public Boolean lock(String key, String value, int timeOut) {
        Long setnx = jedis.setnx(key, value);
        jedis.expire(key, timeOut);
        return setnx > 0;
    }

    /**
     * 释放锁
     */
    public void ublock(String key) {
        jedis.del(key);
    }

    /**
     * 获取值
     */
    public String getValue(String key) {
        return jedis.get(key);
    }

    public static void main(String[] args) throws InterruptedException {
        TimeOutDistributedLockDemo demo = new TimeOutDistributedLockDemo();

        demo.lock("test_lock", "test_value", 10);
        System.out.println("第一次加锁获取的值" + demo.getValue("test_lock"));

        Boolean lock = demo.lock("test_lock", "test_value", 10);
        System.out.println("第二次加锁结果：" + (lock ? "成功" : "失败"));
        Thread.sleep(12 * 1000);
        String task_key = demo.getValue("task_key");
        System.out.println("数据是否过期？" + ((task_key == null || task_key.equals("")) ? "是" : "否"));
        if (task_key == null || task_key.equals("")) {
            demo.lock("test_lock", "test_value", 10);
        }
        String task_lock = demo.getValue("test_lock");
        System.out.println("数据是否过期？" + ((task_lock == null || task_lock.equals("")) ? "是" : "否"));
        System.out.println("删除前：" + demo.getValue("test_lock"));
        demo.ublock("test_lock");
        System.out.println("删除后：" + demo.getValue("test_lock"));
    }
}
