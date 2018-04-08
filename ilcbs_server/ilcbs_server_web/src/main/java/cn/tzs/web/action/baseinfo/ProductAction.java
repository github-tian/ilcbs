package cn.tzs.web.action.baseinfo;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.opensymphony.xwork2.ModelDriven;

import cn.tzs.domain.Factory;
import cn.tzs.domain.Product;
import cn.tzs.domain.User;
import cn.tzs.service.FactoryService;
import cn.tzs.service.ProductService;
import cn.tzs.utils.Page;
import cn.tzs.utils.SysConstant;
import cn.tzs.web.action.BaseAction;

@Namespace("/baseinfo")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/baseinfo/product/jProductList.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/baseinfo/product/jProductView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/baseinfo/product/jProductCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/baseinfo/product/jProductUpdate.jsp"),
		@Result(name = "torole", location = "/WEB-INF/pages/baseinfo/product/jProductRole.jsp"),
		@Result(name = "tolist", location = "productAction_list", type = "redirect") })
public class ProductAction extends BaseAction<Product> implements ModelDriven<Product> {

	
	
	
	@Autowired
	private FactoryService factoryService;
	@Autowired
	private ProductService productService;
	// 模型驱动
	private Product model = new Product();
	// 属性驱动
	private Page page = new Page();
	private static String fid;
	private File productImageFile;
	/**
	 * 首次跳转 查看产品列表页面
	 *
	 */
	@Action(value = "productAction_tolist")
	public String tolist() {
		System.out.println("***********************************" + fid);

		try {
			Specification spec = new Specification() {
				@Override
				public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

					return cb.equal(root.get("factory").get("id").as(String.class), fid);
				}
			};
			Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
			org.springframework.data.domain.Page<Product> jpaPage = productService.findPage(spec, pageable);

			page.setTotalPage(jpaPage.getTotalPages());
			page.setTotalRecord(jpaPage.getTotalElements());
			page.setResults(jpaPage.getContent());
			// 设置url
			page.setUrl("productAction_list");
			super.push(page);

		} catch (Exception e) {
			return "list";
		}
		return "list";
	}

	/**
	 * 查询列表
	 */
	@Action(value = "productAction_list")
	public String list() {
		Specification spec = new Specification() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

				return cb.equal(root.get("factory").get("id").as(String.class), fid);
			}
		};
		Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
		org.springframework.data.domain.Page<Product> jpaPage = productService.findPage(spec, pageable);

		page.setTotalPage(jpaPage.getTotalPages());
		page.setTotalRecord(jpaPage.getTotalElements());
		page.setResults(jpaPage.getContent());
		// 设置url
		page.setUrl("productAction_list");
		super.push(page);

		return "list";
	}

	/**
	 * 查看单个产品页面
	 *
	 */
	@Action(value = "productAction_toview")
	public String toview() {
		Product product = productService.findOne(model.getId());
		super.push(product);
		return "toview";
	}

	/**
	 * 跳转新建产品页面
	 *
	 */
	@Action(value = "productAction_tocreate")
	public String tocreate() {

		Factory factory = factoryService.findOne(fid);

		super.put("factoryName", factory.getFactoryName());
		return "tocreate";
	}

	// 保存新建产品的操作
	@Action(value = "productAction_insert")
	public String insert() throws IOException {
		// 处理图片
		String path = ServletActionContext.getServletContext().getRealPath("/pImages");


		
		String imageFilename="/"+UUID.randomUUID().toString()+".jpg";
		File destFile = new File(path, imageFilename);
		FileUtils.copyFile(productImageFile, destFile);
		FileUtils.copyFile(productImageFile, new File("E:/develop/javaweb/ilcbs/ilcbs_parent/ilcbs_server/ilcbs_server_web/src/main/webapp/pImages",imageFilename));

		
		model.setProductImage("pImages"+imageFilename);
		// 产品信息写入保存
		Factory factory = factoryService.findOne(fid);

		model.setFactory(factory);
		model.setCreateTime(new Date());
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		model.setCreateBy(user.getUserName());
		model.setInputTime(new Date());
		productService.saveOrUpdate(model);
		return "tolist";
	}

	/**
	 * 跳转修改产品页面
	 *
	 */
	@Action(value = "productAction_toupdate")
	public String toupdate() {

		Product product = productService.findOne(model.getId());
		super.push(product);

		return "toupdate";
	}

	// 执行修改操作
	@Action(value = "productAction_update")
	public String update() {
		Product product = productService.findOne(model.getId());

		product.setDescription(model.getDescription());
		product.setPrice(model.getPrice());
		product.setSizeLenght(model.getSizeLenght());
		product.setSizeWidth(model.getSizeWidth());
		product.setSizeHeight(model.getSizeHeight());
		product.setPackingUnit(model.getPackingUnit());
		product.setCbm(model.getCbm());
		product.setMpsizeLenght(model.getMpsizeLenght());
		product.setMpsizeWidth(model.getMpsizeWidth());
		product.setMpsizeHeight(model.getMpsizeHeight());
		product.setRemark(model.getRemark());

		productService.saveOrUpdate(product);
		return "tolist";
	}

	/**
	 * 删除产品操作
	 *
	 */
	@Action(value = "productAction_delete")
	public String delete() {
		productService.delete(model.getId().split(", "));
		return "tolist";
	}

	//////////////////////////// get、and、set///////////////////////////////
	@Override
	public Product getModel() {
		return model;
	}

	public void setModel(Product model) {
		this.model = model;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public File getProductImageFile() {
		return productImageFile;
	}

	public void setProductImageFile(File productImageFile) {
		this.productImageFile = productImageFile;
	}

}
