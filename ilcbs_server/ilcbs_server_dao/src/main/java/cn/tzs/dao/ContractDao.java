package cn.tzs.dao;

import cn.tzs.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 创建符合spring data jpa规范的dao层接口
 */
public interface ContractDao extends JpaRepository<Contract,String>, JpaSpecificationExecutor<Contract> {

	@Query(value = "select count(*) from CONTRACT_C where state = ?1", nativeQuery = true)
	int findAcountByState(int state);

}
