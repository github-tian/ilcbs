package cn.tzs.web.action.baseinfo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.ModelDriven;

import cn.tzs.domain.Product;
import cn.tzs.service.FactoryService;
import cn.tzs.service.ProductService;
import cn.tzs.web.action.BaseAction;

@Namespace("/baseinfo")
@Results({ @Result(name = "factoryList", type = "dispatcher", location = "/WEB-INF/pages/baseinfo/jFactoryList.jsp") })
public class FactoryProductAction extends BaseAction implements ModelDriven<Product> {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FactoryService  factoryService;

	private Product model = new Product();
	private String youyigeId;

	////////////////////// ajax请求获取工厂下的产品信息////////////////////////
	@Action("factoryProductAction_getFactoryProductbyId")
	public String getFactoryProductbyId() throws Exception {
		
		//System.out.println("+++++++++++++++++++"+youyigeId);
		List<Product> products = productService.find(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("factory").get("id").as(String.class), youyigeId);
			}
		});
		
		// 规定哪些属性进行json的转换
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter("id","productNo","productImage","description","factoryName");
		
		// 禁止引用的方式
		// String jsonString = JSON.toJSONString(s, SerializerFeature.DisableCircularReferenceDetect);
		String returnStr = JSON.toJSONString(products,filter,SerializerFeature.DisableCircularReferenceDetect);
		
		//System.out.println("--------------------returnStr-------------------"+returnStr);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(returnStr);

		return NONE;
	}
	////////////////////// ajax请求获取工厂下的产品信息////////////////////////
	@Action("factoryProductAction_getSetProductDatabyId")
	public String getSetProductDatabyId() throws Exception {
		List<Product> products = productService.find(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("id").as(String.class), model.getId());
			}
		});
		
		// 规定哪些属性进行json的转换
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter("id","productNo","productImage","description","factoryName","price");
		
		// 禁止引用的方式
		// String jsonString = JSON.toJSONString(s, SerializerFeature.DisableCircularReferenceDetect);
		String returnStr = JSON.toJSONString(products.get(0),filter,SerializerFeature.DisableCircularReferenceDetect);
		
		System.out.println(returnStr);
		System.out.println("--------------------returnStr-------------------"+returnStr);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(returnStr);
		
		
		return NONE;
	}

	/////////////////////// get/set////////////////////////////

	

	public Product getModel() {
		return model;
	}

	public String getYouyigeId() {
		return youyigeId;
	}

	public void setYouyigeId(String youyigeId) {
		this.youyigeId = youyigeId;
	}

	public void setModel(Product model) {
		this.model = model;
	}

	
}
