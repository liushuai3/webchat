package com.chat.controller;

import com.chat.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("/login")
    public String login(User user){
        if("1".equals(user.getName())&&"1".equals(user.getPassword())){
            return "thymeleaf/login";
        }
        return "thymeleaf/index";
    }
}
