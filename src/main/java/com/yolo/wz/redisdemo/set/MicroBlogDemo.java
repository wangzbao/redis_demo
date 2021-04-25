package com.yolo.wz.redisdemo.set;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 微博案例
 */
public class MicroBlogDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 关注用户
     */
    public void follow(long userId, long followUserId) {
        //大v的粉丝列表增加用户
        jedis.sadd("user::" + followUserId + "::followers", String.valueOf(userId));
        //用户的关注列表增加一个大v
        jedis.sadd("user::" + userId + "::followe_users", String.valueOf(followUserId));
    }

    /**
     * 取消关注用户
     */
    public void unfollow(long userId, long followUserId) {
        //大v的粉丝列表减少用户
        jedis.srem("user::" + followUserId + "::followers", String.valueOf(userId));
        //用户的关注列表减少一个大v
        jedis.srem("user::" + userId + "::followe_users", String.valueOf(followUserId));
    }

    /**
     * 查看有哪些人关注了自己
     */
    public Set<String> getWhoFollowMe(long userId) {
        return jedis.smembers("user::" + userId + "::followers");
    }

    /**
     * 查看关注了自己的人数
     */
    public long getFollowsCount(long userId) {
        return jedis.scard("user::" + userId + "::followers");
    }

    /**
     * 查看自己关注了哪些人
     */
    public Set<String> getFollowsUser(long userId) {
        return jedis.smembers("user::" + userId + "::followe_users");
    }

    public static void main(String[] args) {
        MicroBlogDemo demo = new MicroBlogDemo();
        long userId = 1;
        long friendId = 2;
        long superStar = 3;

        demo.follow(userId, superStar);
        demo.follow(friendId, superStar);
        demo.follow(userId, friendId);

        //明星查看他被谁关注了
        Set<String> superStarsFans = demo.getWhoFollowMe(superStar);
        System.out.println("明星的粉丝：" + superStarsFans.toString());
        System.out.println("明星的粉丝数量：" + demo.getFollowsCount(superStar));

        //朋友看看自己被谁关注了，自己关注了谁
        System.out.println("朋友看看被谁关注了：" + demo.getWhoFollowMe(friendId));
        System.out.println("朋友看看关注了谁：" + demo.getFollowsUser(friendId));
        System.out.println("朋友看看被关注的人数：" + demo.getFollowsCount(friendId));

        //我关注了谁
        System.out.println("查看我关注了谁：" + demo.getFollowsUser(userId));
        System.out.println("查看我关注的人数：" + demo.getFollowsCount(userId));
    }

}
