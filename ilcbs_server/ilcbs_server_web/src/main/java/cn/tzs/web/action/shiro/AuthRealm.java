package cn.tzs.web.action.shiro;

import cn.tzs.domain.Module;
import cn.tzs.domain.Role;
import cn.tzs.domain.User;
import cn.tzs.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("进入了授权方法");
        // 获取登录的用户信息
        User user = (User) pc.getPrimaryPrincipal();

//        String password = user.getPassword();
//        System.out.println(password);

        //认证信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //遍历得到所有认证信息
        Set<Role> roles = user.getRoles();
        for (Role role:roles) {
            Set<Module> modules = role.getModules();
            for (Module module:modules){
                // 将用户拥有的模块添加到认证信息上
                info.addStringPermission(module.getName());
            }
        }
        //返回认证信息
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("进入了认证方法");

        // 获取用户登录进来的信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 根据用户名查询数据库
        User user=null;
        final String username = token.getUsername();
        List<User> users = userService.find(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("userName").as(String.class), username);
            }
        });
        if(users!=null && users.size() > 0){
            user=users.get(0);
            // 返回一个AuthenticationInfo借口的实现类
            // 次构造三个参数分别为 principal：负责人(user)  credentials：资格(证书)  realmName：标识符
            return new SimpleAuthenticationInfo(user,user.getPassword(),user.getUserName());
        }else {
            return  null;
        }
    }
}
