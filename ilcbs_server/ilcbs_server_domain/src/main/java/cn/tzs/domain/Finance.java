package cn.tzs.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 财务管理
 */
@Entity
@Table(name = "FINANCE_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Finance implements Serializable {
    @Id
    @Column(name = "FINANCE_ID")
    @GeneratedValue(generator = "system-assigned")
    @GenericGenerator(name = "system-assigned", strategy = "assigned")
    private String id;//购销合同id
    @Column(name = "INPUT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inputDate;
    @Column(name = "INPUT_BY")
    private String inputBy;
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
    public Date getInputDate() {
        return inputDate;
    }
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
    public String getInputBy() {
        return inputBy;
    }
    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
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
