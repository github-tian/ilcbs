package cn.tzs.service.impl;

import cn.tzs.dao.ExportDao;
import cn.tzs.dao.PackingListDao;
import cn.tzs.domain.Export;
import cn.tzs.domain.PackingList;
import cn.tzs.service.PackingListService;
import cn.tzs.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Service
public class PackingListServiceImpl implements PackingListService {
	//spring注入dao
	@Autowired
	private PackingListDao packingListDao;
	@Autowired
	private ExportDao exportDao;

	public PackingList findOne(String id) {
		return packingListDao.findOne(id);
	}

	public List<PackingList> find(Specification<PackingList> spec) {
		return packingListDao.findAll(spec);
	}

	public Page<PackingList> findPage(Specification<PackingList> spec, Pageable pageable) {
		return packingListDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(PackingList entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//新增
			entity.setId(UUID.randomUUID().toString());
			entity.setState(0);
			String[] exportIds = entity.getExportIds().split(", ");
			for (String exportId : exportIds) {
				Export export = exportDao.findOne(exportId);
				export.setState(3);
				exportDao.save(export);
			}
		}
		packingListDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<PackingList> entitys) {
		for (PackingList obj : entitys) {
			saveOrUpdate(obj);
		}
	}

	public void deleteById(String id) {
		PackingList one = packingListDao.findOne(id);
		if (one.getState() == 0) {
			packingListDao.delete(id);
		}
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			packingListDao.delete(id);
			
		}
	}

}
