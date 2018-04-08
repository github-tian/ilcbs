package cn.tzs.service.impl;

import cn.tzs.dao.UserDao;
import cn.tzs.domain.User;
import cn.tzs.service.UserService;
import cn.tzs.utils.UtilFuns;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    public User findOne(String id) {
        return userDao.findOne(id);
    }

    // 授权操作 过滤器链 注解方式配置
    //@RequiresPermissions("部门管理")
    public List<User> find(Specification<User> spec) {
        return userDao.findAll(spec);
    }

    public Page<User> findPage(Specification<User> spec, Pageable pageable) {
        return userDao.findAll(spec, pageable);
    }

    public void saveOrUpdate(User user) {
        if (UtilFuns.isEmpty(user.getId())){
            String uid = UUID.randomUUID().toString();
            user.setId(uid);
            user.getUserinfo().setId(uid);
            String password=user.getPassword();
            // 用户密码转译
            Md5Hash md5Pwd = new Md5Hash(password, user.getUserName(), 2);
            user.setPassword(md5Pwd.toString());
            userDao.save(user);


            try {
                //设置发送邮件的内容
                simpleMailMessage.setTo(user.getUserinfo().getEmail());
                simpleMailMessage.setSubject("入职欢迎邮件");
                simpleMailMessage.setText("您好,您的登录用户名是:"+user.getUserName() + ",密码是:"+password);
                // 发送邮件操作
                javaMailSender.send(simpleMailMessage);
            } catch (MailException e) {
                e.printStackTrace();
            }


        }else {
            userDao.save(user);
        }
        //userDao.save(user);
    }

    public void saveOrUpdateAll(Collection<User> entitys) {
        for (User user : entitys) {
            saveOrUpdate(user);
        }
    }

    public void deleteById(String id) {
        userDao.delete(id);
    }

    public void delete(String[] ids) {
        for (String id : ids) {
            userDao.delete(id);
        }
    }
}
