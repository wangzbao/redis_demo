package com.yolo.wz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WzApplication {

    public static void main(String[] args) {
        SpringApplication.run(WzApplication.class, args);
    }

}
