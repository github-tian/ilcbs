package cn.tzs.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.tzs.domain.AccessLog;
import cn.tzs.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface AccessLogService extends BaseService<AccessLog> {
	//通过url查询数据
	AccessLog findByCurl(String curl);
	
	//查询前5 个 常用按钮
	List<AccessLog> findTop5MenuByUserName(String userName);


}
