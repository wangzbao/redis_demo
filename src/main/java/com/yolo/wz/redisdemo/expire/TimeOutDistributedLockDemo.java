package com.yolo.wz.redisdemo.expire;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

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
     * 此时需要校验是否是自己加的锁，-->身份校验
     */
    public Boolean ublock(String key, String value) {
//        jedis.del(key);
        Pipeline pipeline = jedis.pipelined();
        String response = jedis.get(key);

        try {
            pipeline.watch(key);

            if (response == null || response.equals("") || response.equals("")
                    || response.equals("null")) {
                return true;
            }

            if (response.equals(value)) {
                pipeline.multi();
                pipeline.del(key);
                pipeline.exec();

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            pipeline.close();
        }
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
        demo.ublock("test_lock","test_error");
        System.out.println("不是本人删除后：" + demo.getValue("test_lock"));

        demo.ublock("test_lock","test_value");
        System.out.println("是本人删除后：" + demo.getValue("test_lock"));
    }
}
