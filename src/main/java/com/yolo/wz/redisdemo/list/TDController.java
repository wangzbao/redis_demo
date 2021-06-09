package com.yolo.wz.redisdemo.list;

import com.yolo.wz.redisdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/tdtest")
public class TDController {
    //    @Value("${tdstatement}")
    private String TD;


    @Autowired
    private RedisTemplate redisTemplate = new RedisTemplate();

    @GetMapping("get/{td}")
    public String getTDDoc(@PathVariable("td") String td) {
        return TD = td;
    }

    @Scheduled(cron = "*/20 * * * * ?")
    public void getDetail(){
        System.out.println("打印信息："+TD);
    }

}
