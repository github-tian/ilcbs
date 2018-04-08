package cn.tzs.dao;

import cn.tzs.domain.ContractProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 创建符合spring data jpa规范的dao层接口
 */
public interface ContractProductDao extends JpaRepository<ContractProduct, String>, JpaSpecificationExecutor<ContractProduct> {

    @Query("from ContractProduct where to_char(contract.shipTime,'yyyy-MM')=?1")
    List<ContractProduct> findCpByShipTime(String shipTime);

    @Query("from ContractProduct where contract.id in (?1)")
    List<ContractProduct> findCpByContract(String[] contractIds);

    @Query("from ContractProduct where contract.id = ?1 order by factoryName")
    List<ContractProduct> findCpByContractOrderFactoryName(String cid);
}
