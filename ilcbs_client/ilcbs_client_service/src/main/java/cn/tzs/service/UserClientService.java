package cn.tzs.service;

import cn.tzs.domain.UserClient;

import java.io.Serializable;

public interface UserClientService {
	
	//获取一条记录
	public UserClient get(Serializable id);
	//新增和修改保存
	public  void saveOrUpdate(UserClient entity);
	
	//单条删除，按id
	public  void deleteById(Serializable id);
	
	public UserClient findByUserNameAndPassword(String userName, String password);
	
	public UserClient findByEmail(String email);
	
}
