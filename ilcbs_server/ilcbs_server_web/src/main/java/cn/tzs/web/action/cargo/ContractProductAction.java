package cn.tzs.web.action.cargo;

import cn.tzs.domain.ContractProduct;
import cn.tzs.domain.Factory;
import cn.tzs.service.ContractProductService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Namespace("/cargo")
@Results({
        @Result(name = "tocreate", location = "/WEB-INF/pages/cargo/contract/jContractProductCreate.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/cargo/contract/jContractProductUpdate.jsp"),
        @Result(name = "tolist", location = "contractProductAction_tocreate?contract.id=${contract.id}", type = "redirect")
})
public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct> {

    @Autowired
    private ContractProductService contractProductService;
    @Autowired
    private FactoryService factoryService;
    //模型驱动
    private ContractProduct model = new ContractProduct();
    //属性驱动
    private Page page = new Page();

    /**
     * 跳转新建商品页面
     */
    @Action(value = "contractProductAction_tocreate")
    public String tocreate() {
        // 查询所有生产货物的厂商 factoryList 存入值栈中
        List<Factory> factoryList = factoryService.find(new Specification<Factory>() {
            @Override
            public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("state").as(Integer.class), 1);
                Predicate p2 = cb.equal(root.get("ctype").as(String.class), "货物");
                return cb.and(p1,p2);
                //return cb.equal(root.get("ctype").as(String.class), "货物");
            }
        });
        super.put("factoryList",factoryList);
        // 订单的货物 page 压入栈顶
        org.springframework.data.domain.Page<ContractProduct> jpaPage = contractProductService.findPage(new Specification<ContractProduct>() {
            @Override
            public Predicate toPredicate(Root<ContractProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("contract").get("id").as(String.class),model.getContract().getId());
            }
        }, new PageRequest(this.page.getPageNo() - 1, this.page.getPageSize()));

        page.setTotalRecord(jpaPage.getTotalElements());
        page.setTotalPage(jpaPage.getTotalPages());
        page.setResults(jpaPage.getContent());
        page.setUrl("contractProductAction_tocreate");
        super.push(page);
        return "tocreate";
    }

    //保存新建商品的操作
    @Action(value = "contractProductAction_insert")
    public String insert() {
        contractProductService.saveOrUpdate(model);
        return "tolist";
    }

    /**
     * 跳转修改购销合同页面
     */
    @Action(value = "contractProductAction_toupdate")
    public String toupdate() {
        // factoryList
        List<Factory> factoryList = factoryService.find(new Specification<Factory>() {
            @Override
            public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("ctype").as(String.class), "货物");
            }
        });
        super.put("factoryList",factoryList);
        ContractProduct contractProduct = contractProductService.findOne(model.getId());
        super.push(contractProduct);
        return "toupdate";
    }

    //执行修改操作
    @Action(value = "contractProductAction_update")
    public String update() {
        ContractProduct contractProduct = contractProductService.findOne(model.getId());
        contractProduct.setFactory(model.getFactory());
        contractProduct.setFactoryName(model.getFactoryName());
        contractProduct.setProductNo(model.getProductNo());
        contractProduct.setProductImage(model.getProductImage());
        contractProduct.setCnumber(model.getCnumber());
        contractProduct.setPackingUnit(model.getPackingUnit());
        contractProduct.setLoadingRate(model.getLoadingRate());
        contractProduct.setBoxNum(model.getBoxNum());
        contractProduct.setPrice(model.getPrice());
        contractProduct.setOrderNo(model.getOrderNo());
        contractProduct.setProductDesc(model.getProductDesc());
        contractProduct.setProductRequest(model.getProductRequest());

        contractProductService.saveOrUpdate(contractProduct);
        return "tolist";
    }

    /**
     * 删除购销合同操作
     */
    @Action(value = "contractProductAction_delete")
    public String delete() {
        contractProductService.delete(model.getId().split(", "));
        return "tolist";
    }

    ////////////////////////////get、and、set///////////////////////////////
    @Override
    public ContractProduct getModel() {
        return model;
    }

    public void setModel(ContractProduct model) {
        this.model = model;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}


