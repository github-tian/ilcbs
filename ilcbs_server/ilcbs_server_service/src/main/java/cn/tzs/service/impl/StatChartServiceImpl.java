package cn.tzs.service.impl;

import cn.tzs.dao.StatChartDao;
import cn.tzs.service.StatChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statChartService")
public class StatChartServiceImpl implements StatChartService {
    @Autowired
    private StatChartDao statChartDao;


    public List getFactorysaleData() {
        return statChartDao.getFactorysaleData();

    }

    public List<Object[]> getProductSaleData() {
        return statChartDao.getProductSaleData();
    }

    public List<Object[]> getOnlineinfoData() {
        return statChartDao.getOnlineinfoData();
    }

	@Override
	public List<Object[]> getIpTimeDate() {
		return statChartDao.getIpTimeDate();
	}
}
