package cn.tzs.client.action;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import cn.tzs.utils.ImageUtil;

public class UserAction extends BaseAction{

	@Autowired 
	private RedisTemplate<String, String> template;
	
	
	private String vercode;   //图片验证码
	private String phoneVercode;  //短信验证码
	private String email;
	private String telephone;
	
	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	public String getPhoneVercode() {
		return phoneVercode;
	}

	public void setPhoneVercode(String phoneVercode) {
		this.phoneVercode = phoneVercode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Action(value="userAction_genActiveCode")
	public String genActiveCode() throws Exception {
		// TODO Auto-generated method stub
		// 生成图片验证码
	
		String rundomStr = ImageUtil.getRundomStr();
		
		// 随机数放到session中
		session.put("imageCode", rundomStr);
		
		// 生成图片放到response中
		HttpServletResponse response = ServletActionContext.getResponse();
		ImageUtil.getImage(rundomStr, response.getOutputStream());
		
		return NONE;
	}
	
	@Action(value="userAction_sendVerCode")
	public String sendVerCode() throws Exception {
		// TODO Auto-generated method stub
		
		// 发送jms消息
		jmsTemplate.send("ilcbs_client", new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				MapMessage message = session.createMapMessage();
				message.setString("phone", telephone);
				
				return message;
			}
		});
		
		return NONE;
	}
	
	@Action(value="userAction_register")
	public String register() throws Exception {
		// TODO Auto-generated method stub
		String returnStr = "2";
		
		
		// 图片验证码是否正确
		String imageCode = (String) session.get("imageCode");
		if(!vercode.equals(imageCode)){  //图片验证码错误
			returnStr="0";
		}
		
		// 判断短信验证码是否正确
		String phoneCode = template.opsForValue().get(telephone);
		if(!phoneVercode.equals(phoneCode)){  //用户输入的短信验证码和redis的不想等
			returnStr="1";
		}
		
		// 清除Redis和session的缓存
		if("2".equals(returnStr)){ //证明一切正常
			session.remove("imageCode");
			//template.delete(telephone);
		}
		
		
		
		
		// 如果正常返回"2"
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(returnStr);
		return NONE;
	}
}
