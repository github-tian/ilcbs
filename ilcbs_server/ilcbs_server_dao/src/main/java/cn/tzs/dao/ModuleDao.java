package cn.tzs.dao;

import cn.tzs.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 创建符合spring data jpa规范的dao层接口
 */
public interface ModuleDao extends JpaRepository<Module, String>, JpaSpecificationExecutor<Module> {
	
	@Query(value = "select * from MODULE_P where curl = ?1", nativeQuery = true)
	Module findByCurl(String curl);

}
