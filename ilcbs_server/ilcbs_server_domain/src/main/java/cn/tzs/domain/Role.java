package cn.tzs.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ROLE_P")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Role extends BaseEntity {
	@Id
	@Column(name="ROLE_ID")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;//角色id
	
	@ManyToMany(mappedBy="roles")
	private Set<User> users = new HashSet<User>(0);//角色与用户   多对多
	
	@ManyToMany()
	@JoinTable(name="ROLE_MODULE_P",
		joinColumns={@JoinColumn(name="ROLE_ID",referencedColumnName="ROLE_ID")},
		inverseJoinColumns={@JoinColumn(name="MODULE_ID",referencedColumnName="MODULE_ID")})
	@OrderBy("ORDER_NO")
	private Set<Module> modules = new HashSet<Module>(0);//角色与模块  多对多
	
	@Column(name="NAME")
	private String name;//角色名称
	
	@Column(name="REMARK")
	private String remark;//备注
	
	@Column(name="ORDER_NO")
	private Integer orderNo;//排序号

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	
	
}
