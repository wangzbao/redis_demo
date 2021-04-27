package com.yolo.wz.redisdemo.sortset;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * 推荐其他商品
 */
public class RecommendProductDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 继续购买商品
     */
    public void continuePruchase(long productId, long nextProductId) {
        jedis.zadd("continue_pruchase_product::" + productId, 1, String.valueOf(nextProductId));
    }

    public Set<Tuple> recommendProduct(long productId) {
        return jedis.zrevrangeWithScores("continue_pruchase_product::" + productId, 0, 2);
    }

    public static void main(String[] args) {
        RecommendProductDemo demo = new RecommendProductDemo();
        int productId = 1;
        for (int i = 0; i < 20; i++) {
            demo.continuePruchase(productId, i + 2);
        }

        for (int i = 0; i < 3; i++) {
            demo.continuePruchase(productId, i + 2);
        }

        Set<Tuple> tuples = demo.recommendProduct(productId);
        System.out.println("推荐购买的新商品是：" + tuples.toString());
    }
}
