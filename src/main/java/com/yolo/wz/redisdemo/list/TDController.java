package com.yolo.wz.redisdemo.list;

import cn.hutool.core.net.NetUtil;
import com.yolo.wz.redisdemo.domain.User;
import com.yolo.wz.redisdemo.util.IpUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tdtest")
public class TDController {
    //    @Value("${tdstatement}")
    private String TD;


    @Autowired
    private RedisTemplate redisTemplate = new RedisTemplate();

    @GetMapping("get")
    public void getTDDoc(User user) {
        System.out.println(user.toString());
    }

    @Scheduled(cron = "*/20 * * * * ?")
    public void getDetail() {
        System.out.println("打印信息：" + TD);
    }

    public static void main(String[] args) {
        //980元5毛2分
        Long dividend = 98052L;
        Integer divisor = 4;
        long l = 0 == divisor ? 0 : NumberUtils.toScaledBigDecimal(dividend / divisor * 1d, 1, RoundingMode.HALF_UP).longValue();
        System.out.println(l);

        String hostAddress = NetUtil.getLocalhost().getHostAddress();
        System.out.println(hostAddress);

        String linuxLocalIp = IpUtil.getLinuxLocalIp();
        System.out.println(linuxLocalIp);

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
        double v = NumberUtils.toScaledBigDecimal(6123 * 100d / 82316, 2, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println(v);

        String target = "2021-06-31" + "groupBy" + "张三";
        String[] groupBIES = target.split("groupBy");
        String result;
        System.out.println(groupBIES.length);
        result = 1 < groupBIES.length ? groupBIES[1] : "233";
        System.out.println(result);

        List<User> list = new ArrayList<>();
        User zhangsan = new User();
        zhangsan.setAge(18);
        zhangsan.setName("张三");
        zhangsan.setLike("篮球");

        User lisi = new User();
        lisi.setName("李四");
        lisi.setAge(24);
        lisi.setLike("跑步");

        User wangwu = new User();
        wangwu.setName("张三");
        wangwu.setAge(24);
        wangwu.setLike("rap");
        list.add(lisi);
        list.add(zhangsan);
        list.add(wangwu);

        System.out.println("list的长度：" + list.size());

        HashSet<String> set = new HashSet<>();
        List<User> users = new ArrayList<>();
        list.stream().collect(Collectors.groupingBy(
                user -> {
                    String name = user.getName();
                    set.add(user.getName());
                    return user.getName();
                }
        )).forEach((key, value) -> {
            System.out.println("key是" + key);
            System.out.println("value是" + value);
            User user = new User();
            user.setName(1 < groupBIES.length ? groupBIES[1] : null);

            value.forEach(stats->{
                User user1 = new User();
                BeanUtils.copyProperties(stats,user1);
            });
            users.add(user);
        });

        System.out.println(set.size());
        System.out.println(users);
    }

}
