package cn.tzs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.tzs.domain.Finance;


public interface FinanceDao  extends JpaRepository<Finance, String>,JpaSpecificationExecutor<Finance> {

}
