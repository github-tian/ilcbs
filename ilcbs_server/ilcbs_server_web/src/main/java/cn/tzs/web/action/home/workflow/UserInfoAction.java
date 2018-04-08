package cn.tzs.web.action.home.workflow;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.tzs.domain.User;
import cn.tzs.service.UserService;
import cn.tzs.utils.SysConstant;
import cn.tzs.web.action.BaseAction;
@Namespace("/home/workflow")
public class UserInfoAction  extends BaseAction<User> implements ModelDriven<User>{

	private User model = new User();
	
	@Autowired
	private UserService userService;
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	/**
	 * 定位到修改用户的页面(并回显数据)
	 */
	@Action(value="userInfoAction_toUpdate",results={@Result(name="toUpdate",location="/WEB-INF/pages/home/personInfo/jUserInfo.jsp")})
	public String toUpdate() throws Exception {
		System.out.println("进入到修改用户信息的页面");
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		User user1 = userService.findOne(user.getUserName());
		super.push(user);
		
		return "toUpdate";
	}
	/**
	 * 修改页面
	 */
	@Action(value="userInfoAction_update",results={@Result(name="alist",type="redirect",location="userInfoAction_toUpdate")})
	public String update() {
		
		User user = userService.findOne(model.getId());
		
		user.setUserName(model.getUserName());
		
		System.out.println("++++++登录名++++++++"+model.getUserName());
		user.getUserinfo().setName(model.getUserinfo().getName());
		user.getUserinfo().setGender(model.getUserinfo().getGender());
		user.getUserinfo().setTelephone(model.getUserinfo().getTelephone());
		user.getUserinfo().setEmail(model.getUserinfo().getEmail());
		user.getUserinfo().setBirthday(model.getUserinfo().getBirthday());
	
		userService.saveOrUpdate(user);
		session.put(SysConstant.CURRENT_USER_INFO, user);
		return "alist";
		

	}

}
