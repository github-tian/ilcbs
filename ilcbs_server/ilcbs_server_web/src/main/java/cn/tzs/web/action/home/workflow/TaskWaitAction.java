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
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSON;

import cn.tzs.domain.AccessLog;
import cn.tzs.domain.CustomerMenu;
import cn.tzs.domain.Module;
import cn.tzs.domain.User;
import cn.tzs.service.AccessLogService;
import cn.tzs.service.ContractService;
import cn.tzs.service.CustomerMenuService;
import cn.tzs.service.ExportService;
import cn.tzs.service.ModuleService;
import cn.tzs.service.ShippingOrderService;
import cn.tzs.utils.SysConstant;
import cn.tzs.utils.UtilFuns;
import cn.tzs.web.action.BaseAction;

@Namespace("/home/workflow")
@Results({
	@Result(name="toTasklist",location="/WEB-INF/pages/home/taskwait/taskWaitList.jsp")
})
public class TaskWaitAction extends BaseAction {
	@Autowired
	private ContractService contractService;
	@Autowired
	private ExportService exportService;
	@Autowired
	private ShippingOrderService shippingOrderService;
	
	/////////////////查询所有状态////////////////////
	@Action("taskWaitAction_tasklist")
	public String fastMenu() throws Exception {

//		购销合同：购销合同 state：0
		int contractNum = contractService.findAcountByState(0);	
		super.put("contractNum", contractNum);
		
		
//		合同管理：购销合同 state：1 报运
		int conExpNum = contractService.findAcountByState(1);	
		super.put("conExpNum", conExpNum);
		
		
//		出口报运 ：1的需要进行电子报运
		int export = exportService.findAcountByState(1);
		super.put("export", export);
		
		
//		装箱管理： 状态为1的提示装箱 状态为1的提示委托。。。。
		int shippingOrder =shippingOrderService.findAcountByState(1);
		super.put("shippingOrder", shippingOrder);

		
		return "toTasklist";
	}

	

	////////////////////////// get/set/////////////////////////////////////////////



}
