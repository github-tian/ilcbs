package cn.tzs.dao;

import cn.tzs.domain.ExtEproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 创建符合SpringData JPA规范的dao层接口
 * 		继承
 * 			JpaRepository：				负责基本的CRUD
 * 					T：实体类类型
 * 					ID：实体类中主键的类型
 * 			JpaSpecificationExecutor	负责复杂查询（分页）
 * 					T：实体类类型
 */
public interface ExtEproductDao extends JpaRepository<ExtEproduct, String>, JpaSpecificationExecutor<ExtEproduct> {


}
