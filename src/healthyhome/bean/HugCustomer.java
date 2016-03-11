package healthyhome.bean;

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
@Table(name = "hug_customer")
public class HugCustomer extends BaseEntity {
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
	public HugCustomer(){}
	//根据传入标志，判断是初始化user还是初始化hug
	public HugCustomer(Integer id,String flag){
		super();
		if("user".equals(flag)){
			this.userId = id;
		}
		if("hug".equals(flag)){
			this.hugId = id; 
		}
	}
	public HugCustomer(Integer userId,Integer hugId){
		this.userId = userId;
		this.hugId = hugId;
	}
	@Column(name="user_id")
	private Integer userId;
	@Column(name="hug_id")
	private Integer hugId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHugId() {
		return hugId;
	}
	public void setHugId(Integer hugId) {
		this.hugId = hugId;
	}
	
}
