package cn.tzs.service.impl;

import cn.tzs.dao.ExtEproductDao;
import cn.tzs.domain.ExtEproduct;
import cn.tzs.service.ExtEproductService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ExtEproductServiceImpl implements ExtEproductService {

	@Autowired
	private ExtEproductDao extEproductDao;
	
	public ExtEproduct findOne(String id) {//根据id查询
		return extEproductDao.findOne(id);
	}

	public void saveOrUpdate(ExtEproduct extEproduct) {//保存或更新
		if(UtilFuns.isEmpty(extEproduct.getId())){  //判断是否新增，根据对象id
			
		}else{
			
		}
		extEproductDao.save(extEproduct);
	}

	public void saveOrUpdateAll(Collection<ExtEproduct> entitys) {//批量保存或更新
		for (ExtEproduct extEproduct : entitys) {
			extEproductDao.save(extEproduct);
		}
	}

	public void deleteById(String id) {//根据id删除
		extEproductDao.delete(id);
	}

	public void delete(String[] ids) {//批量删除
		for (String id : ids) {
			extEproductDao.delete(id);
		}
	}

	//根据条件查询所有
	public List<ExtEproduct> find(Specification<ExtEproduct> spec) {
		return extEproductDao.findAll(spec);
	}

	//分页查询
	public Page<ExtEproduct> findPage(Specification<ExtEproduct> spec, Pageable pageable) {
		return extEproductDao.findAll(spec, pageable);
	}

}
