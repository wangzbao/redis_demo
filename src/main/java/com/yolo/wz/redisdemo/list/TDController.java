package com.yolo.wz.redisdemo.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tdtest")
public class TDController {
    @Value("${tdstatement}")
    private String TD;

    @Autowired
    private RedisTemplate redisTemplate = new RedisTemplate();

    @GetMapping("get")
    public String getTDDoc() {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i+1);
//            redisTemplate.boundListOps("key").rightPush("第" + (i + 1) + "个v");
//        }
//        for (int i = 0; i < 10; i++) {
//            String v = (String) redisTemplate.boundListOps("key").leftPop();
//            System.out.println(v);
//        }

        return TD;
    }

}
