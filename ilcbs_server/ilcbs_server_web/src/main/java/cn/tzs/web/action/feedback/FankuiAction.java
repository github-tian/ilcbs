package cn.tzs.web.action.feedback;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.opensymphony.xwork2.ModelDriven;

import cn.tzs.domain.Factory;
import cn.tzs.domain.Feedback;
import cn.tzs.domain.User;
import cn.tzs.service.FeedbackService;
import cn.tzs.utils.Page;
import cn.tzs.utils.SysConstant;
import cn.tzs.web.action.BaseAction;

@Namespace("/feedback")
@Results({
        @Result(name = "toBack", location = "/WEB-INF/pages/home/feedback/fankuiList.jsp"),
        @Result(name = "back", type="redirect", location = "fankuiAction_toBack"),
})
public class FankuiAction extends BaseAction<Feedback> implements ModelDriven<Feedback> {

//	反馈接口
	@Autowired
	private FeedbackService feedbackService;
	
//	实例化model
	private Feedback model = new Feedback();
	public Feedback getModel() {
		return model;
	}
	public void setModel(Feedback model) {
		this.model = model;
	}
	
//	创建page对象
	private Page page = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 系统使用反馈
	 * @return
	 */
	@Action("fankuiAction_toBack")
	public String toBack(){
		
//		1，查出所有反馈信息，回显至JSP页面
		Specification<Factory> factorySpec = new Specification<Factory>() {
			@Override
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("isShare").as(Integer.class), 1);
			}
		};
		Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
		org.springframework.data.domain.Page<Feedback> jpaPage = feedbackService.findPage(null, pageable);
		page.setResults(jpaPage.getContent());
		page.setTotalPage(jpaPage.getTotalPages());
		page.setTotalRecord(jpaPage.getTotalElements());
		
//		设置form表单提交的地址
		page.setUrl("fankuiAction_toBack");
		
//		压入值栈
		super.push(page);
		
		return "toBack";
	}
	
	/**
	 * 保存反馈信息
	 * @return
	 */
	@Action("fankuiAction_back")
	public String back(){
		
//		2，获取当前用户
		User user = (User) super.session.get(SysConstant.CURRENT_USER_INFO);
		
		feedbackService.saveOrUpdate(model);
		
		return "back";
	}

	
}


