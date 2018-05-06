package com.chat.controller;

import com.chat.pojo.RedisConfig;
import com.chat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("th")
public class ThymeleafController {
    @Autowired
    private RedisConfig redisConfig;

	@RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("redisConfig", redisConfig);
        map.addAttribute("name", "thymeleaf");
        return "thymeleaf/index";
    }
	@PostMapping("postform")
    public String postform(User u) {
		
		System.out.println("姓名：" + u.getName());
		System.out.println("年龄：" + u.getAge());
		
        return "redirect:/th/test";
    }
	
	@RequestMapping("showerror")
    public String showerror(User u) {
		
		int a = 1 / 0;
		
        return "redirect:/th/test";
    }
}