package com.chat.controller;

import com.chat.pojo.JSONResult;
import com.chat.pojo.RedisConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    private RedisConfig redisConfig;
    //@RequestMapping("/{name}")
    //@GetMapping("/")
   /* @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object test(@RequestParam(value = "name", required = true)String name){
        if(name!=null&&!"".equals(name)) {
            name = name + "webchat";
        }
        return name;
    }*/
    @RequestMapping(value = "/")
    public Object test1(){
        return "webchat";
    }
}
