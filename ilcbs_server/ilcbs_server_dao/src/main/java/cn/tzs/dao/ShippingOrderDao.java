package cn.tzs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.tzs.domain.ShippingOrder;


public interface ShippingOrderDao  extends JpaRepository<ShippingOrder, String>,JpaSpecificationExecutor<ShippingOrder> {

	@Query(value = "select count(*) from SHIPPING_ORDER_C where state = ?1", nativeQuery = true)
	int shippingOrderService(int state);

}
