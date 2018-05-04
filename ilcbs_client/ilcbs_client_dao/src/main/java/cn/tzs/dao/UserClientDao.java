package cn.tzs.dao;

import cn.tzs.domain.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClientDao extends JpaRepository<UserClient, String> {
	
	UserClient findByUserNameAndPassword(String userName, String password);

	UserClient findByEmail(String email);
}
