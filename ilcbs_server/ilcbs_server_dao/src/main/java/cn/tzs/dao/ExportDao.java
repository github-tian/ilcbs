package cn.tzs.dao;

import cn.tzs.domain.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 创建符合SpringData JPA规范的dao层接口
 * 		继承
 * 			JpaRepository：				负责基本的CRUD
 * 					T：实体类类型
 * 					ID：实体类中主键的类型
 * 			JpaSpecificationExecutor	负责复杂查询（分页）
 * 					T：实体类类型
 */
public interface ExportDao extends JpaRepository<Export, String>, JpaSpecificationExecutor<Export> {
	
	@Query(value = "select count(*) from EXPORT_C where state = ?1", nativeQuery = true)
	int findAcountByState(int state);


}
