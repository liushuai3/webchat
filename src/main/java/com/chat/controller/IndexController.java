package com.chat.controller;

import com.chat.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;

import java.nio.channels.FileChannel;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("/")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("user",new User());
        return "thymeleaf/index";
    }
}
