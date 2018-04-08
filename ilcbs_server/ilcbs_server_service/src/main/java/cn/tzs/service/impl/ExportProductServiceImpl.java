package cn.tzs.service.impl;

import cn.tzs.dao.ExportProductDao;
import cn.tzs.domain.ExportProduct;
import cn.tzs.service.ExportProductService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ExportProductServiceImpl implements ExportProductService {

	@Autowired
	private ExportProductDao exportProductDao;
	
	public ExportProduct findOne(String id) {//根据id查询
		return exportProductDao.findOne(id);
	}

	public void saveOrUpdate(ExportProduct exportProduct) {//保存或更新
		if(UtilFuns.isEmpty(exportProduct.getId())){  //判断是否新增，根据对象id

		}else{
			
		}
		exportProductDao.save(exportProduct);
	}

	public void saveOrUpdateAll(Collection<ExportProduct> entitys) {//批量保存或更新
		for (ExportProduct exportProduct : entitys) {
			exportProductDao.save(exportProduct);
		}
	}

	public void deleteById(String id) {//根据id删除
		exportProductDao.delete(id);
	}

	public void delete(String[] ids) {//批量删除
		for (String id : ids) {
			exportProductDao.delete(id);
		}
	}

	//根据条件查询所有
	public List<ExportProduct> find(Specification<ExportProduct> spec) {
		return exportProductDao.findAll(spec);
	}

	//分页查询
	public Page<ExportProduct> findPage(Specification<ExportProduct> spec, Pageable pageable) {
		return exportProductDao.findAll(spec, pageable);
	}

}
