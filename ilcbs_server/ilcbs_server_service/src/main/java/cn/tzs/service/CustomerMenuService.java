package cn.tzs.service;

import java.util.List;

import cn.tzs.domain.CustomerMenu;


public interface CustomerMenuService extends BaseService<CustomerMenu> {

	//查询前5个自定义菜单
	List<CustomerMenu> findTop5MenuByUserName(String userName);


}
