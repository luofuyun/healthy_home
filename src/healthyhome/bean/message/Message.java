package healthyhome.bean.message;

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
@Table(name = "message")
public class Message extends BaseEntity {
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
	@Column(name="content")
	private String content;
	@Column(name="publish_user_id")
	private Integer publishUserId;
	@Column(name="recieve_user_id")
	private Integer recieveUserId;
	
	@Column(name="type")
	private Integer type;
	@Column(name="type_name")
	private String typeName;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name="answer_content")
	private String answerContent;
	
	@Column(name="publish_time")
	private String publishTime;
	@Column(name="answer_time")
	private String answerTime;
	@Column(name="is_answer")
	private String isAnswer;
	public String getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}
	public Message(Integer type){
		this.type = type;
	}
	public Message(){}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPublishUserId() {
		return publishUserId;
	}
	public void setPublishUserId(Integer publishUserId) {
		this.publishUserId = publishUserId;
	}
	public Integer getRecieveUserId() {
		return recieveUserId;
	}
	public void setRecieveUserId(Integer recieveUserId) {
		this.recieveUserId = recieveUserId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
}
