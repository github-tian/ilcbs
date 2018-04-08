package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.AccessLogDao;
import cn.tzs.domain.AccessLog;
import cn.tzs.service.AccessLogService;
import cn.tzs.utils.UtilFuns;

@Service("accessLogServiceImpl")
public class AccessLogServiceImpl implements AccessLogService {
	// spring注入dao
	@Autowired
	private AccessLogDao accessLogDao;

	public List<AccessLog> find(Specification<AccessLog> spec) {
		return accessLogDao.findAll(spec);
	}

	@Override
	public AccessLog findOne(String id) {
		// TODO Auto-generated method stub
		return accessLogDao.findOne(id);
	}

	public Page<AccessLog> findPage(Specification<AccessLog> spec, Pageable pageable) {
		return accessLogDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(AccessLog entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			// entity.setState(1);
		}
		accessLogDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<AccessLog> entitys) {
		for (AccessLog obj : entitys) {
			accessLogDao.save(obj);
		}
	}

	public void deleteById(String id) {
		accessLogDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			accessLogDao.delete(id);
		}
	}

	@Override
	public AccessLog findByCurl(String curl) {

		return accessLogDao.findByCurl(curl);
	}

	@Override
	public List<AccessLog> findTop5MenuByUserName(String userName) {
		return accessLogDao.findTop5MenuByUserName(userName);
	}



}
