package cn.tzs.dao;

import cn.tzs.domain.CompanyClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<CompanyClient, String> {

}
