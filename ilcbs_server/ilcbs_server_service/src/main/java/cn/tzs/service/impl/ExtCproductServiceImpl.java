package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import cn.tzs.dao.ContractDao;
import cn.tzs.domain.Contract;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.ExtCproductDao;
import cn.tzs.domain.ExtCproduct;
import cn.tzs.service.ExtCproductService;
import cn.tzs.utils.UtilFuns;

@Service
public class ExtCproductServiceImpl implements ExtCproductService {

	@Autowired
	private ExtCproductDao extCproductDao;
	@Autowired
	private ContractDao contractDao;
	
	public ExtCproduct findOne(String id) {//根据id查询
		return extCproductDao.findOne(id);
	}

	public void saveOrUpdate(ExtCproduct extCproduct) {//保存或更新
		if(UtilFuns.isEmpty(extCproduct.getId())){  //判断是否新增，根据对象id
			//if (UtilFuns.isNotEmpty(extCproduct.getContractProduct().getContract().getId())) {
				Contract contract = contractDao.findOne(extCproduct.getContractProduct().getContract().getId());
				double amount=0.0;
				if (UtilFuns.isNotEmpty(extCproduct.getPrice())&&UtilFuns.isNotEmpty(extCproduct.getCnumber())) {
					amount= extCproduct.getPrice() * extCproduct.getCnumber();
				}
				contract.setTotalAmount(contract.getTotalAmount()+amount);
				extCproduct.setAmount(amount);
				contractDao.save(contract);
			//}
		}else{
			if (UtilFuns.isNotEmpty(extCproduct.getContractProduct().getContract().getId())) {
				Contract contract = contractDao.findOne(extCproduct.getContractProduct().getContract().getId());
				Double oldAmount = extCproduct.getAmount();
				double amount=0.0;
				if (UtilFuns.isNotEmpty(extCproduct.getPrice())&&UtilFuns.isNotEmpty(extCproduct.getCnumber())) {
					amount= extCproduct.getPrice() * extCproduct.getCnumber();
				}
				contract.setTotalAmount(contract.getTotalAmount()+amount-oldAmount);
				extCproduct.setAmount(amount);
				contractDao.save(contract);
			}
		}
		extCproductDao.save(extCproduct);
	}

	public void saveOrUpdateAll(Collection<ExtCproduct> entitys) {//批量保存或更新
		for (ExtCproduct extCproduct : entitys) {
			extCproductDao.save(extCproduct);
		}
	}

	public void deleteById(String id) {//根据id删除
		ExtCproduct extCproduct = findOne(id);
		Contract contract = extCproduct.getContractProduct().getContract();
		contract.setTotalAmount(contract.getTotalAmount()-extCproduct.getAmount());
		extCproductDao.delete(id);
	}

	public void delete(String[] ids) {//批量删除
		for (String id : ids) {
			deleteById(id);
		}
	}

	//根据条件查询所有
	public List<ExtCproduct> find(Specification<ExtCproduct> spec) {
		return extCproductDao.findAll(spec);
	}

	//分页查询
	public Page<ExtCproduct> findPage(Specification<ExtCproduct> spec, Pageable pageable) {
		return extCproductDao.findAll(spec, pageable);
	}

}
