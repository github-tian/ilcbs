package cn.tzs.service.impl;

import cn.tzs.dao.InvoiceDao;
import cn.tzs.domain.Invoice;
import cn.tzs.service.InvoiceService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class InvoiceServiceImpl implements InvoiceService {
	//spring注入dao
	@Autowired
	private InvoiceDao invoiceDao;

	public Invoice findOne(String id) {
		return invoiceDao.findOne(id);
	}

	public List<Invoice> find(Specification<Invoice> spec) {
		return invoiceDao.findAll(spec);
	}

	public Page<Invoice> findPage(Specification<Invoice> spec, Pageable pageable) {
		return invoiceDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(Invoice entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//新增
			entity.setState(1);
		}
		invoiceDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<Invoice> entitys) {
		for (Invoice obj : entitys) {
			saveOrUpdate(obj);
		}
	}

	public void deleteById(String id) {
		invoiceDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			deleteById(id);
		}
	}

}
