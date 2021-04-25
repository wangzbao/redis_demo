package com.yolo.wz.redisdemo.set;

import redis.clients.jedis.Jedis;

/**
 * 微博案例
 */
public class MicroBlogDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    public void follow(long userId, long followUserId) {
        //大v的粉丝列表增加用户
        jedis.sadd("user::"+followUserId+"::followers",String.valueOf(userId));
        //用户的关注列表增加一个大v
        jedis.sadd("user::"+userId+"::followe_users",String.valueOf(followUserId));
    }

}
