package com.chat.controller.wechat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController		// @RestController = @Controller + @ResponseBody
@RequestMapping("/wechat")
public class WeChatContoller {

	@RequestMapping("/index")
	public String getWeChat(String signature){
		return signature;
	}
}
