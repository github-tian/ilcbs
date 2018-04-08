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
@Table(name="FEEDBACK_C")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FEEDBACK_ID")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	
	@Column(name="INPUT_BY")
	private String inputBy;			
    
	@Column(name="INPUT_TIME")
	private Date inputTime;			
    
	@Column(name="TITLE")
	private String title;			
    
	@Column(name="CONTENT")
	private String content;			
    
	@Column(name="CLASS_TYPE")
	private String classType;			//1管理2安全3建议4其他
    
	@Column(name="TEL")
	private String tel;			
    
	@Column(name="ANSWER_BY")
	private String answerBy;			
    
	@Column(name="ANSWER_TIME")
	private Date answerTime;			
    
	@Column(name="SOLVE_METHOD")
	private String solveMethod;			
    
	@Column(name="RESOLUTION")
	private String resolution;			//1已修改2无需修改3重复问题4描述不完整5无法再现6其他
    
	@Column(name="DIFFICULTY")
	private String difficulty;			//1极难2比较难3有难度4一般
    
	@Column(name="IS_SHARE")
	private String isShare;			//0不公开1公开
    
	@Column(name="STATE")
	private String state;			//0未处理1已处理
    
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

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAnswerBy() {
		return answerBy;
	}

	public void setAnswerBy(String answerBy) {
		this.answerBy = answerBy;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getSolveMethod() {
		return solveMethod;
	}

	public void setSolveMethod(String solveMethod) {
		this.solveMethod = solveMethod;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
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
