package cn.tzs.web.action.cargo;

import cn.tzs.domain.Export;
import cn.tzs.domain.PackingList;
import cn.tzs.domain.ShippingOrder;
import cn.tzs.domain.Invoice;
import cn.tzs.domain.Finance;
import cn.tzs.domain.User;
import cn.tzs.exceotion.SysException;
import cn.tzs.service.ExportService;
import cn.tzs.service.PackingListService;
import cn.tzs.service.InvoiceService;
import cn.tzs.service.FinanceService;
import cn.tzs.utils.Page;
import cn.tzs.utils.SysConstant;
import cn.tzs.utils.UtilFuns;
import cn.tzs.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Date;
import java.util.List;
/**
 * 财务管理
 */
@Namespace("/cargo")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/cargo/finance/jFinanceList.jsp"),
        @Result(name = "toView", location = "/WEB-INF/pages/cargo/finance/jFinanceView.jsp"),
        @Result(name = "toCreate", location = "/WEB-INF/pages/cargo/finance/jFinanceCreate.jsp"),
        @Result(name = "toUpdate", location = "/WEB-INF/pages/cargo/finance/jFinanceUpdate.jsp"),
        @Result(name = "toList", location = "financeAction_list", type = "redirect")
})
public class FinanceAction extends BaseAction implements ModelDriven<Finance> {

	
	
	
	@Autowired
	private PackingListService packingListService;
    
    @Autowired
    private FinanceService financeService;
    @Autowired
    private ExportService exportService;
    
    @Autowired
    private InvoiceService invoiceService;
    private Finance model = new Finance();
    private Page page = new Page();
    

    /**
     * 财务单管理查看所有页面
     */
    @Action(value = "financeAction_list")
    public String list() {
    	System.out.println("测试进入方法");
    	
        org.springframework.data.domain.Page<Finance> jpaPage = financeService.findPage(null, new PageRequest(page.getPageNo() - 1, page.getPageSize()));
        if (!UtilFuns.isNotEmpty(jpaPage)) {
            return "list";
        }
        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("financeAction_list");
      /*  super.push(page);*/
        super.put("page",page);
        return "list";
    }

    /**
     * 财务单管理查看单个页面
     */
    @Action(value = "financeAction_toview")
    public String toView() {
        Finance one = financeService.findOne(model.getId());
        super.push(one);
        return "toView";
    }

    /**
     * 财务单管理新增页面
     */
    @Action(value = "financeAction_tocreate")
    public String toCreate() {
    	/**
    	 * 发票开给已经财务的装箱单,装箱单状态为2  (Invoice)
    	 */
    	Specification<Invoice> spec = new Specification<Invoice>() {

			@Override
			public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("state").as(Integer.class), 3);
			}
		};
        List<Invoice> invoices = invoiceService.find(spec);

        super.put("invoices", invoices);
        return "toCreate";
    }
    /**
     * 财务单管理新增操作
     */
    @Action(value = "financeAction_insert")
    public String insert() {
       
    	User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
    	model.setCreateBy(user.getUserName());
    	model.setState(0);
    	financeService.saveOrUpdate(model);
    	/**
    	 * 修改财务单的状态
    	 */
    	Invoice invoice = invoiceService.findOne(model.getId());
    	/**
    	 * 财务生成账单后,发票状态为4
    	 */
    	invoice.setState(4);
    	invoiceService.saveOrUpdate(invoice);  
    	  
        return "toList";
    }

    /**
     * 财务单管理修改页面
     */
    @Action(value = "financeAction_toupdate")
    public String toUpdate() {
    	
    	if (financeService.findOne(model.getId()).getState()==0) {
    		
    		Finance one = financeService.findOne(model.getId());
    		//暂留去除状态为2:已报运的修改权限  抛出已报运的提示
    		super.push(one);
    		return "toUpdate";
    		
    		
		} else {
			
			super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
	        return "list";
		}
    	
    	
    }

    /**
     * 财务单管理修改操作
     */
    @Action(value = "financeAction_update")
    public String update() {
        Finance finance = financeService.findOne(model.getId());
        
        
        finance.setInputBy(model.getInputBy());
        finance.setInputDate(model.getInputDate());
        //所有的新增都为0
        finance.setState(0);
        
        financeService.saveOrUpdate(finance);
        return "toList";
    }

    /**
     * 财务单管理删除操作
     */
    @Action(value = "financeAction_delete")
    public String delete() {
    	String[] ids = model.getId().split(", ");
    	
    	for (String id : ids) {
			
    		if (financeService.findOne(id).getState()==0) {
    			
    			financeService.deleteById(id);
    			/**
    			 * 财务单删除时,重新设置财务单状态为
    			 */
    			Invoice invoice = invoiceService.findOne(id);
    			
    			invoice.setState(3);
    			invoiceService.saveOrUpdate(invoice);  
    			
    		} else {
    			
    			super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
    			return "list";
    		}
		}
    	
    	return "toList";
    	
    }

///////////////////////添加提交的方法/////////////////////////////
    @Action(value = "financeAction_submit")
    public String submit() {
	String[] ids = model.getId().split(", ");
    	
    	for (String id : ids) {
    	
	    	if (financeService.findOne(id).getState()==0) {
	    		
	    			Finance finance = financeService.findOne(id);
	    			finance.setState(4);
	    			financeService.saveOrUpdate(finance);
	    			/**
	    			 * 发票提交时回想装箱单的发票号  与发票日期,并修改装箱单状态
	    			 */
	    			
	    			PackingList packingList = packingListService.findOne(id);
	    			packingList.setState(4);
	    			/*packingList.setInvoiceNo(finance.getBlNo());
	            packingList.setInvoiceDate(new Date());*/
	    			packingListService.saveOrUpdate(packingList);
	    		
	    		
			} else {
				
				super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
				return "list";
			}
    	
    	}
    	return "toList";
    	
    
    	
    	
    }

    @Override
    public Finance getModel() {
        return model;
    }
    public void setModel(Finance model) {
        this.model = model;
    }
   
}


