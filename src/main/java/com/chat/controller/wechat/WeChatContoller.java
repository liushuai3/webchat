package com.chat.controller.wechat;

import com.chat.pojo.message.TextMessage;
import com.chat.utils.CheckUtil;
import com.chat.utils.MessageUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
		//message:{signature=5acfc62110690653be05c33be431d3140a2f3ab1, openid=oH58K0u5cGEpgADi7zK8jqyBsfX0, nonce=1607365876, timestamp=1531047691}
        String respMessage = null;
        //默认返回的文本消息内容
        String respContent = "请求处理异常，请稍后尝试！";
        try {
			//xml请求解析
			Map<String,String> requestMap = MessageUtil.pareXml(request);
			log.error("requestMap:"+requestMap.toString());
			//发送方账号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			//公众账号
			String toUserName = requestMap.get("ToUserName");
			//消息类型
			String msgType = requestMap.get("MsgType");

			//文本消息
			if(msgType.equals(MessageUtil.MESSSAGE_TYPE_TEXT)){
				//respContent = "Hi,您发送的是文本消息！";
				//回复文本消息
				TextMessage textMessage = new TextMessage();
				//这里需要注意，否则无法回复消息给用户了
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(MessageUtil.MESSSAGE_TYPE_TEXT);
				//textMessage.setFuncFlag(0);
				respContent = "Hi，你发的消息是："+requestMap.get("Content");
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

		}catch(Exception e){
			log.error("erro:"+e.getMessage());
		}
		return respMessage;
	}
}
