package cn.tzs.service;

import cn.tzs.domain.Module;

public interface ModuleService extends BaseService<Module>{

	//通过curl查询一个模块信息
	Module findByCurl(String curl);


}
