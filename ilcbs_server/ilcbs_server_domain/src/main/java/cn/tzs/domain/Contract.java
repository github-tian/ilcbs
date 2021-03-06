package cn.tzs.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="CONTRACT_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Contract extends BaseEntity {
	@Id
	@Column(name="CONTRACT_ID")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;//购销合同id
	
	@OneToMany(mappedBy="contract")
	private Set<ContractProduct> contractProducts = new HashSet<ContractProduct>(0);
	
	@Column(name="OFFEROR")
	private String offeror;//收购方
	@Column(name="CONTRACT_NO")
	private String contractNo;//编号
	@Column(name="SIGNING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signingDate;//签单日期
	@Column(name="INPUT_BY")
	private String inputBy;//制单人
	@Column(name="CHECK_BY")
	private String checkBy;//审单人
	@Column(name="INSPECTOR")
	private String inspector;//验货员
	@Column(name="TOTAL_AMOUNT")
	private Double totalAmount;//总金额
	@Column(name="CREQUEST")
	private String crequest;//要求
	@Column(name="CUSTOM_NAME")
	private String customName;//客户名称
	@Column(name="SHIP_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date shipTime;//船期
	@Column(name="IMPORT_NUM")
	private Integer importNum;//重要程度
	@Column(name="DELIVERY_PERIOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryPeriod;//交货期限
	@Column(name="OLD_STATE")
	private Integer oldState;//旧的状态
	@Column(name="OUT_STATE")
	private Integer outState;//走货状态
	@Column(name="TRADE_TERMS")
	private String tradeTerms;//贸易条款
	@Column(name="PRINT_STYLE")
	private Integer printStyle;//打印版式
	@Column(name="REMARK")
	private String remark;//说明
	@Column(name="STATE")
	private Integer state;//状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOfferor() {
		return offeror;
	}
	public void setOfferor(String offeror) {
		this.offeror = offeror;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public String getInputBy() {
		return inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCrequest() {
		return crequest;
	}
	public void setCrequest(String crequest) {
		this.crequest = crequest;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public Date getShipTime() {
		return shipTime;
	}
	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}
	public Integer getImportNum() {
		return importNum;
	}
	public void setImportNum(Integer importNum) {
		this.importNum = importNum;
	}
	public Date getDeliveryPeriod() {
		return deliveryPeriod;
	}
	public void setDeliveryPeriod(Date deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}
	public Integer getOldState() {
		return oldState;
	}
	public void setOldState(Integer oldState) {
		this.oldState = oldState;
	}
	public Integer getOutState() {
		return outState;
	}
	public void setOutState(Integer outState) {
		this.outState = outState;
	}
	public String getTradeTerms() {
		return tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}
	public Integer getPrintStyle() {
		return printStyle;
	}
	public void setPrintStyle(Integer printStyle) {
		this.printStyle = printStyle;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Set<ContractProduct> getContractProducts() {
		return contractProducts;
	}
	public void setContractProducts(Set<ContractProduct> contractProducts) {
		this.contractProducts = contractProducts;
	}
}
