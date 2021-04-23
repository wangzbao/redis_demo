package com.yolo.wz.redisdemo.list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tdtest")
public class TDController {
    @Value("${td}")
    private String td;


    @GetMapping("get")
    public String getTDDoc(){
        System.out.println("coming.....");
        System.out.println(td);
        return td;
    }

    

}
