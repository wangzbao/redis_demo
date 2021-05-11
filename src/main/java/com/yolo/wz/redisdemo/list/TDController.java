package com.yolo.wz.redisdemo.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
        List<VenderInfo> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            VenderInfo venderInfo = new VenderInfo();
            venderInfo.setVenderName(""+i+1);
            Random random = new Random();
            int i1 = random.nextInt(100);
            venderInfo.setSmsCount(i1);
            venderInfo.setDdCount(i1);
            list.add(venderInfo);
        }

        System.out.println("------qian-------");
        System.out.println(list);
        System.out.println("------hou-------");
        sort(list);
        int pageNum = 2;
        int pageSize = 10;
        int startNum = (pageNum - 1) * pageSize;
        int endNum = pageNum * pageSize ;
        List<VenderInfo> subList = list.subList(startNum, endNum);
        System.out.println(list);
        System.out.println("-------截取-------");
        System.out.println(subList);
        return TD;
    }

    private void sort(List<VenderInfo> list) {
        Collections.sort(list, new Comparator<VenderInfo>() {
            @Override
            public int compare(VenderInfo o1, VenderInfo o2) {
                return -o1.getSmsCount().compareTo(o2.getSmsCount());
            }
        });
    }

}
