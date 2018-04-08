package cn.tzs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.tzs.domain.AccessLog;

public interface AccessLogDao extends JpaRepository<AccessLog, String>, JpaSpecificationExecutor<AccessLog> {

	@Query(value = "select * from ACCESS_LOG_P where curl = ?1", nativeQuery = true)
	AccessLog findByCurl(String curl);

	@Query(value = "select * from (select * from ACCESS_LOG_P where user_name = ?1 order by times desc) where rownum <6", nativeQuery = true)
	List<AccessLog> findTop5MenuByUserName(String userName);



}
