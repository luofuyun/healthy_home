package healthyhome.bean.healthy;

import healthyhome.bean.BaseEntity;
import healthyhome.bean.UserMessage;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "healthy_activity")
public class HealthyActivity extends BaseEntity {
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
	@Column(name="activity_theme")
	private String activityTheme;
	@Column(name="activity_time")
	private String activityTime;
	@Column(name="activity_content")
	private String activityContent;
	@Column(name="activity_addr")
	private String activityAddr;
	private String isPart;
	
	public String getIsPart() {
		return isPart;
	}
	public void setIsPart(String isPart) {
		this.isPart = isPart;
	}
	public String getActivityAddr() {
		return activityAddr;
	}
	public void setActivityAddr(String activityAddr) {
		this.activityAddr = activityAddr;
	}
	private UserMessage um;
	public UserMessage getUm() {
		return um;
	}
	public void setUm(UserMessage um) {
		this.um = um;
	}
	private String membersStr;
	
	public String getMembersStr() {
		return membersStr;
	}
	public void setMembersStr(String membersStr) {
		this.membersStr = membersStr;
	}
	public String getActivityTheme() {
		return activityTheme;
	}
	public void setActivityTheme(String activityTheme) {
		this.activityTheme = activityTheme;
	}
	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
}
