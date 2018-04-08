package cn.tzs.web.action.sysadmin;

import cn.tzs.domain.Dept;
import cn.tzs.service.DeptService;
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
import java.util.List;

@Namespace("/sysadmin")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/sysadmin/dept/jDeptList.jsp"),
        @Result(name = "toview", location = "/WEB-INF/pages/sysadmin/dept/jDeptView.jsp"),
        @Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/dept/jDeptCreate.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/dept/jDeptUpdate.jsp"),
        @Result(name = "tolist", location = "deptAction_list", type = "redirect")
})
public class DeptAction extends BaseAction implements ModelDriven<Dept> {

    @Autowired
    private DeptService deptService;
    //模型驱动
    private Dept model = new Dept();
    //属性驱动
    private Page page = new Page();

    /**
     * 查看部门列表页面
     *
     */
    @Action(value = "deptAction_list")
    public String list() {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        };
        Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Dept> jpaPage = deptService.findPage(specification, pageable);

        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("deptAction_list");
        super.push(page);
        return "list";
    }

    /**
     * 查看单个部门页面
     *
     */
    @Action(value = "deptAction_toview")
    public String toview() {
        Dept dept = deptService.findOne(model.getId());
        super.push(dept);
        return "toview";
    }

    /**
     * 跳转新建部门页面
     *
     */
    @Action(value = "deptAction_tocreate")
    public String tocreate() {
        List<Dept> deptList = deptService.find(new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        super.put("deptList", deptList);
        return "tocreate";
    }

    //保存新建部门的操作
    @Action(value = "deptAction_insert")
    public String insert() {
        deptService.saveOrUpdate(model);
        return "tolist";
    }

    /**
     * 跳转修改部门页面
     *
     */
    @Action(value = "deptAction_toupdate")
    public String toupdate() {
        List<Dept> deptList = deptService.find(new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        Dept dept = deptService.findOne(model.getId());
        super.push(dept);
        deptList.remove(dept);
        super.put("deptList", deptList);
        return "toupdate";
    }

    //执行修改操作
    @Action(value = "deptAction_update")
    public String update() {
        Dept dept = deptService.findOne(model.getId());
        //使用spring的工具类进行信息拷贝
//        BeanUtils.copyProperties(model,dept,"id","state");
        dept.setDeptName(model.getDeptName());
        dept.setParent(model.getParent());

        deptService.saveOrUpdate(dept);
        return "tolist";
    }

    /**
     * 删除部门操作
     *
     */
    @Action(value = "deptAction_delete")
    public String delete() {
        deptService.delete(model.getId().split(", "));
        return "tolist";
    }


    ////////////////////////////get、and、set///////////////////////////////
    @Override
    public Dept getModel() {
        return model;
    }

    public void setModel(Dept model) {
        this.model = model;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}


