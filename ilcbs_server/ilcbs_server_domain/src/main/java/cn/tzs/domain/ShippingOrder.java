package cn.tzs.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 委托管理
 */
@Entity
@Table(name = "SHIPPING_ORDER_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ShippingOrder implements Serializable {

    @Id
    @Column(name = "SHIPPING_ORDER_ID")
    @GeneratedValue(generator = "system-assigned")
    @GenericGenerator(name = "system-assigned", strategy = "assigned")
    private String Id;
    @Column(name = "ORDER_TYPE")
    private String orderType;
    @Column(name = "SHIPPER")
    private String shipper;
    @Column(name = "CONSIGNEE")
    private String consignee;
    @Column(name = "NOTIFY_PARTY")
    private String notifyParty;
    @Column(name = "LC_NO")
    private String lcNo;
    @Column(name = "PORT_OF_LOADING")
    private String portOfLoading;
    @Column(name = "PORT_OF_TRANS")
    private String portOfTrans;
    @Column(name = "PORT_OF_DISCHARGE")
    private String portOfDischarge;
    @Column(name = "LOADING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loadingDate;
    @Column(name = "LIMIT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;
    @Column(name = "IS_BATCH")
    private String isBatch;
    @Column(name = "IS_TRANS")
    private String isTrans;
    @Column(name = "COPY_NUM")
    private String copyNum;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "SPECIAL_CONDITION")
    private String specialCondition;
    @Column(name = "FREIGHT")
    private String freight;
    @Column(name = "CHECK_BY")
    private String checkBy;
    @Column(name = "STATE")
    private Integer state;

    @Column(name = "CREATE_BY")
    private String createBy;          //创建者的id
    @Column(name = "CREATE_DEPT")
    private String createDept;        //创建者所在部门的id
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;          //创建时间

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getShipper() {
        return shipper;
    }
    public void setShipper(String shipper) {
        this.shipper = shipper;
    }
    public String getConsignee() {
        return consignee;
    }
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    public String getNotifyParty() {
        return notifyParty;
    }
    public void setNotifyParty(String notifyParty) {
        this.notifyParty = notifyParty;
    }
    public String getLcNo() {
        return lcNo;
    }
    public void setLcNo(String lcNo) {
        this.lcNo = lcNo;
    }
    public String getPortOfLoading() {
        return portOfLoading;
    }
    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }
    public String getPortOfTrans() {
        return portOfTrans;
    }
    public void setPortOfTrans(String portOfTrans) {
        this.portOfTrans = portOfTrans;
    }
    public String getPortOfDischarge() {
        return portOfDischarge;
    }
    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }
    public Date getLoadingDate() {
        return loadingDate;
    }
    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }
    public Date getLimitDate() {
        return limitDate;
    }
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }
    public String getIsBatch() {
        return isBatch;
    }
    public void setIsBatch(String isBatch) {
        this.isBatch = isBatch;
    }
    public String getIsTrans() {
        return isTrans;
    }
    public void setIsTrans(String isTrans) {
        this.isTrans = isTrans;
    }
    public String getCopyNum() {
        return copyNum;
    }
    public void setCopyNum(String copyNum) {
        this.copyNum = copyNum;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSpecialCondition() {
        return specialCondition;
    }
    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
    }
    public String getFreight() {
        return freight;
    }
    public void setFreight(String freight) {
        this.freight = freight;
    }
    public String getCheckBy() {
        return checkBy;
    }
    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
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

