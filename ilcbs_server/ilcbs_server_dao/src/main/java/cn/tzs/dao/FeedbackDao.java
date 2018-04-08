package cn.tzs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.tzs.domain.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback, String>, JpaSpecificationExecutor<Feedback> {

	// 查询所有
	@Query(value = "select * from FEEDBACK_C order by create_time desc", nativeQuery = true)
	List<Feedback> findAllOrderByCreateTime();

	// 根据姓名查询所有
	@Query(value = "select * from FEEDBACK_C where INPUT_BY = ?1 order by create_time desc", nativeQuery = true)
	List<Feedback> findByUserNameOrderByCreateTime(String userName);

}
