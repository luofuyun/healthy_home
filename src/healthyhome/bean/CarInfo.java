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
@Table(name = "car_info")
public class CarInfo extends BaseEntity {
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
	@Column(name="car_owner_id")
	private String carOwnerId;
	public String getCarOwnerId() {
		return carOwnerId;
	}
	public void setCarOwnerId(String carOwnerId) {
		this.carOwnerId = carOwnerId;
	}
	@Column(name="car_name")
	private String carName;
	@Column(name="car_branch")
	private String carBranch;
	@Column(name="car_value")
	private float carValue;
	@Column(name="car_number")
	private String carNumber;
	@Column(name="car_seats")
	private int carSeats;
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarBranch() {
		return carBranch;
	}
	public void setCarBranch(String carBranch) {
		this.carBranch = carBranch;
	}
	public float getCarValue() {
		return carValue;
	}
	public void setCarValue(float carValue) {
		this.carValue = carValue;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public int getCarSeats() {
		return carSeats;
	}
	public void setCarSeats(int carSeats) {
		this.carSeats = carSeats;
	}
	
}
