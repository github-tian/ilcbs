package cn.tzs.service.impl;

import cn.tzs.dao.RoleDao;
import cn.tzs.domain.Role;
import cn.tzs.service.RoleService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role findOne(String id) {
        return roleDao.findOne(id);
    }

    public List<Role> find(Specification<Role> spec) {
        return roleDao.findAll(spec);
    }

    public Page<Role> findPage(Specification<Role> spec, Pageable pageable) {
        return roleDao.findAll(spec, pageable);
    }

    public void saveOrUpdate(Role role) {
        if (UtilFuns.isEmpty(role.getId())){

        }else {

        }
        roleDao.save(role);
    }

    public void saveOrUpdateAll(Collection<Role> entitys) {
        for (Role role : entitys) {
            saveOrUpdate(role);
        }
    }

    public void deleteById(String id) {
        roleDao.delete(id);
    }

    public void delete(String[] ids) {
        for (String id : ids) {
            roleDao.delete(id);
        }
    }
}
