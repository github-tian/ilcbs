package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.CustomerMenuDao;
import cn.tzs.domain.CustomerMenu;
import cn.tzs.service.CustomerMenuService;
import cn.tzs.utils.UtilFuns;

@Service("customerMenuServiceImpl")
public class CustomerMenuServiceImpl implements CustomerMenuService {
	// spring注入dao
	@Autowired
	private CustomerMenuDao customerMenuDao;

	public List<CustomerMenu> find(Specification<CustomerMenu> spec) {
		return customerMenuDao.findAll(spec);
	}

	@Override
	public CustomerMenu findOne(String id) {
		// TODO Auto-generated method stub
		return customerMenuDao.findOne(id);
	}

	public Page<CustomerMenu> findPage(Specification<CustomerMenu> spec, Pageable pageable) {
		return customerMenuDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(CustomerMenu entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			// entity.setState(1);
		}
		customerMenuDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<CustomerMenu> entitys) {
		for (CustomerMenu obj : entitys) {
			customerMenuDao.save(obj);
		}
	}

	public void deleteById(String id) {
		customerMenuDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			customerMenuDao.delete(id);
		}
	}

	@Override
	public List<CustomerMenu> findTop5MenuByUserName(String userName) {
		return customerMenuDao.findTop5MenuByUserName(userName);
	}
}
