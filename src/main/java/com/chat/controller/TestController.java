package com.chat.controller;

import com.chat.pojo.JSONResult;
import com.chat.pojo.RedisConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private RedisConfig redisConfig;
    @RequestMapping("/")
    public Object test(){
        return "webchat";
    }
}
