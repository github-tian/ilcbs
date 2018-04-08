package cn.tzs.service;

import java.util.List;

public interface StatChartService {


    List getFactorysaleData();

    List<Object[]> getProductSaleData();

    List<Object[]> getOnlineinfoData();

	List<Object[]> getIpTimeDate();
}
