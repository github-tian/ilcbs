package cn.tzs.web.action.baseinfo;

import cn.tzs.domain.Factory;
import cn.tzs.domain.User;
import cn.tzs.service.FactoryService;
import cn.tzs.service.ProductService;
import cn.tzs.utils.Page;
import cn.tzs.utils.SysConstant;
import cn.tzs.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

@Namespace("/baseinfo")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/baseinfo/factory/jFactoryList.jsp"),
        @Result(name = "toview", location = "/WEB-INF/pages/baseinfo/factory/jFactoryView.jsp"),
        @Result(name = "tocreate", location = "/WEB-INF/pages/baseinfo/factory/jFactoryCreate.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/baseinfo/factory/jFactoryUpdate.jsp"),
        @Result(name = "torole", location = "/WEB-INF/pages/baseinfo/factory/jFactoryRole.jsp"),
        @Result(name = "tolist", location = "factoryAction_list", type = "redirect")
})
public class FactoryAction extends BaseAction<Factory> implements ModelDriven<Factory> {

    @Autowired
    private ProductService productService;
    @Autowired
    private FactoryService factoryService;
    //模型驱动
    private Factory model = new Factory();
    //属性驱动
    private Page page = new Page();

    /**
     * 查看部门列表页面
     *
     */
    @Action(value = "factoryAction_list")
    public String list() {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        };
        Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Factory> jpaPage = factoryService.findPage(specification, pageable);

        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("factoryAction_list");
        super.push(page);
        return "list";
    }

    /**
     * 查看单个部门页面
     *
     */
    @Action(value = "factoryAction_toview")
    public String toview() {
        Factory factory = factoryService.findOne(model.getId());
        super.push(factory);
        return "toview";
    }

    /**
     * 跳转新建部门页面
     *
     */
    @Action(value = "factoryAction_tocreate")
    public String tocreate() {
        List<Factory> factoryList = factoryService.find(new Specification<Factory>() {
            @Override
            public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        super.put("factoryList", factoryList);
        return "tocreate";
    }

    //保存新建部门的操作
    @Action(value = "factoryAction_insert")
    public String insert() {
    	model.setCreateTime(new Date());
    	User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
    	model.setCreateBy(user.getUserName());
    	model.setUpdateTime(new Date());
        factoryService.saveOrUpdate(model);
        return "tolist";
    }

    /**
     * 跳转修改部门页面
     *
     */
    @Action(value = "factoryAction_toupdate")
    public String toupdate() {
        List<Factory> factoryList = factoryService.find(new Specification<Factory>() {
            @Override
            public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        Factory factory = factoryService.findOne(model.getId());
        super.push(factory);
        factoryList.remove(factory);
        super.put("factoryList", factoryList);
        return "toupdate";
    }

    //执行修改操作
    @Action(value = "factoryAction_update")
    public String update() {
        Factory factory = factoryService.findOne(model.getId());
      
        factory.setCtype(model.getCtype());
        factory.setContacts(model.getContacts());
        factory.setPhone(model.getPhone());
        factory.setMobile(model.getMobile());
        factory.setFax(model.getFax());
        factory.setAddress(model.getAddress());
        factory.setInspector(model.getInspector());
        factory.setRemark(model.getRemark());
        factory.setState(model.getState());
        factory.setUpdateTime(new Date());
        
        
        factoryService.saveOrUpdate(factory);
        return "tolist";
    }

    /**
     * 删除部门操作
     *
     */
    @Action(value = "factoryAction_delete")
    public String delete() {
        factoryService.delete(model.getId().split(", "));
        return "tolist";
    }
    
    
    ////////////////////////////get、and、set///////////////////////////////
    @Override
    public Factory getModel() {
        return model;
    }

    public void setModel(Factory model) {
        this.model = model;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}



