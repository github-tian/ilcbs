package cn.tzs.service;

import cn.tzs.domain.Contract;

public interface ContractService extends BaseService<Contract>{

	//根据状态查询个数
	int findAcountByState(int state);


}
