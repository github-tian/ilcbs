package cn.tzs.web.action.cargo;

import cn.tzs.domain.Export;
import cn.tzs.domain.PackingList;
import cn.tzs.domain.ShippingOrder;
import cn.tzs.domain.User;
import cn.tzs.exceotion.SysException;
import cn.tzs.domain.ShippingOrder;
import cn.tzs.service.ExportService;
import cn.tzs.service.PackingListService;
import cn.tzs.service.ShippingOrderService;
import cn.tzs.utils.Page;
import cn.tzs.utils.SysConstant;
import cn.tzs.utils.UtilFuns;
import cn.tzs.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

import org.apache.struts2.components.If;
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
        @Result(name = "list", location = "/WEB-INF/pages/cargo/shippingorder/jShippingOrderList.jsp"),
        @Result(name = "toView", location = "/WEB-INF/pages/cargo/shippingorder/jShippingOrderView.jsp"),
        @Result(name = "toCreate", location = "/WEB-INF/pages/cargo/shippingorder/jShippingOrderCreate.jsp"),
        @Result(name = "toUpdate", location = "/WEB-INF/pages/cargo/shippingorder/jShippingOrderUpdate.jsp"),
        @Result(name = "toList", location = "shippingOrderAction_list", type = "redirect")
})
public class ShippingOrderAction extends BaseAction implements ModelDriven<ShippingOrder> {

	
	
	
	@Autowired
	private PackingListService packingListService;
    
    @Autowired
    private ShippingOrderService shippingOrderService;
   
    private ShippingOrder model = new ShippingOrder();
    private Page page = new Page();
    

    /**
     * 委托单管理查看所有页面
     */
    @Action(value = "shippingOrderAction_list")
    public String list() {
    	System.out.println("测试进入方法");
    	
        org.springframework.data.domain.Page<ShippingOrder> jpaPage = shippingOrderService.findPage(null, new PageRequest(page.getPageNo() - 1, page.getPageSize()));
        if (!UtilFuns.isNotEmpty(jpaPage)) {
            return "list";
        }
        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("shippingOrderAction_list");
      /*  super.push(page);*/
        super.put("page",page);
        return "list";
    }

    /**
     * 委托单管理查看单个页面
     */
    @Action(value = "shippingOrderAction_toview")
    public String toView() {
        ShippingOrder one = shippingOrderService.findOne(model.getId());
        super.push(one);
        return "toView";
    }

    /**
     * 委托单管理新增页面
     */
    @Action(value = "shippingOrderAction_tocreate")
    public String toCreate() {
    	
    	Specification<PackingList> spec = new Specification<PackingList>() {

			@Override
			public Predicate toPredicate(Root<PackingList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
        List<PackingList> packingList = packingListService.find(spec);

        super.put("packingList", packingList);
        return "toCreate";
    }
    /**
     * 委托单管理新增操作
     */
    @Action(value = "shippingOrderAction_insert")
    public String insert() {
       
    	User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
    	model.setCreateBy(user.getUserName());
    	model.setState(0);
    	//创建部门暂留存id还是名字
    	
    	model.setCreateTime(new Date());
        shippingOrderService.saveOrUpdate(model);
        /**
         * 生成委托单后,将装箱单的状态改为2  让其不在委托新增列表中显示
         */
        PackingList packingList = packingListService.findOne(model.getId());
        packingList.setState(2);
        packingListService.saveOrUpdate(packingList);

        return "toList";
    }

    /**
     * 委托单管理修改页面
     */
    @Action(value = "shippingOrderAction_toupdate")
    public String toUpdate() {
    	if (shippingOrderService.findOne(model.getId()).getState()==0) {
    		
    		ShippingOrder one = shippingOrderService.findOne(model.getId());
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
    @Action(value = "shippingOrderAction_update")
    public String update() {
        ShippingOrder shippingOrder = shippingOrderService.findOne(model.getId());

        shippingOrder.setOrderType(model.getOrderType());
        shippingOrder.setShipper(model.getShipper());
        shippingOrder.setConsignee(model.getConsignee());
        shippingOrder.setNotifyParty(model.getNotifyParty());
        shippingOrder.setLcNo(model.getLcNo());
        shippingOrder.setPortOfLoading(model.getPortOfLoading());
        shippingOrder.setPortOfTrans(model.getPortOfTrans());
        shippingOrder.setPortOfDischarge(model.getPortOfDischarge());
        shippingOrder.setLoadingDate(model.getLoadingDate());
        shippingOrder.setLimitDate(model.getLimitDate());
        shippingOrder.setIsBatch(model.getIsBatch());
        shippingOrder.setIsTrans(model.getIsTrans());
        shippingOrder.setCopyNum(model.getCopyNum());
        shippingOrder.setRemark(model.getRemark());
        shippingOrder.setSpecialCondition(model.getSpecialCondition());
        shippingOrder.setFreight(model.getFreight());
        shippingOrder.setCheckBy(model.getCheckBy());
        
        shippingOrderService.saveOrUpdate(shippingOrder);
        return "toList";
    }

    /**
     * 委托单管理删除操作
     */
    @Action(value = "shippingOrderAction_delete")
    public String delete() {
    	
    	String[] ids = model.getId().split(", ");
    	
    	for (String id : ids) {
    		if (shippingOrderService.findOne(id).getState()==0) {
    			
    			shippingOrderService.deleteById(id);
    			
    			/**
    			 * 删除委托单后,将装箱单的状态回退为1  让其不在委托新增列表中显示
    			 */
    			PackingList packingList = packingListService.findOne(id);
    			packingList.setState(1);
    			packingListService.saveOrUpdate(packingList);
    			
    			
    		} else {
    			
    			super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
    			return "list";
    		}
    	}
    	return "toList";
    	
    }

///////////////////////添加提交的方法/////////////////////////////
    @Action(value = "shippingOrderAction_submit")
    public String submit() {
	String[] ids = model.getId().split(", ");
    	
    	for (String id : ids) {
    		if (shippingOrderService.findOne(id).getState()==0) {
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

    @Override
    public ShippingOrder getModel() {
        return model;
    }
    public void setModel(ShippingOrder model) {
        this.model = model;
    }
   
}


