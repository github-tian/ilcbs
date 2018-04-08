package cn.tzs.service.impl;

import cn.tzs.dao.FinanceDao;
import cn.tzs.domain.Finance;
import cn.tzs.service.FinanceService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class FinanceServiceImpl implements FinanceService {
	//spring注入dao
	@Autowired
	private FinanceDao financeDao;

	public Finance findOne(String id) {
		return financeDao.findOne(id);
	}

	public List<Finance> find(Specification<Finance> spec) {
		return financeDao.findAll(spec);
	}


	public Page<Finance> findPage(Specification<Finance> spec, Pageable pageable) {
		return financeDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(Finance entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//新增
			entity.setState(1);
		}
		financeDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<Finance> entitys) {
		for (Finance obj : entitys) {
			saveOrUpdate(obj);
		}
	}

	public void deleteById(String id) {
		financeDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			deleteById(id);
		}
	}

}
