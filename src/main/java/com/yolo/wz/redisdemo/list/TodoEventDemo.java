package com.yolo.wz.redisdemo.list;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.List;
import java.util.Random;

/**
 * OA系统的todolist
 */
public class TodoEventDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加待办事项
     *
     * @param userId
     * @param todoEvent
     */
    public void addTodoEvent(long userId, String todoEvent) {
        jedis.lpush("todo_event::" + userId, todoEvent);
    }

    /**
     * 分页查询待办事项
     */
    public List<String> findTodoList(long userId, int pageSize, int pageNum) {
        int startNum = (pageNum - 1) * pageSize;
        int endNum = pageNum * pageSize - 1;
        List<String> lrange = jedis.lrange("todo_event::" + userId, startNum, endNum);
        return lrange;
    }

    /**
     * 添加紧急待办事项
     */
    public void insertTodoEvent(long userId, ListPosition position, String targetEvent, String todoEvent) {
        Long linsert = jedis.linsert("todo_event::" + userId, position, targetEvent, todoEvent);
    }

    /**
     * 修改一个代办事项
     */
    public void updateTodoEvent(long userId, int index, String todoEvent) {
        jedis.lset("todo_event::" + userId, index, todoEvent);
    }

    /**
     * 完成代码事项移除
     * 0——表示删除此list中相同的待办事项的个数
     */
    public void finishTodoEvent(long userId, String todoEvent) {
        jedis.lrem("todo_event::" + userId, 0, todoEvent);
    }

    public static void main(String[] args) {
        TodoEventDemo demo = new TodoEventDemo();
        long userId = 5;
        //添加待办事项
        for (int i = 0; i < 20; i++) {
            demo.addTodoEvent(userId, "第" + (i + 1) + "个代办事项");
        }

        //查询第一页待办事项
        System.out.println("第一次查询待办事项");
        List<String> todoList = demo.findTodoList(userId, 10, 1);
        for (String todoEvent : todoList) {
            System.out.println(todoEvent);
        }

        //插入一个代办事项
        Random random = new Random();
        int index = random.nextInt(todoList.size());
        String targetEvent = todoList.get(index);
        demo.insertTodoEvent(userId, ListPosition.BEFORE,targetEvent,"这是一个插入的代办事项");

        //重新分页查询第一页
        System.out.println("第二次查询待办事项");
        List<String> retodoList = demo.findTodoList(userId, 10, 1);
        for (String two : retodoList) {
            System.out.println(two);
        }

        //完成第一条代办
        demo.finishTodoEvent(userId,todoList.get(8));

        //再查询一次第一页
        System.out.println("第三次查询待办事项");
        List<String> finishtodoList = demo.findTodoList(userId, 10, 1);
        for (String three : finishtodoList) {
            System.out.println(three);
        }

        //修改一条代办
        demo.updateTodoEvent(userId,random.nextInt(todoList.size()),"这是一条修改的代办事项");

        //最后查询一次第一页
        System.out.println("第四次查询待办事项");
        List<String> lasttodoList = demo.findTodoList(userId, 10, 1);
        for (String four : lasttodoList) {
            System.out.println(four);
        }
    }
}
