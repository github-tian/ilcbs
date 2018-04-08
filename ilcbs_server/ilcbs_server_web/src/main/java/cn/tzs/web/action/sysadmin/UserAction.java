package cn.tzs.web.action.sysadmin;

import cn.tzs.domain.Dept;
import cn.tzs.domain.Role;
import cn.tzs.domain.User;
import cn.tzs.service.DeptService;
import cn.tzs.service.RoleService;
import cn.tzs.service.UserService;
import cn.tzs.utils.Page;
import cn.tzs.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.shiro.crypto.hash.Md5Hash;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Namespace("/sysadmin")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/sysadmin/user/jUserList.jsp"),
        @Result(name = "toview", location = "/WEB-INF/pages/sysadmin/user/jUserView.jsp"),
        @Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/user/jUserCreate.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/user/jUserUpdate.jsp"),
        @Result(name = "torole", location = "/WEB-INF/pages/sysadmin/user/jUserRole.jsp"),
        @Result(name = "tolist", location = "userAction_list", type = "redirect")
})
public class UserAction extends BaseAction implements ModelDriven<User> {

    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    //模型驱动
    private User model = new User();
    //属性驱动
    private Page page = new Page();
    private String[] roleIds;


    /**
     * 查看用户列表页面
     */
    @Action(value = "userAction_list")
    public String list() {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        };
        Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
        org.springframework.data.domain.Page<User> jpaPage = userService.findPage(specification, pageable);

        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("userAction_list");
        super.push(page);
        return "list";
    }

    /**
     * 查看单个用户页面
     */
    @Action(value = "userAction_toview")
    public String toview() {
        User user = userService.findOne(model.getId());
        super.push(user);
        return "toview";
    }

    /**
     * 跳转新增用户页面
     */
    @Action(value = "userAction_tocreate")
    public String tocreate() {
        List<User> userList = userService.find(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        super.put("userList", userList);
        List<Dept> deptList = deptService.find(new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        super.put("deptList", deptList);
        return "tocreate";
    }

    //保存新建用户的操作
    @Action(value = "userAction_insert")
    public String insert() {
        userService.saveOrUpdate(model);
        return "tolist";
    }

    /**
     * 跳转修改用户页面
     */
    @Action(value = "userAction_toupdate")
    public String toupdate() {
        List<Dept> deptList = deptService.find(new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("state").as(Integer.class), 1);
            }
        });
        super.put("deptList", deptList);
        User user = userService.findOne(model.getId());
        super.push(user);
        return "toupdate";
    }

    //执行修改操作
    @Action(value = "userAction_update")
    public String update() {
        User user = userService.findOne(model.getId());

//        if (user.getUserinfo() == null) {
//            model.getUserinfo().setId(model.getId());
//            user.setUserinfo(model.getUserinfo());
//        } else {
//            user.getUserinfo().setName(model.getUserinfo().getName());
//        }
        user.setUserinfo(model.getUserinfo());
        user.getDept().setId(model.getDept().getId());
        user.setState(model.getState());

        userService.saveOrUpdate(user);
        return "tolist";
    }

    /**
     * 删除用户操作
     */
    @Action(value = "userAction_delete")
    public String delete() {
        userService.delete(model.getId().split(", "));
        return "tolist";
    }


    /**
     * 修改用户权限页面
     */
    @Action(value = "userAction_torole")
    public String torole() {
        // 用户对象压入值栈中
        User user = userService.findOne(model.getId());
        super.push(user);
        // 所有的角色集合roleList
        List<Role> roleList = roleService.find(null);
        super.put("roleList", roleList);
        // 用户拥有的所有角色 拼接成一个字符串 roleStr
        Set<Role> roles = user.getRoles();
        StringBuilder sb = new StringBuilder();
        for (Role role : roles) {
            sb.append(role.getName()).append(",");
        }
        super.put("roleStr", sb.toString());
        return "torole";
    }

    /**
     * 修改用户权限操作
     */
    @Action(value = "userAction_role")
    public String role() {
        // 查询user对象 并获取role集合
        User user = userService.findOne(model.getId());

        // 属性注入方式获取 roleIds  (String类型的数组)
        // 遍历循环
        HashSet<Role> roles = new HashSet<Role>();
        for (String roleId : roleIds) {
            roles.add(roleService.findOne(roleId));
        }
        user.setRoles(roles);
        userService.saveOrUpdate(user);
        return "tolist";
    }

    ////////////////////////////get、and、set///////////////////////////////
    @Override
    public User getModel() {
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}



