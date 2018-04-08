package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.LoginLogDao;
import cn.tzs.domain.LoginLog;
import cn.tzs.service.LoginLogService;
import cn.tzs.utils.UtilFuns;

@Service("loginLogServiceImpl")
public class LoginLogServiceImpl implements LoginLogService {
	// spring注入dao
	@Autowired
	private LoginLogDao loginLogDao;

	public List<LoginLog> find(Specification<LoginLog> spec) {
		return loginLogDao.findAll(spec);
	}

	@Override
	public LoginLog findOne(String id) {
		// TODO Auto-generated method stub
		return loginLogDao.findOne(id);
	}

	public Page<LoginLog> findPage(Specification<LoginLog> spec, Pageable pageable) {
		return loginLogDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(LoginLog entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			// entity.setState(1);
		}
		loginLogDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<LoginLog> entitys) {
		for (LoginLog obj : entitys) {
			loginLogDao.save(obj);
		}
	}

	public void deleteById(String id) {
		loginLogDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			loginLogDao.delete(id);
		}
	}




}
