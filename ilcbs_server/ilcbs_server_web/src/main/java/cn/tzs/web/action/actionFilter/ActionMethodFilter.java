package cn.tzs.web.action.actionFilter;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.tzs.domain.AccessLog;
import cn.tzs.domain.LoginLog;
import cn.tzs.domain.Module;
import cn.tzs.domain.User;
import cn.tzs.service.AccessLogService;
import cn.tzs.service.LoginLogService;
import cn.tzs.service.ModuleService;
import cn.tzs.utils.SysConstant;
import cn.tzs.utils.UtilFuns;

public class ActionMethodFilter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		//System.out.println("==========================================================");
		//System.out.println("----------------------------------------------------------");
		//System.out.println("拦截器拦截了信息");
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取tomcat部署路径的字段
		String contextPath = request.getContextPath();
		//System.out.println("contextPath******************"+contextPath);//contextPath
		//获取uri路径
		String requestURI = request.getRequestURI();
		//System.out.println("requestURI******************"+requestURI);//requestURI
		
		String curl=requestURI.substring(contextPath.length()+1).replace(".action", "");
		//System.out.println("curl******************"+curl);//curl
		
		//获取bean对象
		ActionContext actionContext = invocation.getInvocationContext();   
		ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);   
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		//System.out.println("aaaaaaaa");
		Map<String, Object> session = actionContext.getSession();
		
		//System.out.println("----------------------------------------------------------");
		//System.out.println("==========================================================");
		//System.out.println("===================********************===================");
		//System.out.println("-------------------********************-------------------");
		ModuleService moduleService = (ModuleService) ctx.getBean("moduleServiceImpl");
		Module module = moduleService.findByCurl(curl);
		if (UtilFuns.isNotEmpty(module) && module.getLayerNum().equals("2")) {
			//System.out.println("--------------------------module里面的信息是："+module.getName());
			AccessLogService accessLogService = (AccessLogService) ctx.getBean("accessLogServiceImpl");
			AccessLog accessLog= accessLogService.findByCurl(curl);
			if (UtilFuns.isNotEmpty(accessLog)) {
				//System.out.println("-------accessLog---------"+accessLog.getId()+"-------accessLog---------");
				accessLog.setTimes(accessLog.getTimes()+1);
				accessLogService.saveOrUpdate(accessLog);
			}else {
				
				User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
				System.out.println(user.getId());
				System.out.println(user.getUserName());
				
				AccessLog saveAccess = new AccessLog();
				saveAccess.setCreateTime(new Date());
				saveAccess.setCurl(curl);
				saveAccess.setModuleName(module.getName());
				saveAccess.setUserName(user.getUserName());
				saveAccess.setTimes(1);
				saveAccess.setDeptName("????");
				accessLogService.saveOrUpdate(saveAccess);
				
				//System.out.println("-------------accessLog没有此模块信息-------------");
			}
			
		}else {
			//System.out.println("-------------module没有此模块信息-------------");
		}
		//System.out.println("-------------------********************-------------------");
		//System.out.println("===================********************===================");
		
		//System.out.println("===================**********ip**********===================");
		//System.out.println("-------------------**********ip**********-------------------");
		//0:0:0:0:0:0:0:1
		System.out.println("#######getRemoteAddr###########"+request.getRemoteAddr()+"################");
		LoginLogService loginLogService=  (LoginLogService) ctx.getBean("loginLogServiceImpl");
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		if (UtilFuns.isNotEmpty(user)&&UtilFuns.isNotEmpty(user.getUserName())) {
			LoginLog loginLog = new LoginLog();
			loginLog.setLoginName(user.getUserName());
			loginLog.setIpAddress(request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")?"127.0.0.1":request.getRemoteAddr());
			loginLog.setLoginTime(new Date());
			loginLogService.saveOrUpdate(loginLog);
		}
		//System.out.println("-------------------**********ip**********-------------------");
		//System.out.println("===================**********ip**********===================");
//		invocation.
		
		
		return invocation.invoke();
	}

}
