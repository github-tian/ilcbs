package cn.tzs.web.action.sysadmin;

import cn.tzs.domain.Dept;
import cn.tzs.domain.Module;
import cn.tzs.service.DeptService;
import cn.tzs.service.ModuleService;
import cn.tzs.utils.Page;
import cn.tzs.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;
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

@Namespace("/sysadmin")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/sysadmin/module/jModuleList.jsp"),
        @Result(name = "toview", location = "/WEB-INF/pages/sysadmin/module/jModuleView.jsp"),
        @Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/module/jModuleCreate.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/module/jModuleUpdate.jsp"),
        @Result(name = "tolist", location = "moduleAction_list", type = "redirect")
})
public class ModuleAction extends BaseAction implements ModelDriven<Module> {

    @Autowired
    private ModuleService moduleService;

    //模型驱动
    private Module model = new Module();
    //属性驱动
    private Page page = new Page();

    /**
     * 查看用户列表页面
     *
     */
    @Action(value = "moduleAction_list")
    public String list() {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        };
        Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Module> jpaPage = moduleService.findPage(specification, pageable);

        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("moduleAction_list");
        super.push(page);
        return "list";
    }

    /**
     * 查看单个用户页面
     *
     */
    @Action(value = "moduleAction_toview")
    public String toview() {
        Module module = moduleService.findOne(model.getId());
        super.push(module);
        return "toview";
    }

    /**
     * 跳转新增用户页面
     *
     */
    @Action(value = "moduleAction_tocreate")
    public String tocreate() {
        return "tocreate";
    }

    //保存新建用户的操作
    @Action(value = "moduleAction_insert")
    public String insert() {
        moduleService.saveOrUpdate(model);
        return "tolist";
    }

    /**
     * 跳转修改用户页面
     *
     */
    @Action(value = "moduleAction_toupdate")
    public String toupdate() {
        Module module = moduleService.findOne(model.getId());
        super.push(module);
        return "toupdate";
    }

    //执行修改操作
    @Action(value = "moduleAction_update")
    public String update() {
        Module module = moduleService.findOne(model.getId());

        BeanUtils.copyProperties(model,module,"id","createTime","updateTime");

        module.setCreateTime(new Date());
        module.setUpdateTime(new Date());

        moduleService.saveOrUpdate(module);
        return "tolist";
    }

    /**
     * 删除用户操作
     *
     */
    @Action(value = "moduleAction_delete")
    public String delete() {
        moduleService.delete(model.getId().split(", "));
        return "tolist";
    }


    ////////////////////////////get、and、set///////////////////////////////
    @Override
    public Module getModel() {
        return model;
    }

    public void setModel(Module model) {
        this.model = model;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}


