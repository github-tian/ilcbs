package cn.tzs.web.action.cargo;

import cn.tzs.domain.ExtCproduct;
import cn.tzs.domain.Factory;
import cn.tzs.service.ExtCproductService;
import cn.tzs.service.FactoryService;
import cn.tzs.utils.Page;
import cn.tzs.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

@Namespace("/cargo")
@Results({
        @Result(name = "tocreate", location = "/WEB-INF/pages/cargo/contract/jExtCproductCreate.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/cargo/contract/jExtCproductUpdate.jsp"),
        @Result(name = "tolist", location = "extCproductAction_tocreate?contractProduct.id=${contractProduct.id}&contractProduct.contract.id=${contractProduct.contract.id}", type = "redirect")
})
public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct> {

    @Autowired
    private ExtCproductService extCproductService;
    @Autowired
    private FactoryService factoryService;
    //模型驱动
    private ExtCproduct model = new ExtCproduct();
    //属性驱动
    private Page page = new Page();

    /**
     * 跳转新建附件页面
     */
    @Action(value = "extCproductAction_tocreate")
    public String tocreate() {


        // 查询所有生产附件的厂商 factoryList 存入值栈中
        List<Factory> factoryList = factoryService.find(new Specification<Factory>() {
            @Override
            public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("ctype").as(String.class), "附件");
                Predicate p2 = cb.equal(root.get("state").as(Integer.class), 1);
                return cb.and(p1, p2);
                //return cb.equal(root.get("ctype").as(String.class), "附件");
            }
        });
        super.put("factoryList", factoryList);

        // 货物的附件 page 压入栈顶
        org.springframework.data.domain.Page<ExtCproduct> jpaPage = extCproductService.findPage(new Specification<ExtCproduct>() {
            @Override
            public Predicate toPredicate(Root<ExtCproduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查找与货物id相等的附件
                return cb.equal(root.get("contractProduct").get("id").as(String.class), model.getContractProduct().getId());
            }
        }, new PageRequest(this.page.getPageNo() - 1, this.page.getPageSize()));

        page.setTotalRecord(jpaPage.getTotalElements());
        page.setTotalPage(jpaPage.getTotalPages());
        page.setResults(jpaPage.getContent());
        page.setUrl("extCproductAction_tocreate");
        super.push(page);
        return "tocreate";
    }

    //保存新建附件的操作
    @Action(value = "extCproductAction_insert")
    public String insert() {
        extCproductService.saveOrUpdate(model);

        return "tolist";
    }

    /**
     * 跳转修改购销合同页面
     */
    @Action(value = "extCproductAction_toupdate")
    public String toupdate() {
        // factoryList
        List<Factory> factoryList = factoryService.find(new Specification<Factory>() {
            @Override
            public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("ctype").as(String.class), "附件");
            }
        });
        super.put("factoryList", factoryList);
        ExtCproduct extCproduct = extCproductService.findOne(model.getId());
        super.push(extCproduct);

        return "toupdate";
    }

    //执行修改操作
    @Action(value = "extCproductAction_update")
    public String update() {
        ExtCproduct extCproduct = extCproductService.findOne(model.getId());

        extCproduct.setFactory(model.getFactory());
        extCproduct.setFactoryName(model.getFactoryName());
        extCproduct.setProductNo(model.getProductNo());
        extCproduct.setProductImage(model.getProductImage());
        extCproduct.setCnumber(model.getCnumber());
        extCproduct.setPackingUnit(model.getPackingUnit());
        extCproduct.setPrice(model.getPrice());
        extCproduct.setOrderNo(model.getOrderNo());
        extCproduct.setProductDesc(model.getProductDesc());
        extCproduct.setProductRequest(model.getProductRequest());

        extCproductService.saveOrUpdate(extCproduct);

        return "tolist";
    }

    /**
     * 删除购销合同操作
     */
    @Action(value = "extCproductAction_delete")
    public String delete() {
        extCproductService.delete(model.getId().split(", "));

        return "tolist";
    }

    ////////////////////////////get、and、set///////////////////////////////
    @Override
    public ExtCproduct getModel() {
        return model;
    }

    public void setModel(ExtCproduct model) {
        this.model = model;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}


