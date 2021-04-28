package com.yolo.wz.redisdemo.hyperloglog;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 网站日常数据统计
 */
public class WebsiteStatistDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 初始化某一天的UV数据
     */
    public void initUVData(String date) {
        Random random = new Random();
        int startIndex = random.nextInt(1000);
        System.out.println("今日访问uv起始Id是：" + startIndex);

        for (int i = 0; i < startIndex + 1256; i++) {
            for (int j = 0; j < 10; j++) {
                jedis.pfadd("hyperloglog_uv_" + date, String.valueOf(i + 1));
            }
        }
    }

    /**
     * 获取某一天的uv值
     */
    public long getUV(String date) {
        return jedis.pfcount("hyperloglog_uv_" + date);
    }

    /**
     * 获取周uv
     */
    public long getWeeklyUV() {
        List<String> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            String date = dateFormat.format(calendar.getTime());

            list.add("hyperloglog_uv_" + date);
        }
        String[] toArray = list.toArray(new String[list.size()]);
        jedis.pfmerge("weekly_uv", toArray);
        return jedis.pfcount("weekly_uv");
    }

    public static void main(String[] args) {
        WebsiteStatistDemo demo = new WebsiteStatistDemo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        long duplicate = 0;
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            String date = dateFormat.format(calendar.getTime());
            demo.initUVData(date);
            long uv = demo.getUV(date);
            System.out.println("日期为" + date + "的uv值是：" + uv);
            //重复的uv值
            duplicate += uv;
        }
        System.out.println("没去重的周活跃：" + duplicate);
        System.out.println("去重的周活跃：" + demo.getWeeklyUV());
    }
}
