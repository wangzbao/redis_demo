package com.yolo.wz.redisdemo.hash;

import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户会话管理案例
 */
public class SessionDemo {

    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 检查session是否有效
     *
     * @return
     */
    public boolean isSessionValid(String token) throws ParseException {
        if (null == token || "".equals(token)) {
            return false;
        }
        //此处的session就是字符串，如用户userid
        String session = jedis.hget("sessions", "session::" + token);
        if (session == null || "".equals(session)) {
            return false;
        }
        //获取过期时间
        String expireTime = jedis.hget("session::expire_time", "session::" + token);
        if (expireTime == null || "".equals(expireTime)) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date expireDate = dateFormat.parse(expireTime);
        Date now = new Date();
        //现在时间超过过期时间
        if (now.after(expireDate)) {
            return false;
        }
        //如果token不为空，且获取到的session不为空，且没有过期，则说明session没过期
        return true;
    }

    /**
     * 登录成功后初始化session
     *
     * @param userId
     */
    public void initSession(long userId, String token) {
        //设置session
        jedis.hset("sessions", "session::" + token, String.valueOf(userId));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 24);
        Date expireTime = calendar.getTime();
        //设置session过期时间
        jedis.hset("session::expire_time", "session::" + token, dateFormat.format(expireTime));

    }

}

