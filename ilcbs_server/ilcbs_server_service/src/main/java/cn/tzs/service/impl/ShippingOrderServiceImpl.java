package cn.tzs.service.impl;

import cn.tzs.dao.ShippingOrderDao;
import cn.tzs.domain.ShippingOrder;
import cn.tzs.service.ShippingOrderService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class ShippingOrderServiceImpl implements ShippingOrderService {
	//spring注入dao
	@Autowired
	private ShippingOrderDao shippingOrderDao;

	public ShippingOrder findOne(String id) {
		return shippingOrderDao.findOne(id);
	}

	public List<ShippingOrder> find(Specification<ShippingOrder> spec) {
		return shippingOrderDao.findAll(spec);
	}

	public Page<ShippingOrder> findPage(Specification<ShippingOrder> spec, Pageable pageable) {
		return shippingOrderDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(ShippingOrder entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//新增
			entity.setState(1);
		}
		shippingOrderDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<ShippingOrder> entitys) {
		for (ShippingOrder obj : entitys) {
			saveOrUpdate(obj);
		}
	}

	public void deleteById(String id) {
		shippingOrderDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			deleteById(id);
		}
	}

	@Override
	public int findAcountByState(int state) {
		return shippingOrderDao.shippingOrderService(state);
	}

}
