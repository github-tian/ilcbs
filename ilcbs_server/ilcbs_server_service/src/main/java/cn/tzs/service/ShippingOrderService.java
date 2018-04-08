package cn.tzs.service;

import cn.tzs.domain.ShippingOrder;

public interface ShippingOrderService extends BaseService<ShippingOrder> {

	int findAcountByState(int state);


}
