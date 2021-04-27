package com.yolo.wz.redisdemo.set;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 商品搜索
 */
public class ProductSearchDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加商品时候附带关键字
     */
    public void addProductKeyword(long productId, String[] keywords) {
        for (String keyword : keywords) {
            jedis.sadd("keyword::" + keyword + "::products", String.valueOf(productId));
        }
    }

    /**
     * 搜索商品
     */
    public Set<String> searchProduct(String[] keywords){
        List<String> list = new ArrayList<>();
        for (String keyword : keywords) {
            list.add("keyword::" + keyword + "::products");
        }

        String[] array = list.toArray(new String[list.size()]);
        return jedis.sinter(array);
    }

    public static void main(String[] args) {
        ProductSearchDemo demo = new ProductSearchDemo();

        //添加一批商品
        demo.addProductKeyword(1,new String[]{"手机","iphone","潮流"});
        demo.addProductKeyword(2,new String[]{"iphone","炫酷","潮流"});
        demo.addProductKeyword(3,new String[]{"手机","iphone","骚粉"});

        System.out.println("商品搜索结果："+demo.searchProduct(new String[]{"iphone", "潮流"}));
    }

}
