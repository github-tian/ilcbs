package cn.tzs.web.action.cargo;

import cn.tzs.domain.Export;
import cn.tzs.domain.PackingList;
import cn.tzs.domain.ShippingOrder;
import cn.tzs.domain.Invoice;
import cn.tzs.domain.User;
import cn.tzs.exceotion.SysException;
import cn.tzs.service.ExportService;
import cn.tzs.service.PackingListService;
import cn.tzs.service.ShippingOrderService;
import cn.tzs.service.InvoiceService;
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
 * 委托管理
 */
@Namespace("/cargo")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/cargo/invoice/jInvoiceList.jsp"),
        @Result(name = "toView", location = "/WEB-INF/pages/cargo/invoice/jInvoiceView.jsp"),
        @Result(name = "toCreate", location = "/WEB-INF/pages/cargo/invoice/jInvoiceCreate.jsp"),
        @Result(name = "toUpdate", location = "/WEB-INF/pages/cargo/invoice/jInvoiceUpdate.jsp"),
        @Result(name = "toList", location = "invoiceAction_list", type = "redirect")
})
public class InvoiceAction extends BaseAction implements ModelDriven<Invoice> {

	
	
	
	@Autowired
	private PackingListService packingListService;
    
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ExportService exportService;
    
    @Autowired
    private ShippingOrderService shippingOrderService;
    private Invoice model = new Invoice();
    private Page page = new Page();
    

    /**
     * 委托单管理查看所有页面
     */
    @Action(value = "invoiceAction_list")
    public String list() {
    	System.out.println("测试进入方法");
    	
        org.springframework.data.domain.Page<Invoice> jpaPage = invoiceService.findPage(null, new PageRequest(page.getPageNo() - 1, page.getPageSize()));
        if (!UtilFuns.isNotEmpty(jpaPage)) {
            return "list";
        }
        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("invoiceAction_list");
      /*  super.push(page);*/
        super.put("page",page);
        return "list";
    }

    /**
     * 委托单管理查看单个页面
     */
    @Action(value = "invoiceAction_toview")
    public String toView() {
        Invoice one = invoiceService.findOne(model.getId());
        super.push(one);
        return "toView";
    }

    /**
     * 委托单管理新增页面
     */
    @Action(value = "invoiceAction_tocreate")
    public String toCreate() {
    	/**
    	 * 发票开给已经委托的装箱单,装箱单状态为2  (ShippingOrder)
    	 */
    	Specification<ShippingOrder> spec = new Specification<ShippingOrder>() {

			@Override
			public Predicate toPredicate(Root<ShippingOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("state").as(Integer.class), 2);
			}
		};
        List<ShippingOrder> shippingOrders = shippingOrderService.find(spec);

        super.put("shippingOrders", shippingOrders);
        return "toCreate";
    }
    /**
     * 委托单管理新增操作
     */
    @Action(value = "invoiceAction_insert")
    public String insert() {
       
    	User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
    	model.setCreateBy(user.getUserName());
    	model.setState(0);
    	invoiceService.saveOrUpdate(model);
    	/**
    	 * 修改委托单的状态
    	 */
    	ShippingOrder shippingOrder = shippingOrderService.findOne(model.getId());
    	
    	shippingOrder.setState(3);
    	shippingOrderService.saveOrUpdate(shippingOrder);  
    	  
    	  
    	  
    	 //回填合同编号
    	/*PackingList packingList = packingListService.findOne(model.getId());
    	model.setScNo(packingList.getExportNos());
        invoiceService.saveOrUpdate(model);*/
        
        

        return "toList";
    }

    /**
     * 委托单管理修改页面
     */
    @Action(value = "invoiceAction_toupdate")
    public String toUpdate() {
    	
    	if (invoiceService.findOne(model.getId()).getState()==0) {
    		
    		Invoice one = invoiceService.findOne(model.getId());
    		super.push(one);
    		return "toUpdate";
    		
    		
		} else {
			
			super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
	        return "list";
		}
    	
    }

    /**
     * 委托单管理修改操作
     */
    @Action(value = "invoiceAction_update")
    public String update() {
        Invoice invoice = invoiceService.findOne(model.getId());
        
        invoice.setBlNo(model.getBlNo());
        invoice.setTradeTerms(model.getTradeTerms());
        invoice.setScNo(model.getScNo());
        invoiceService.saveOrUpdate(invoice);
        return "toList";
    }

    /**
     * 委托单管理删除操作
     */
    @Action(value = "invoiceAction_delete")
    public String delete() {
    	String[] ids = model.getId().split(", ");
    	for (String id : ids) {
			
    		if (invoiceService.findOne(id).getState()==0) {
    			
    			invoiceService.deleteById(id);
    			/**
    			 * 委托单删除时,重新设置委托单状态为2
    			 */
    			ShippingOrder shippingOrder = shippingOrderService.findOne(id);
    			
    			shippingOrder.setState(2);
    			shippingOrderService.saveOrUpdate(shippingOrder);  
    			
    		} else {
    			
    			super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
    			return "list";
    		}
    		
		}
    	return "toList";
    	
    	
    	
    	
    }

///////////////////////添加提交的方法/////////////////////////////
    @Action(value = "invoiceAction_submit")
    public String submit() {
    	String[] ids = model.getId().split(", ");
    	for (String id : ids) {
    		if (invoiceService.findOne(id).getState()==0) {
    			
    			Invoice invoice = invoiceService.findOne(id);
    			invoice.setState(3);
    			invoiceService.saveOrUpdate(invoice);
    			/**
    			 * 发票提交时回想装箱单的发票号  与发票日期,并修改装箱单状态
    			 */
    			
    			PackingList packingList = packingListService.findOne(id);
    			packingList.setState(3);
    			packingList.setInvoiceNo(invoice.getBlNo());
    			packingList.setInvoiceDate(new Date());
    			packingListService.saveOrUpdate(packingList);
    		
			} else {
				
				super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
				return "list";
			}
    		
    	}
    	
    	return "toList";
    	
    	
    	
    }

    @Override
    public Invoice getModel() {
        return model;
    }
    public void setModel(Invoice model) {
        this.model = model;
    }
   
}


