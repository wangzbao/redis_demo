package com.yolo.wz.redisdemo.list;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 注册之后发送邮件的任务
 */
public class SendMailDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加到发送邮件的队列
     */
    public void enqueueSendMailTask(String sendTask) {
        jedis.lpush("send_mail_task_queue", sendTask);
    }

    /**
     * 阻塞式获取任务
     *
     * @return
     */
    public List<String> takeMailTask() {
        List<String> send_mail_task_queue = jedis.brpop(5, "send_mail_task_queue");
        return send_mail_task_queue;
    }

    public static void main(String[] args) {
        SendMailDemo demo = new SendMailDemo();
        System.out.println("尝试获取阻塞式获取。。。");
        List<String> strings = demo.takeMailTask();
        if (strings != null) {
            System.out.println(strings.toString());
        }

        //添加
        demo.enqueueSendMailTask("添加一条数据进入队列。");
        System.out.println(demo.takeMailTask());
    }
}
