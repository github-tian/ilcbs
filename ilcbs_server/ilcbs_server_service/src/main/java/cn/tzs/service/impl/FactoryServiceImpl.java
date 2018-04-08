package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.FactoryDao;
import cn.tzs.domain.Factory;
import cn.tzs.service.FactoryService;
import cn.tzs.utils.UtilFuns;

@Service
public class FactoryServiceImpl implements FactoryService {

	@Autowired
	private FactoryDao factoryDao;
	
	public Factory findOne(String id) {//根据id查询
		return factoryDao.findOne(id);
	}

	public void saveOrUpdate(Factory factory) {//保存或更新
		if(UtilFuns.isEmpty(factory.getId())){  //判断是否新增，根据对象id
			
		}else{
			
		}
		factoryDao.save(factory);
	}

	public void saveOrUpdateAll(Collection<Factory> entitys) {//批量保存或更新
		for (Factory factory : entitys) {
			factoryDao.save(factory);
		}
	}

	public void deleteById(String id) {//根据id删除
		factoryDao.delete(id);
	}

	public void delete(String[] ids) {//批量删除
		for (String id : ids) {
			factoryDao.delete(id);
		}
	}

	//根据条件查询所有
	public List<Factory> find(Specification<Factory> spec) {
		return factoryDao.findAll(spec);
	}

	//分页查询
	public Page<Factory> findPage(Specification<Factory> spec, Pageable pageable) {
		return factoryDao.findAll(spec, pageable);
	}

}
