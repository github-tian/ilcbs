package cn.tzs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.tzs.domain.PackingList;


public interface PackingListDao  extends JpaRepository<PackingList, String>,JpaSpecificationExecutor<PackingList> {

}
