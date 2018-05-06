package com.chat.controller;

import com.chat.pojo.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ftl")
public class FreemarkerController {

    @Autowired
    private RedisConfig redisConfig;
	
	@RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("redisConfig", redisConfig);
        return "freemarker/index";
    }
}