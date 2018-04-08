package cn.tzs.web.action.home.workflow;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.tzs.domain.MessageBore;
import cn.tzs.domain.User;
import cn.tzs.service.MessageBoreService;
import cn.tzs.utils.Page;
import cn.tzs.utils.SysConstant;
import cn.tzs.web.action.BaseAction;

@Namespace("/home/workflow")
@Results({ @Result(name = "toMessageBore", location = "/WEB-INF/pages/home/messageBore/messageBoreList.jsp"),
		@Result(name = "toList", location = "messageBoardAction_tasklist", type = "redirect") })
public class MessageBoardAction extends BaseAction implements ModelDriven<MessageBore> {

	@Autowired
	private MessageBoreService messageBoreService;
	/// 属性驱动，模型驱动
	private MessageBore model = new MessageBore();
	private Page page = new Page();

	/////////////////// action////////////////////////////
	@Action("messageBoardAction_tasklist")
	public String tasklist() throws Exception {
//		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		org.springframework.data.domain.Page<MessageBore> jpaPage = messageBoreService.findPage(null,
				new PageRequest(this.page.getPageNo() - 1, this.page.getPageSize()));

		page.setTotalRecord(jpaPage.getTotalElements());
		page.setTotalPage(jpaPage.getTotalPages());
		page.setResults(jpaPage.getContent());
		page.setUrl("messageBoardAction_tasklist");
		super.push(page);

		return "toMessageBore";
	}
	
	@Action("messageBoardAction_mBoardSubmits")
	public String messageBoardSubmit() throws Exception {
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		model.setTime(new Date());
		model.setUsername(user.getUserName());
		
		messageBoreService.saveOrUpdate(model);
		
		return "toList";
	}


	//////////////////////// get/set////////////////////////////////////////////

	public MessageBore getModel() {
		return model;
	}

	public void setModel(MessageBore model) {
		this.model = model;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
