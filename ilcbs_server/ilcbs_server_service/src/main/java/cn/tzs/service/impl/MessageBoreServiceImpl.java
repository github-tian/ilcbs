package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.MessageBoreDao;
import cn.tzs.domain.MessageBore;
import cn.tzs.service.MessageBoreService;
import cn.tzs.utils.UtilFuns;

@Service
public class MessageBoreServiceImpl implements MessageBoreService {
	// spring注入dao
	@Autowired
	private MessageBoreDao messageBoreDao;

	public List<MessageBore> find(Specification<MessageBore> spec) {
		return messageBoreDao.findAll(spec);
	}

	@Override
	public MessageBore findOne(String id) {
		// TODO Auto-generated method stub
		return messageBoreDao.findOne(id);
	}

	public Page<MessageBore> findPage(Specification<MessageBore> spec, Pageable pageable) {
		return messageBoreDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(MessageBore entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			// entity.setState(1);
		}
		messageBoreDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<MessageBore> entitys) {
		for (MessageBore obj : entitys) {
			messageBoreDao.save(obj);
		}
	}

	public void deleteById(String id) {
		messageBoreDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			messageBoreDao.delete(id);
		}
	}




}
