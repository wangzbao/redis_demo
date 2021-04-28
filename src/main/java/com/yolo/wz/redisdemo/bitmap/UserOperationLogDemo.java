package com.yolo.wz.redisdemo.bitmap;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户行为操作日志
 */

public class UserOperationLogDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加行为日志
     */
    public void recordUserOperationLog(String operation, long userId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());

        jedis.setbit("operation::" + operation + "::" + today + "::log",
                userId, String.valueOf(1));
    }

    /**
     * 判断用户今天是否执行过某个操作
     */
    public boolean hasOperation(String operation, long userId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());

        return jedis.getbit("operation::" + operation + "::" + today + "::log", userId);
    }

    public static void main(String[] args) {
        UserOperationLogDemo demo = new UserOperationLogDemo();
        demo.recordUserOperationLog("操作1",110);

        System.out.println("是否执行过操作："+(demo.hasOperation("操作1",110)?"是":"否"));
        System.out.println("是否执行过操作："+(demo.hasOperation("操作1",112)?"是":"否"));
    }
}
