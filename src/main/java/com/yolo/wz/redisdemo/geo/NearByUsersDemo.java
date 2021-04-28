package com.yolo.wz.redisdemo.geo;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找附近的人案例
 */
public class NearByUsersDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加一个地理位置
     */
    public void addLoacation(String name, double longitude, double latitude) {
        jedis.geoadd("location_data", longitude, latitude, name);
    }

    /**
     * 查询附近的人
     */
    public List<GeoRadiusResponse> getNearByShop() {
        return jedis.georadiusByMember("location_data", "张三", 5, GeoUnit.KM);
    }


    public static void main(String[] args) {
        NearByUsersDemo demo = new NearByUsersDemo();
        List<GeoRadiusResponse> nears = demo.getNearByShop();
        List<String> list = new ArrayList<>();
        for (GeoRadiusResponse response : nears) {
            String member = response.getMemberByString();
            if (!member.equals("张三")) {
                list.add(member);
            }
        }

        System.out.println("附近5公里内的：" + list);
    }
}
