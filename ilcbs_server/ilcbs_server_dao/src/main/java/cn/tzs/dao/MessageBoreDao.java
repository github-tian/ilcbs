package cn.tzs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.tzs.domain.LoginLog;
import cn.tzs.domain.MessageBore;

public interface MessageBoreDao extends JpaRepository<MessageBore, String>, JpaSpecificationExecutor<MessageBore> {




}
