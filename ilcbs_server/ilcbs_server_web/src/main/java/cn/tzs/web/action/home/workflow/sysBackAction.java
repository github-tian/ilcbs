package cn.tzs.web.action.home.workflow;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tzs.utils.MailUtil;
import cn.tzs.web.action.BaseAction;

@Namespace("/home/workflow")
@Results({
	@Result(name="sysBack",location="/WEB-INF/pages/home/personInfo/sysback.jsp")
})
public class sysBackAction extends BaseAction{
	

	private String textarea;
	
	@Action("sysBackAction_sysback")
	public String sysback(){
		return "sysBack";
	}
	
	@Action("sysBackAction_sendMail")
	public String sendMail() throws Exception{
		MailUtil.sendMsg("18237447002@163.com", "工作日及文本信息!", textarea);
		
		return "sysBack";
		
	}
	
	
	public String getTextarea() {
		return textarea;
	}


	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}
	
}
