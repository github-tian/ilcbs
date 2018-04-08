package cn.tzs.service;

import java.util.List;

import cn.tzs.domain.Feedback;

public interface FeedbackService extends BaseService<Feedback>{

//	根据姓名查找
	List<Feedback> findByUserName(String userName);
//	查找所有
	List<Feedback> findAll();


}
