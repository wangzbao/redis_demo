package com.yolo.wz.redisdemo.set;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 朋友圈点赞案例
 */
public class Momentsdemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 点赞
     */
    public void likeMoment(long userId, long momentId) {
        jedis.sadd("moment_like_user::" + momentId, String.valueOf(userId));
    }

    /**
     * 取消点赞
     */
    public void unlikeMoment(long userId, long momentId) {
        jedis.srem("moment_like_user::" + momentId, String.valueOf(userId));
    }

    /**
     * 查看自己是否对某条朋友圈点赞过
     */
    public boolean haslikedMoment(long userId, long momentId) {
        return jedis.sismember("moment_like_user::" + momentId, String.valueOf(userId));
    }

    /**
     * 查看自己是否对某条朋友圈点赞过
     */
    public Set<String> getMomentlikeUser(long momentId) {
        return jedis.smembers("moment_like_user::" + momentId);
    }

    /**
     * 获取一条朋友圈被多少人点赞过
     */
    public long getMomentlikeUserCount(long momentId) {
        return jedis.scard("moment_like_user::" + momentId);
    }

    public static void main(String[] args) {
        long userId = 1;
        long friendId = 12;
        long otherFriendId = 13;
        long momentId = 1100;
        Momentsdemo demo = new Momentsdemo();
        //朋友1点赞
        demo.likeMoment(friendId, momentId);
        System.out.println(demo.getMomentlikeUserCount(momentId));
        demo.unlikeMoment(friendId, momentId);
        boolean b = demo.haslikedMoment(friendId, momentId);
        System.out.println(b ? "朋友1是真爱" : "塑料朋友");
        demo.likeMoment(otherFriendId, momentId);
        boolean of = demo.haslikedMoment(otherFriendId, momentId);
        System.out.println(of ? "别的朋友是真爱" : "塑料朋友");
        //朋友1重新点赞
        demo.likeMoment(friendId, momentId);
        Set<String> momentlikeUser = demo.getMomentlikeUser(momentId);
        System.out.println(momentlikeUser.toString());
        System.out.println("点赞的人数：" + demo.getMomentlikeUserCount(momentId));

    }
}