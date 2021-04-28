package com.yolo.wz.redisdemo.geo;

import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

/**
 * 用户与商家的距离计算
 */
public class UserShopDistanceDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加一个地理位置
     */
    public void addLoacation(String name, double longitude, double latitude) {
        jedis.geoadd("location_data", longitude, latitude, name);
    }

    /**
     * 获取用户到商家的位置
     */
    public double getDistance(String user, String shop) {
        return jedis.geodist("location_data", user, shop, GeoUnit.KM);
    }

    public static void main(String[] args) {
        UserShopDistanceDemo demo = new UserShopDistanceDemo();
        demo.addLoacation("张三", 116.49428832615371, 39.8657532516626);
        demo.addLoacation("沙县小吃店", 116.4578216516191, 39.8887128761251);

        double distance = demo.getDistance("张三", "沙县小吃店");
        System.out.println("张三距离沙县小吃店为：" + distance);
    }
}
