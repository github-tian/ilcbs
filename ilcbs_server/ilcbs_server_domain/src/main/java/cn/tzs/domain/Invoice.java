package cn.tzs.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 发票管理
 */
@Entity
@Table(name = "INVOICE_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Invoice implements Serializable {
    @Id
    @Column(name = "INVOICE_ID")
    @GeneratedValue(generator = "system-assigned")
    @GenericGenerator(name = "system-assigned", strategy = "assigned")
    private String id;
    @Column(name = "SC_NO")
    private String scNo;            //packing.getExportNos S/C就是报运的合同号
    @Column(name = "BL_NO")
    private String blNo;
    @Column(name = "TRADE_TERMS")
    private String tradeTerms;
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
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getScNo() {
        return scNo;
    }
    public void setScNo(String scNo) {
        this.scNo = scNo;
    }
    public String getBlNo() {
        return blNo;
    }
    public void setBlNo(String blNo) {
        this.blNo = blNo;
    }
    public String getTradeTerms() {
        return tradeTerms;
    }
    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
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
