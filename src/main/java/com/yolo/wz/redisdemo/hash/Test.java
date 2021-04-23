package com.yolo.wz.redisdemo.hash;


import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;

public class Test {

    private Jedis jedis = new Jedis("127.0.0.1");
    public static void main(String[] args) {

        Test test = new Test();
        Jedis jedis = test.jedis;
        System.out.println(jedis.getClient().getHost());

        System.out.println("服务正在运行：" + jedis.ping());

        HashMap<String, String> zhangsan = new HashMap<>();
        zhangsan.put("name","张三");
        zhangsan.put("age","18");
        HashMap<String, String> lisi = new HashMap<>();
        lisi.put("name","李四");
        lisi.put("age","14");
        jedis.hmset("student",zhangsan);
        jedis.hmset("student",lisi);
        List<String> hmget = jedis.hmget("student", "name");
        System.out.println(hmget);

        long shoot = 1L;
        shoot = shoot/36;
        System.out.println(shoot);
    }
}
