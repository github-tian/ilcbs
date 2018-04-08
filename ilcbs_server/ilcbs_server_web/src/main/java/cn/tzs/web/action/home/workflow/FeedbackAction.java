package cn.tzs.web.action.home.workflow;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.tzs.domain.Feedback;
import cn.tzs.domain.User;
import cn.tzs.service.FeedbackService;
import cn.tzs.service.UserService;
import cn.tzs.utils.SysConstant;
import cn.tzs.web.action.BaseAction;

@Namespace("/home/workflow")
@Results({ @Result(name = "feedbackList", location = "/WEB-INF/pages/home/workflow/feedbackList.jsp"),
		@Result(name = "toList", location = "feedbackAction_feedbackList",  type = "redirect")
})
public class FeedbackAction extends BaseAction implements ModelDriven<Feedback> {

	@Autowired
	private UserService userService;
	@Autowired
	private FeedbackService feedbackService;

	private Feedback model =new Feedback();

	//////////////////// 意见反馈页面///////////////////////////
	@Action("feedbackAction_feedbackList")
	public String feedbackList() throws Exception {
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);

		Integer degree = userService.findOne(user.getId()).getUserinfo().getDegree();
		List<Feedback> feedbackList = null;
		switch (degree) {
		case 4:
			feedbackList = feedbackService.findByUserName(user.getUserName());
			break;
		// case 3:
		//
		// break;
		// case 2:
		//
		// break;
		// case 1:
		//
		// break;
		default:
			feedbackList = feedbackService.findAll();
			break;
		}

		super.put("feedbackList", feedbackList);
		return "feedbackList";
	}

	//////////////////// 意见反馈提交操作///////////////////////////
	@Action("feedbackAction_feedbackContent")
	public String feedbackContent() throws Exception {
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		//System.out.println("-------------------user.getUserName()-------------------------------" + user.getUserName());
		
		model.setInputBy(user.getUserName());
		model.setInputTime(new Date());
		model.setState("0");
		model.setCreateTime(new Date());

		feedbackService.saveOrUpdate(model);
		
		return "toList";
	}
	//////////////////// 意见反馈  标记已经解决///////////////////////////
	@Action("feedbackAction_markFeedback")
	public String markFeedback() throws Exception {
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		System.out.println("-------------------user.getUserName()-------------------------------" + user.getUserName());
		Feedback feedback = feedbackService.findOne(model.getId());
		feedback.setState("1");
		feedback.setAnswerBy(user.getUserName());
		feedback.setAnswerTime(new Date());
		
		feedbackService.saveOrUpdate(feedback);
		
		return "toList";
	}

	//////////////////// get/set///////////////////////
	public Feedback getModel() {
		return model;
	}

	public void setModel(Feedback model) {
		this.model = model;
	}

}
