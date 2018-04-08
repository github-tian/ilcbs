package cn.tzs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.tzs.domain.LoginLog;

public interface LoginLogDao extends JpaRepository<LoginLog, String>, JpaSpecificationExecutor<LoginLog> {




}
