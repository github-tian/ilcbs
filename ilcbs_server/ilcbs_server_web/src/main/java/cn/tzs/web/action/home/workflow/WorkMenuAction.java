package cn.tzs.web.action.home.workflow;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSON;

import cn.tzs.domain.AccessLog;
import cn.tzs.domain.CustomerMenu;
import cn.tzs.domain.Module;
import cn.tzs.domain.User;
import cn.tzs.service.AccessLogService;
import cn.tzs.service.CustomerMenuService;
import cn.tzs.service.ModuleService;
import cn.tzs.utils.SysConstant;
import cn.tzs.utils.UtilFuns;
import cn.tzs.web.action.BaseAction;

@Namespace("/home/workflow")
public class WorkMenuAction extends BaseAction {

	@Autowired
	private AccessLogService accessLogService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private CustomerMenuService customerMenuService;

	private String moduleIds;

	///////////////// ajax查询快捷菜单////////////////////
	@Action("workMenuAction_fastMenu")
	public String fastMenu() throws Exception {

		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);

		List<AccessLog> accessLogs = accessLogService.findTop5MenuByUserName(user.getUserName());
		String returnStr = JSON.toJSONString(accessLogs);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(returnStr);
		return NONE;
	}

	///////////////// ajax查询自定义菜单////////////////////
	@Action("workMenuAction_customerMenu")
	public String customerMenu() throws Exception {

		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);

		List<CustomerMenu> customerMenus = customerMenuService.findTop5MenuByUserName(user.getUserName());
		String returnStr = JSON.toJSONString(customerMenus);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(returnStr);
		return NONE;
	}

	///////////////// ajax清除快捷菜单//////////////////
	@Action("workMenuAction_clearFastMenue")
	public String clearFastMenue() throws Exception {

		final User user = (User) session.get(SysConstant.CURRENT_USER_INFO);

		List<AccessLog> accessLogs = accessLogService.find(new Specification<AccessLog>() {
			@Override
			public Predicate toPredicate(Root<AccessLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("userName").as(String.class), user.getUserName());
			}
		});

		for (AccessLog accessLog : accessLogs) {
			accessLogService.deleteById(accessLog.getId());
		}

		// System.out.println("-------------getUserName----------------"+user.getUserName());
		// ServletActionContext.getResponse().getWriter().write("");
		return NONE;
	}

	// 自定义菜单页面
	@Action(value = "workMenu_toCustmorMenu", results = {
			@Result(name = "toCustmorMenu", location = "/WEB-INF/pages/home/custmorMenu/custmorMenu.jsp") })
	public String genzTreeNdes() throws Exception {

		return "toCustmorMenu";
	}

	// 自定义菜单修改
	@Action("workMenuAction_genzTreeNodes")
	public String genzTreeNodes() throws Exception {
		final User user = (User) session.get(SysConstant.CURRENT_USER_INFO);

		List<CustomerMenu> customerMenus = customerMenuService.find(new Specification<CustomerMenu>() {

			@Override
			public Predicate toPredicate(Root<CustomerMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				return cb.equal(root.get("userName").as(String.class), user.getUserName());
			}
		});

		// // 查询所有模块
		List<Module> moduleList = moduleService.find(null);
		ArrayList returnList = new ArrayList();
		for (Module module : moduleList) {
			if (module.getLayerNum().equals("3")) {
				continue;
			}
			HashMap returnMap = new HashMap();
			returnMap.put("id", module.getId());
			returnMap.put("pId", module.getParentId());
			returnMap.put("name", module.getName());

			for (CustomerMenu customerMenu : customerMenus) {
				String mdName = customerMenu.getModuleName();
				if (module.getName().equals(mdName)) {
					returnMap.put("checked", true);
				}
			}

			returnList.add(returnMap);
		}
		String returnStr = JSON.toJSONString(returnList);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(returnStr);
		return NONE;
	}

	/**
	 * 修改角色权限操作
	 */
	@Action(value = "workMenuAction_saveCustomerMenu", results = {
			@Result(name = "toHome", location = "/homeAction_tomain.action?moduleName=home", type = "redirect") })
	public String module() {

		final User user = (User) session.get(SysConstant.CURRENT_USER_INFO);

		List<CustomerMenu> customerMenus = customerMenuService.find(new Specification<CustomerMenu>() {
			@Override
			public Predicate toPredicate(Root<CustomerMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("userName").as(String.class), user.getUserName());
			}
		});

		for (CustomerMenu customerMenu : customerMenus) {
			customerMenuService.deleteById(customerMenu.getId());
		}

		// 循环得到权限模块

		for (String moduleId : moduleIds.split(",")) {
			Module module = moduleService.findOne(moduleId);

			if (module.getLayerNum().equals("2") && UtilFuns.isNotEmpty(module.getCurl())) {
				CustomerMenu customerMenu = new CustomerMenu();
				customerMenu.setUserName(user.getUserName());
				customerMenu.setCurl(module.getCurl());
				customerMenu.setModuleName(module.getName());
				customerMenuService.saveOrUpdate(customerMenu);
			}
		}

		return "toHome";
	}

	////////////////////////// get/set/////////////////////////////////////////////

	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

}
