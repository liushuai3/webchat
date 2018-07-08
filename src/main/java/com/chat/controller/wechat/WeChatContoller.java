package com.chat.controller.wechat;

import com.chat.utils.CheckUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//@Controller
@RestController		// @RestController = @Controller + @ResponseBody
@RequestMapping("/wechat")
public class WeChatContoller {
	Logger log = LogManager.getLogger(WeChatContoller.class);
//	@RequestMapping("/index")
//	public String getWeChat(String signature,String timestamp,String nonce,String echostr){
//		if(CheckUtil.checkSignature(signature,timestamp,nonce)){
//			return echostr;
//		}else {
//			return "hello";
//		}
//	}

	@RequestMapping("/index")
	public String getWeChat(HttpServletRequest request){
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if(null == valueObj){
				value = "";
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){
					value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1);
			}else{
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		log.error("message:"+returnMap.toString());
		return "success";
	}
}
