package cn.tzs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.tzs.domain.CustomerMenu;

public interface CustomerMenuDao extends JpaRepository<CustomerMenu, String>, JpaSpecificationExecutor<CustomerMenu> {
	
	@Query(value = "select * from (select * from CUSTOMER_MENU_P where user_name = ?1 ) where rownum <6", nativeQuery = true)
	List<CustomerMenu> findTop5MenuByUserName(String userName);

}
