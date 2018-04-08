package cn.tzs.web.action.stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.tzs.service.StatChartService;
import cn.tzs.web.action.BaseAction;

@Namespace("/stat")
@Results({
        @Result(name = "factorySale", location = "/WEB-INF/pages/stat/chart/pieDonut3D.html"),
        @Result(name = "productSale", location = "/WEB-INF/pages/stat/chart/column-3d.htm"),
        @Result(name = "onlineInfo", location = "/WEB-INF/pages/stat/chart/linSmooth.jsp"),
        @Result(name = "IpTimeDate", location = "/WEB-INF/pages/stat/chart/ipTimeDate.htm")
})
public class StatCharAction extends BaseAction {

    @Autowired
    private StatChartService statChartService;

    // 跳转生产厂家的页面
    @Action(value = "statChartAction_factorysale")
    public String factorySale() {
//        List<Object []> list= statChartService.getFactorysaleData();
//        ArrayList arrayList = new ArrayList();
//        for (Object[] objects : list) {
//            HashMap map = new HashMap();
//            map.put("productNo", objects[0].toString());
//            map.put("amount", objects[1].toString());
//            arrayList.add(map);
//        }
//
//        String json = JSON.toJSONString(arrayList);
//        super.put("myChartData",json);
        return "factorySale";
    }

    //ajax请求封装数据
//    {
//        "country": "United States",
//            "visits": 9252
//    },
//    {
//        "country": "China",
//            "visits": 1882
//    }
    //生产厂家的数据
    @Action(value = "statChartAction_getFactorySaleData")
    public String getFactorySaleData() throws IOException {
        List<Object[]> list = statChartService.getFactorysaleData();

       /* ArrayList arrayList = new ArrayList();
        for (Object[] objects : list) {
            HashMap map = new HashMap();
            map.put("productNo", objects[0].toString());
            map.put("amount", objects[1].toString());
            arrayList.add(map);
        }*/
        System.out.println("111111111111111111111111________________11111111111111111111111");
        ArrayList newList = new ArrayList();
        for (Object[] objects : list) {
            HashMap map = new HashMap();
            map.put("name", objects[0].toString());
            map.put("value", objects[1].toString());
            newList.add(map);
        }
// 		for (int i = 0; i < list.size(); i++) {
//			HashMap map = new HashMap();
//			map.put("name", list.get(i++));
//			map.put("value", list.get(i));
//			
//			newList.add(map);
//		}
		
        
        
        
        String json = JSON.toJSONString(newList);
        System.out.println(json+"111111111111111111111111________________11111111111111111111111");

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
        return NONE;
    }

    //跳转产品销售排行页面
    @Action(value = "statChartAction_productsale")
    public String productSale() {
        return "productSale";
    }

    //产品销售排行的json数据
    @Action(value = "statChartAction_getProductSaleData")
    public String getProductSaleData() throws IOException {
        List<Object[]> list = statChartService.getProductSaleData();
        ArrayList<String> cpName = new ArrayList<String>();
        ArrayList<Double> cpNumber = new ArrayList<Double>();
        for (Object[] objects : list) {
            cpName.add(objects[0].toString());
            cpNumber.add(Double.parseDouble(objects[1].toString()));
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("cpName", cpName);
        map.put("cpNumber", cpNumber);

        String json = JSON.toJSONString(map);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);

        return NONE;
    }
    //跳转系统访问压力图页面
    @Action(value = "statChartAction_onlineinfo")
    public String onlineInfo() {
        return "onlineInfo";
    }

    //系统访问压力图的json数据
    @Action(value = "statChartAction_getOnlineinfoData")
    public String getOnlineinfoData() throws IOException {
        List<Object[]> list = statChartService.getOnlineinfoData();
        ArrayList<String> time = new ArrayList<String>();
        ArrayList<Double> times = new ArrayList<Double>();
        for (Object [] object : list) {
            time.add(object[0].toString());
            times.add(Double.parseDouble(object[1].toString()));
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("oTime", time);
        map.put("oTimes", times);

        String json = JSON.toJSONString(map);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);

        return NONE;
    }
    
    
    //系统访问压力图的json数据
    @Action(value = "statChartAction_getIpTimeDate")
    public String getIpTimeDate() throws IOException {
    	
    	 return "IpTimeDate";
    }
    
    
    
    
    
    
    //系统ip访问图的json数据
    @Action(value = "statChartAction_getIpTimeDateInfo")
    public String getIpTimeDateInfo() throws IOException {
    	List<Object[]> list = statChartService.getIpTimeDate();
    	
    	ArrayList<String> time = new ArrayList<String>();
    	ArrayList<Double> times = new ArrayList<Double>();
    	for (Object [] object : list) {
    		time.add(object[0].toString());
    		times.add(Double.parseDouble(object[1].toString()));
    	}
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("ipActions", time);
    	map.put("ipTimes", times);
    	
    	String json = JSON.toJSONString(map);
    	
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("utf-8");
    	response.getWriter().write(json);
    	
    	return NONE;
    }


}
