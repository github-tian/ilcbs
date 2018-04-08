package cn.tzs.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.tzs.dao.FeedbackDao;
import cn.tzs.domain.Feedback;
import cn.tzs.service.FeedbackService;
import cn.tzs.utils.UtilFuns;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	// spring注入dao
	@Autowired
	private FeedbackDao feedbackDao;

	public List<Feedback> find(Specification<Feedback> spec) {
		return feedbackDao.findAll(spec);
	}

	@Override
	public Feedback findOne(String id) {
		// TODO Auto-generated method stub
		return feedbackDao.findOne(id);
	}

	public Page<Feedback> findPage(Specification<Feedback> spec, Pageable pageable) {
		return feedbackDao.findAll(spec, pageable);
	}

	public void saveOrUpdate(Feedback entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// 新增
			 entity.setState("0");
			 System.err.println("我在保存");
		}
		feedbackDao.save(entity);
	}

	public void saveOrUpdateAll(Collection<Feedback> entitys) {
		for (Feedback obj : entitys) {
			feedbackDao.save(obj);
		}
	}

	public void deleteById(String id) {
		feedbackDao.delete(id);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			feedbackDao.delete(id);
		}
	}

	@Override
	public List<Feedback> findByUserName(String userName) {
		return feedbackDao.findByUserNameOrderByCreateTime(userName);
	}

	@Override
	public List<Feedback> findAll() {
		return feedbackDao.findAllOrderByCreateTime();
		
	}

}
