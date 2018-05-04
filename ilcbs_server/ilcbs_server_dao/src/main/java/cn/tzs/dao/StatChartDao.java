package cn.tzs.dao;

import cn.tzs.domain.Contract;
import cn.tzs.domain.ContractProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 创建符合spring data jpa规范的dao层接口
 */
public interface StatChartDao extends JpaRepository<ContractProduct, String>, JpaSpecificationExecutor<ContractProduct> {

    @Query(value = "select factory_name,sum(amount) from CONTRACT_PRODUCT_C group by factory_name order by sum(amount) desc", nativeQuery = true)
    List<Object[]> getFactorysaleData();

    @Query(value = "select * from (select product_no , nvl(sum(cnumber),0) from CONTRACT_PRODUCT_C group by product_no order by nvl(sum(cnumber),0) desc) where rownum < 16", nativeQuery = true)
    List<Object[]> getProductSaleData();

    @Query(value = "select a1,nvl(ch,0) from ONLINE_INFO_T t,(select to_char(login_time,'HH24') th,count(*) ch from LOGIN_LOG_P group by to_char(login_time,'HH24')) h where t.a1 = h.th(+) order by t.a1", nativeQuery = true)
    List<Object[]> getOnlineinfoData();

    @Query(value = "select ip_address,count(*) from LOGIN_LOG_P group by ip_address order by count(*) desc", nativeQuery = true)
	List<Object[]> getIpTimeDate();
}
