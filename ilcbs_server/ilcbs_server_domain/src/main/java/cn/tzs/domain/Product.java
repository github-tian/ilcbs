package cn.tzs.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="PRODUCT_C")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;	  	
	
	@ManyToOne
	@JoinColumn(name="FACTORY_ID")
	private Factory factory;//购销合同对象   货物与合同   多对一 
	
	@Column(name="PRODUCT_NO")
	private String productNo;			

	@Column(name="PRODUCT_IMAGE")
	private String productImage;			
    
	@Column(name="DESCRIPTION")
	private String description;					
    
	@Column(name="FACTORY_NAME")
	private String factoryName;			
    
	@Column(name="PRICE")
	private Double price;			
    
	@Column(name="SIZE_LENGHT")
	private Double sizeLenght;			
    
	@Column(name="SIZE_WIDTH")
	private Double sizeWidth;			
    
	@Column(name="SIZE_HEIGHT")
	private Double sizeHeight;			
    
	@Column(name="COLOR")
	private String color;			//会写很多内容
    
	@Column(name="PACKING")
	private String packing;			
    
	@Column(name="PACKING_UNIT")
	private String packingUnit;			//PCS/SETS
    
	@Column(name="TYPE20")
	private Double type20;			
    
	@Column(name="TYPE40")
	private Double type40;			
    
	@Column(name="TYPE40HC")
	private Double type40hc;			
    
	@Column(name="QTY")
	private Double qty;			
    
	@Column(name="CBM")
	private Double cbm;			
    
	@Column(name="MPSIZE_LENGHT")
	private Double mpsizeLenght;			
    
	@Column(name="MPSIZE_WIDTH")
	private Double mpsizeWidth;			
    
	@Column(name="MPSIZE_HEIGHT")
	private Double mpsizeHeight;			
    
	@Column(name="REMARK")
	private String remark;			//NOTE
    
	/////////////////
	
	@Column(name="INPUT_TIME")
	private Date inputTime;			//CREATEDATE
    
	@Column(name="CREATE_BY")
	private String createBy;			
    
	@Column(name="CREATE_DEPT")
	private String createDept;			
    
	@Column(name="CREATE_TIME")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSizeLenght() {
		return sizeLenght;
	}

	public void setSizeLenght(Double sizeLenght) {
		this.sizeLenght = sizeLenght;
	}

	public Double getSizeWidth() {
		return sizeWidth;
	}

	public void setSizeWidth(Double sizeWidth) {
		this.sizeWidth = sizeWidth;
	}

	public Double getSizeHeight() {
		return sizeHeight;
	}

	public void setSizeHeight(Double sizeHeight) {
		this.sizeHeight = sizeHeight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getPackingUnit() {
		return packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	public Double getType20() {
		return type20;
	}

	public void setType20(Double type20) {
		this.type20 = type20;
	}

	public Double getType40() {
		return type40;
	}

	public void setType40(Double type40) {
		this.type40 = type40;
	}

	public Double getType40hc() {
		return type40hc;
	}

	public void setType40hc(Double type40hc) {
		this.type40hc = type40hc;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getCbm() {
		return cbm;
	}

	public void setCbm(Double cbm) {
		this.cbm = cbm;
	}

	public Double getMpsizeLenght() {
		return mpsizeLenght;
	}

	public void setMpsizeLenght(Double mpsizeLenght) {
		this.mpsizeLenght = mpsizeLenght;
	}

	public Double getMpsizeWidth() {
		return mpsizeWidth;
	}

	public void setMpsizeWidth(Double mpsizeWidth) {
		this.mpsizeWidth = mpsizeWidth;
	}

	public Double getMpsizeHeight() {
		return mpsizeHeight;
	}

	public void setMpsizeHeight(Double mpsizeHeight) {
		this.mpsizeHeight = mpsizeHeight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}			
    

	
	
	
}
