package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.ProductDao;
import cn.tzs.domain.Product;
import cn.tzs.service.ProductService;
import cn.tzs.utils.UtilFuns;

@Service
public class ProductServiceImpl implements ProductService {
	// spring注入dao
	@Autowired
	private ProductDao productDao;

	public List<Product> find(Specification<Product> spec) {
		return productDao.findAll(spec);
	}

	@Override
	public Product findOne(String id) {
		// TODO Auto-generated method stub
		return productDao.findOne(id);
	}

	public Page<Product> findPage(Specification<Product> spec, Pageable pageable) {
		return productDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(Product entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			// entity.setState(1);
		}
		productDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<Product> entitys) {
		for (Product obj : entitys) {
			productDao.save(obj);
		}
	}

	public void deleteById(String id) {
		productDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			productDao.delete(id);
		}
	}

}
