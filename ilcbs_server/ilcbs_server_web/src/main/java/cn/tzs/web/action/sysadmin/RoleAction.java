package cn.tzs.web.action.sysadmin;

import cn.tzs.domain.Module;
import cn.tzs.domain.Role;
import cn.tzs.service.ModuleService;
import cn.tzs.service.RoleService;
import cn.tzs.service.UserService;
import cn.tzs.utils.Page;
import cn.tzs.utils.UtilFuns;
import cn.tzs.web.action.BaseAction;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Namespace("/sysadmin")
@Results({
        @Result(name = "list", location = "/WEB-INF/pages/sysadmin/role/jRoleList.jsp"),
        @Result(name = "toview", location = "/WEB-INF/pages/sysadmin/role/jRoleView.jsp"),
        @Result(name = "tocreate", location = "/WEB-INF/pages/sysadmin/role/jRoleCreate.jsp"),
        @Result(name = "toupdate", location = "/WEB-INF/pages/sysadmin/role/jRoleUpdate.jsp"),
        @Result(name = "tomodule", location = "/WEB-INF/pages/sysadmin/role/jRoleModule.jsp"),
        @Result(name = "tolist", location = "roleAction_list", type = "redirect")
})
public class RoleAction extends BaseAction implements ModelDriven<Role> {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;
    //模型驱动
    private Role model = new Role();
    //属性驱动
    private Page page = new Page();
    private String moduleIds;

    /**
     * 查看角色列表页面
     */
    @Action(value = "roleAction_list")
    public String list() {
        Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Role> jpaPage = roleService.findPage(null, pageable);

        page.setTotalPage(jpaPage.getTotalPages());
        page.setTotalRecord(jpaPage.getTotalElements());
        page.setResults(jpaPage.getContent());
        //设置url
        page.setUrl("roleAction_list");
        super.push(page);
        return "list";
    }

    /**
     * 查看单个角色页面
     */
    @Action(value = "roleAction_toview")
    public String toview() {
        Role role = roleService.findOne(model.getId());
        super.push(role);
        return "toview";
    }

    /**
     * 跳转新建角色页面
     */
    @Action(value = "roleAction_tocreate")
    public String tocreate() {
        return "tocreate";
    }

    //保存新建角色的操作
    @Action(value = "roleAction_insert")
    public String insert() {
        roleService.saveOrUpdate(model);
        return "tolist";
    }

    /**
     * 跳转修改角色页面
     */
    @Action(value = "roleAction_toupdate")
    public String toupdate() {
        Role role = roleService.findOne(model.getId());
        this.push(role);
        return "toupdate";
    }

    //执行修改操作
    @Action(value = "roleAction_update")
    public String update() {
        Role role = roleService.findOne(model.getId());
        //使用spring的工具类进行信息拷贝
        BeanUtils.copyProperties(model, role, "id");
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
//        role.setName(model.getName());
//        role.setRemark(model.getRemark());
        roleService.saveOrUpdate(role);
        return "tolist";
    }

    /**
     * 删除角色操作
     */
    @Action(value = "roleAction_delete")
    public String delete() {
        roleService.delete(model.getId().split(", "));
        return "tolist";
    }

    /**
     * 角色权限操作页面
     */
    @Action(value = "roleAction_tomodule")
    public String tomodule() {
        // 查询所要修改的角色
        Role role = roleService.findOne(model.getId());
        super.push(role);
        return "tomodule";
    }

    /**
     * 角色权限操作异步请求获取所有模块节点
     * 格式：[{ "id":"11", "pId":"1", "name":"随意勾选 1-1"},{ "id":111, "pId":11, "name":"随意勾选 1-1-1","checked":true}]
     */
    @Action(value = "roleAction_genzTreeNodes")
    public String genzTreeNodes() throws IOException {
        String redisStr = redisTemplate.opsForValue().get("genzTreeNodes_" + model.getId());

        if (UtilFuns.isEmpty(redisStr)) {
            // 查询此用户的信息
            Role role = roleService.findOne(model.getId());
            Set<Module> modules = role.getModules();
            // 查询所有模块
            List<Module> moduleList = moduleService.find(null);
            ArrayList returnList = new ArrayList();
            for (Module module : moduleList) {
                HashMap returnMap = new HashMap();
                returnMap.put("id", module.getId());
                returnMap.put("pId", module.getParentId());
                returnMap.put("name", module.getName());
                if(modules.contains(module)){
                    returnMap.put("checked", true);
                }
                returnList.add(returnMap);
            }
            redisStr = JSON.toJSONString(returnList);

            redisTemplate.opsForValue().set("genzTreeNodes_" + model.getId(),redisStr);
            System.out.println(redisStr);
            System.out.println("从数据库中获取数据================");
        }else{
            System.out.println("从redis中获取数据================");
        }
        System.out.println("======返回的数据=="+redisStr);

        // 获取response对象
        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置编码格式
        response.setCharacterEncoding("utf-8");
        response.setHeader("cache-control", "no-cache");
        response.getWriter().write(redisStr);

        return NONE;
    }

    /**
     * 修改角色权限操作
     */
    @Action(value = "roleAction_module")
    public String module() {
        redisTemplate.delete("genzTreeNodes_" + model.getId());
        // 获取当前role对象
        Role role = roleService.findOne(model.getId());

        // 循环得到权限模块
        HashSet<Module> modules = new HashSet<Module>();
        for (String moduleId : moduleIds.split(",")) {
            modules.add(moduleService.findOne(moduleId));
        }
        role.setModules(modules);

        System.out.println(moduleIds);
        System.out.println(role.getModules());
        //保存到数据库
        roleService.saveOrUpdate(role);

        return "tolist";
    }

    ////////////////////////////get、and、set///////////////////////////////
    @Override
    public Role getModel() {
        return model;
    }

    public void setModel(Role model) {
        this.model = model;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(String moduleIds) {
        this.moduleIds = moduleIds;
    }
}




