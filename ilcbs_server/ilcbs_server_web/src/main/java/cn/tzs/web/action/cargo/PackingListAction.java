package cn.tzs.web.action.cargo;

import cn.tzs.domain.Export;
import cn.tzs.domain.PackingList;
import cn.tzs.exceotion.SysException;
import cn.tzs.service.ExportService;
import cn.tzs.service.PackingListService;
import cn.tzs.utils.Page;
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
import java.util.List;

@Namespace("/cargo")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/cargo/packinglist/jPackingListListPage.jsp"),
        @Result(name = "toView", location = "/WEB-INF/pages/cargo/packinglist/jPackingListView.jsp"),
        @Result(name = "toCreate", location = "/WEB-INF/pages/cargo/packinglist/jPackingListCreate.jsp"),
        @Result(name = "toUpdate", location = "/WEB-INF/pages/cargo/packinglist/jPackingListUpdate.jsp"),
        @Result(name = "toList", location = "packingListAction_list", type = "redirect")
})
public class PackingListAction extends BaseAction implements ModelDriven<PackingList> {

    @Autowired
    private PackingListService packingListService;
    @Autowired
    private ExportService exportService;

    private PackingList model = new PackingList();
    private Page page = new Page();

    /**
     * 装箱管理查看所有页面
     */
    @Action(value = "packingListAction_list")
    public String list() {

        org.springframework.data.domain.Page<PackingList> jpaPage = packingListService.findPage(null, new PageRequest(page.getPageNo() - 1, page.getPageSize()));
        if (!UtilFuns.isNotEmpty(jpaPage)) {
            return "list";
        }
        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("packingListAction_list");
        super.push(page);
        return "list";
    }

    /**
     * 装箱管理查看单个页面
     */
    @Action(value = "packingListAction_toview")
    public String toView() {
        PackingList one = packingListService.findOne(model.getId());
        super.push(one);
        return "toView";
    }

    /**
     * 装箱管理新增页面
     */
    @Action(value = "packingListAction_tocreate")
    public String toCreate() {
    	
    	
    	
        List<Export> exportList = exportService.find(new Specification<Export>() {
            @Override
            public Predicate toPredicate(Root<Export> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 2);
            }
        });

        super.put("exportList", exportList);
        return "toCreate";
    }
    /**
     * 装箱管理新增操作
     */
    @Action(value = "packingListAction_insert")
    public String insert() {
        //装箱单的保存
        packingListService.saveOrUpdate(model);

        return "toList";
    }

    /**
     * 装箱管理修改页面
     */
    @Action(value = "packingListAction_toupdate")
    public String toUpdate() {
    	
    	if (packingListService.findOne(model.getId()).getState()==0) {
    		
    		PackingList one = packingListService.findOne(model.getId());
    		super.push(one);
    		return "toUpdate";
    		
		} else {
			
			super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
	        return "list";
		}
    	
    	
    }

    /**
     * 装箱管理修改操作
     */
    @Action(value = "packingListAction_update")
    public String update() {
        PackingList packingList = packingListService.findOne(model.getId());
        packingList.setBuyer(model.getBuyer());
        packingList.setSeller(model.getSeller());
        packingList.setInvoiceNo(model.getInvoiceNo());
        packingList.setInvoiceDate(model.getInvoiceDate());
        packingList.setMarks(model.getMarks());
        packingList.setDescriptions(model.getDescriptions());

        packingListService.saveOrUpdate(packingList);
        return "toList";
    }

    /**
     * 装箱管理删除操作
     */
    @Action(value = "packingListAction_delete")
    public String delete() {
    	String[] ids = model.getId().split(", ");
    	
    	for (String id : ids) {
    		if (packingListService.findOne(id).getState()==0) {
    			
    			packingListService.deleteById(id);
    			
    		} else {
    			
    			super.put("perError","已提交,您已无权修改删除或再次提交;如有问题,请联系上一流转部门处理");
    			return "list";
    		}
    	}

		return "toList";
    	
    }

//////////////////////添加提交的内容//////////////////////////////

    @Action("packingListAction_submit")
    public String submit() throws SysException {
    	String[] ids = model.getId().split(", ");
    	
    	for (String id : ids) {
    		if (packingListService.findOne(id).getState()==0) {
    			
    			
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

  
    @Override
    public PackingList getModel() {
        return model;
    }
    public void setModel(PackingList model) {
        this.model = model;
    }
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

}


