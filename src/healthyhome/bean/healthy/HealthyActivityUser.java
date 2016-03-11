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
@Table(name = "healthy_activity_user")
public class HealthyActivityUser extends BaseEntity {
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
	@Column(name="healthy_activity_id")
	private Integer healthyActivityId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHealthyActivityId() {
		return healthyActivityId;
	}
	public void setHealthyActivityId(Integer healthyActivityId) {
		this.healthyActivityId = healthyActivityId;
	}
	
}
