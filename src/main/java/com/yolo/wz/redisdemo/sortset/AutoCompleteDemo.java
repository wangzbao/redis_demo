package com.yolo.wz.redisdemo.sortset;

import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Set;

/**
 * 搜索框自动补全
 */
public class AutoCompleteDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 查询时候将搜索关键字加入索引
     */
    public void search(String keyword) {
        char[] keywordCharArray = keyword.toCharArray();
        StringBuffer potentialKeyword = new StringBuffer();

        for (char keywordChar : keywordCharArray) {
            potentialKeyword.append(keywordChar);

            jedis.zincrby("potential_keyword::" + potentialKeyword.toString() + "::keyword",
                    new Date().getTime(),
                    keyword);

            //设置过期时间
            jedis.expire("potential_keyword::" + potentialKeyword.toString() + "::keyword", 10);
        }
    }

    /**
     * 获取自动补全的列表
     */
    public Set<String> getAutoCompleteList(String potentialKeyWord) {
        return jedis.zrevrange("potential_keyword::" + potentialKeyWord + "::keyword", 0, 2);
    }

    public static void main(String[] args) throws InterruptedException {
        AutoCompleteDemo demo = new AutoCompleteDemo();

        demo.search("我爱大家");
        demo.search("我喜欢学习redis");
        demo.search("我很喜欢某个城市");
        demo.search("我不太爱玩儿");
        demo.search("我喜欢学习Spark");

        Set<String> wo = demo.getAutoCompleteList("我");
        System.out.println(wo);
        Set<String> woxi = demo.getAutoCompleteList("我喜");
        System.out.println(woxi);

        Thread.sleep(12 * 1000);
        System.out.println("冷数据自动淘汰机制后：" + demo.getAutoCompleteList("我"));
    }

}
