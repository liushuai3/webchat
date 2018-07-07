package com.chat.controller.wechat;

import com.chat.utils.CheckUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController		// @RestController = @Controller + @ResponseBody
@RequestMapping("/wechat")
public class WeChatContoller {

	@RequestMapping("/index")
	public String getWeChat(String signature,String timestamp,String nonce,String echostr){
		if(CheckUtil.checkSignature(signature,timestamp,nonce)){
			return echostr;
		}else {
			return "hello";
		}
	}
}
