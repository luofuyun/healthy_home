package healthyhome.bean.healthy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import healthyhome.bean.BaseEntity;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "healthy_ask")
public class HealthyAsk extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="user_id")
	private Integer userId;
	
	public HealthyAsk(Integer userId) {
		super();
		this.userId = userId;
	}
	public HealthyAsk(){
		
	}
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	@Column(name="create_time")
	private String createTime;
	@Column(name="is_valid")
	private Integer isValid;
	@Column(name="title")
	private String title;
	@Column(name="is_ask")
	private String isAsk;
	public String getIsAsk() {
		return isAsk;
	}
	public void setIsAsk(String isAsk) {
		this.isAsk = isAsk;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
