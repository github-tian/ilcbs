package cn.tzs.service.impl;

import cn.tzs.dao.DeptDao;
import cn.tzs.domain.Dept;
import cn.tzs.service.DeptService;
import cn.tzs.utils.UtilFuns;
import cn.tzs.utils.file.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    public Dept findOne(String id) {
        return deptDao.findOne(id);
    }

    public List<Dept> find(Specification<Dept> spec) {
        return deptDao.findAll(spec);
    }

    public Page<Dept> findPage(Specification<Dept> spec, Pageable pageable) {
        return deptDao.findAll(spec, pageable);
    }

    public void saveOrUpdate(Dept dept) {
        if (UtilFuns.isEmpty(dept.getId())){
            dept.setState(1);
        }else {

        }
        deptDao.save(dept);
    }

    public void saveOrUpdateAll(Collection<Dept> entitys) {
        for (Dept dept : entitys) {
            saveOrUpdate(dept);
        }
    }

    public void deleteById(String id) {
        deptDao.delete(id);
    }

    public void delete(String[] ids) {
        for (String id : ids) {
            deleteById(id);
        }
    }
}
