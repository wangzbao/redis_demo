package com.yolo.wz.redisdemo.sortset;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Date;
import java.util.Set;

/**
 * 新闻推荐机制
 */
public class NewsDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加新闻
     */
    public void addNews(long newsId, long timeStamp) {
//        jedis.zadd("news", new Date().getTime(), String.valueOf(newsId));
        jedis.zadd("news", timeStamp, String.valueOf(newsId));
    }

    /**
     * 搜索新闻
     */
    public Set<Tuple> getNews(long maxTimeStamp, long minTimeStamp, int index, int count) {
        return jedis.zrevrangeByScoreWithScores("news", maxTimeStamp, minTimeStamp, index, count);
    }

    public static void main(String[] args) {
        NewsDemo demo = new NewsDemo();

        for (int i = 0; i < 20; i++) {
            demo.addNews(i + 1, i + 1);
        }

        long maxTimeStamp = 18;
        long minTimeStamp = 2;
        int pageNumber = 1;
        int pageSize = 10;
        int startIndex = (pageNumber - 1) * 10;

        Set<Tuple> news = demo.getNews(maxTimeStamp, minTimeStamp, startIndex, pageSize);
        System.out.println(news);
    }

}
