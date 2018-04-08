package cn.tzs.service.impl;

import cn.tzs.dao.ModuleDao;
import cn.tzs.domain.Module;
import cn.tzs.service.ModuleService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service("moduleServiceImpl")
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;

    public Module findOne(String id) {
        return moduleDao.findOne(id);
    }

    public List<Module> find(Specification<Module> spec) {
        return moduleDao.findAll(spec);
    }

    public Page<Module> findPage(Specification<Module> spec, Pageable pageable) {
        return moduleDao.findAll(spec, pageable);
    }

    public void saveOrUpdate(Module module) {
        if (UtilFuns.isEmpty(module.getId())){

        }else {

        }
        moduleDao.save(module);
    }

    public void saveOrUpdateAll(Collection<Module> entitys) {
        for (Module module : entitys) {
            saveOrUpdate(module);
        }
    }

    public void deleteById(String id) {
        moduleDao.delete(id);
    }

    public void delete(String[] ids) {
        for (String id : ids) {
            moduleDao.delete(id);
        }
    }

	@Override
	public Module findByCurl(String curl) {
		return moduleDao.findByCurl(curl);
	}
}
