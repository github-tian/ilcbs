package cn.tzs.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 装箱管理
 */
@Entity
@Table(name = "PACKING_LIST_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PackingList implements Serializable {
    @Id
    @Column(name = "PACKING_LIST_ID")
    @GeneratedValue(generator = "system-assigned")
    @GenericGenerator(name = "system-assigned", strategy = "assigned")
    private String id;//购销合同id

    @Column(name = "SELLER")
    private String seller;              //卖方
    @Column(name = "BUYER")
    private String buyer;               //买方
    @Column(name="INVOICE_NO")
    private String invoiceNo;           //发票号 ---> 	一对一
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;           //发票日期
    @Column(name = "MARKS")
    private String marks;               //唛头

    @Column(name = "DESCRIPTIONS")
    private String descriptions;         //描述
    @Column(name = "EXPORT_IDS")
    private String exportIds;           //EXPORT_IDS
    @Column(name = "EXPORT_NOS")
    private String exportNos;           //EXPORT_NOS
    @Column(name = "STATE")
    private Integer state;              //状态

    @Column(name = "CREATE_BY")
    private String createBy;          //创建者的id
    @Column(name = "CREATE_DEPT")
    private String createDept;        //创建者所在部门的id
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;          //创建时间

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PACKING_LIST_ID")
    private Invoice invoice;            //装箱与发票   一对一
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PACKING_LIST_ID")
    private ShippingOrder shippingOrder;  //装箱与委托   一对一
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PACKING_LIST_ID")
    private Finance finance;            //装箱与财务   一对一

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
    public String getBuyer() {
        return buyer;
    }
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
    public String getInvoiceNo() {
        return invoiceNo;
    }
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
    public Date getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getMarks() {
        return marks;
    }
    public void setMarks(String marks) {
        this.marks = marks;
    }
    public String getDescriptions() {
        return descriptions;
    }
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    public String getExportIds() {
        return exportIds;
    }
    public void setExportIds(String exportIds) {
        this.exportIds = exportIds;
    }
    public String getExportNos() {
        return exportNos;
    }
    public void setExportNos(String exportNos) {
        this.exportNos = exportNos;
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
    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public ShippingOrder getShippingOrder() {
        return shippingOrder;
    }
    public void setShippingOrder(ShippingOrder shippingOrder) {
        this.shippingOrder = shippingOrder;
    }
    public Finance getFinance() {
        return finance;
    }
    public void setFinance(Finance finance) {
        this.finance = finance;
    }
}
