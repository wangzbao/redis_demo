package com.yolo.wz.redisdemo.set;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * 抽奖小程序
 */
public class LotteryDrawDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加抽奖候选人
     */
    public void addLotteryDraw(long userId, long lotteryDrawEventId) {
        jedis.sadd("lottery_draw::" + lotteryDrawEventId + "::candidate", String.valueOf(userId));
    }

    /**
     * 抽奖发起
     */
    public List<String> doLotteryDraw(long lotteryDrawEventId,int count){
        return jedis.srandmember("lottery_draw::" + lotteryDrawEventId + "::candidate",count);
    }

    public static void main(String[] args) {
        LotteryDrawDemo demo = new LotteryDrawDemo();
        long lotteryDrawEventId = 110;
        for (int i = 0; i < 20; i++) {
            long userId = i+1;

            demo.addLotteryDraw(userId,lotteryDrawEventId);
        }

        System.out.println("获奖人是："+demo.doLotteryDraw(lotteryDrawEventId, 3));
    }
}
