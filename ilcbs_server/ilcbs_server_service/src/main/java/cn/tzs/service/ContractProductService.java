package cn.tzs.service;

import cn.tzs.domain.ContractProduct;

import java.util.List;

public interface ContractProductService extends BaseService<ContractProduct>{


    List<ContractProduct> findCpByShipTime(String shipTime);

    List<ContractProduct> findCpByContractOrderFactoryName(String cid);
}
