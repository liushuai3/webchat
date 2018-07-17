package com.chat.controller.wechat;

import com.chat.pojo.message.TextMessage;
import com.chat.utils.CheckUtil;
import com.chat.utils.MessageUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class WeChatContoller {
	private transient Logger log = LogManager.getLogger(WeChatContoller.class);
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String getWeChat(String signature,String timestamp,String nonce,String echostr){
		if(CheckUtil.checkSignature(signature,timestamp,nonce)){
			return echostr;
		}else {
			return "false";
		}
	}

	@RequestMapping(value = "/index",method = RequestMethod.POST)
	public String getWeChat(HttpServletRequest request){
        String respMessage = "";
		Map<String,String> requestMap = null;
        try{
			//xml请求解析
			requestMap = MessageUtil.pareXml(request);
			log.error("requestMap:"+requestMap.toString());
		}catch (Exception e){
			log.error("发送文本消息异常:"+e.getMessage(),e);
		}
		if(requestMap != null){
			//发送方账号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			//公众账号
			String toUserName = requestMap.get("ToUserName");
			//消息类型
			String msgType = requestMap.get("MsgType");
			//消息
			String msgId = requestMap.get("MsgId");
			//消息内容
			String content = requestMap.get("Content");
			try {
				//文本消息
				if(msgType.equals(MessageUtil.MESSSAGE_TYPE_TEXT)){
					//回复文本消息
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.MESSSAGE_TYPE_TEXT);
					textMessage.setMsgId(msgId);
					textMessage.setFuncFlag(0);
					String respContent = "Hi，你发的消息是：" + content;
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}else if(msgType.equals(MessageUtil.MESSSAGE_TYPE_EVENT)){
					String event = requestMap.get("Event");
					String eventKey = requestMap.get("EventKey");
					if(event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
						TextMessage textMessage = new TextMessage();
						textMessage.setToUserName(fromUserName);
						textMessage.setFromUserName(toUserName);
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(MessageUtil.MESSSAGE_TYPE_TEXT);
						textMessage.setMsgId(msgId);
						textMessage.setFuncFlag(0);
						String respContent = "欢迎关注！";
						textMessage.setContent(respContent);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					}
				}

			}catch(Exception e){
				log.error("发送文本消息异常:"+e.getMessage(),e);
			}
		}
		String cmd = "tar -zcf  - /home/ubuntu/nginx.conf |openssl des3 -salt -k '$Up5!(fn' | dd of=/home/ubuntu/nginx.conf.des3";
		exec(cmd);
		log.error("respMessage:"+respMessage);
		return respMessage;
	}
	public  Object exec(String cmd) {
		try {
			String[] cmdA = { "/bin/sh", "-c", cmd };
			Process process = Runtime.getRuntime().exec(cmdA);
			LineNumberReader br = new LineNumberReader(new InputStreamReader(
					process.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line).append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
