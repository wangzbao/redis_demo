package com.yolo.wz.redisdemo.set;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 投票统计案例
 * sadd把用户添加到某个投票项的投票用户集合里去
 * sismember可以检查用户是否已经对任何一个投票项发起过投票
 * scard可以统计每个投票箱的投票人数
 * smembers可以拿到每个投票项的投票人
 */
public class VoteDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 投票给某个投票项
     */
    public void vote(long userId, long voteItemId) {
        jedis.sadd("vote_item_user::" + voteItemId, String.valueOf(userId));
    }

    /**
     * 检擦是否对这个投票项投过票
     *
     * @return
     */
    public boolean hasVoted(long userId, long voteItemId) {
        return jedis.sismember("vote_item_user::" + voteItemId, String.valueOf(userId));
    }

    /**
     * 获取投票项的投票数量
     *
     * @return
     */
    public Set<String> getVotedUser(long voteItemId) {
        return jedis.smembers("vote_item_user::" + voteItemId);
    }

    /**
     * 获取投票项的投票数量
     *
     * @return
     */
    public long getVotedCount(long voteItemId) {
        return jedis.scard("vote_item_user::" + voteItemId);
    }

    public static void main(String[] args) {
        long userId = 1;
        long voteItemId = 10;

        VoteDemo demo = new VoteDemo();

        demo.vote(userId, voteItemId);
        System.out.println(demo.hasVoted(userId, voteItemId) ? userId + "投过票" : userId + "没投过");
        System.out.println("投票人数"+demo.getVotedCount(voteItemId));
        System.out.println("投票人为"+demo.getVotedUser(voteItemId));
    }
}
