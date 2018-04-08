package cn.tzs.web.action;

import cn.tzs.domain.User;
import cn.tzs.utils.SysConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * @Description: 登录和退出类
 * 继承BaseAction的作用
 * 1.可以与struts2的API解藕合
 * 2.还可以在BaseAction中提供公有的通用方法
 */
@Namespace("/")
@Results({
	@Result(name="login",location="/WEB-INF/pages/sysadmin/login/login.jsp"),
	@Result(name="success",location="/WEB-INF/pages/home/fmain.jsp"),
	@Result(name="logout",location="/index.jsp")})
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;



	//SSH传统登录方式
	@Action("loginAction_login")
	public String login() throws Exception {
		
//		if(true){
//			String msg = "登录错误，请重新填写用户名密码!";
//			this.addActionError(msg);
//			throw new Exception(msg);
//		}
//		User user = new User(username, password);
//		User login = userService.login(user);
//		if (login != null) {
//			ActionContext.getContext().getValueStack().push(user);
//			session.put(SysConstant.CURRENT_USER_INFO, login);	//记录session
//			return SUCCESS;
//		}
//		return "login";


		if (StringUtils.isBlank(username)){
			return "login";
		}

        try {
		    // 获取Subject对象
            Subject subject = SecurityUtils.getSubject();
            // 走到这里开始  执行认证  参数：登录令牌
			subject.login(new UsernamePasswordToken(username,password));// AuthenticationToken 的实现类 UsernamePasswordToken
			// 认证完成记录保存在session
            User user = (User) subject.getPrincipal();
            session.put(SysConstant.CURRENT_USER_INFO,user);
            return SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            super.put("errorInfo","输入的用户名或密码错误");
            return "login";
        }

	}
	
	
	//退出
	@Action("loginAction_logout")
	public String logout(){
		session.remove(SysConstant.CURRENT_USER_INFO);		//删除session
		SecurityUtils.getSubject().logout();   //登出
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

