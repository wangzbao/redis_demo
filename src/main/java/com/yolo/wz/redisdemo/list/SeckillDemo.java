package com.yolo.wz.redisdemo.list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * 秒杀活动案例
 */
@Component
public class SeckillDemo {
    @Value("${td}")
    private String td;
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 秒杀抢购请求入队
     *
     * @param secKillRequest
     */
    public void enqueueSeckillRequest(String secKillRequest) {
        jedis.lpush("sec_kill_request_queue", secKillRequest);
    }

    /**
     * 秒杀抢购请求出队
     *
     * @param secKillRequest
     */
    public String dequeueSeckillRequest(String secKillRequest) {
        String rpop = jedis.rpop("sec_kill_request_queue");
        return rpop;
    }

    public static void main(String[] args) {
        SeckillDemo demo = new SeckillDemo();
//        for (int i = 0; i < 10; i++) {
//            demo.enqueueSeckillRequest("第" + (i + 1) + "个秒杀请求。");
//        }
//
//        while (true) {
//            String result = demo.dequeueSeckillRequest("sec_kill_request_queue");
//            if (result == null || "".equals(result)) {
//                break;
//            }
//            System.out.println(result);
//        }

    }
}
