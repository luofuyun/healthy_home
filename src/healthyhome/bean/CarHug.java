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
@Table(name = "car_hug")
public class CarHug extends BaseEntity {
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

	public CarHug(Integer id) {
		this.id = id;
	}

	public CarHug() {

	}

	@Column(name = "make_car_hug_user_id")
	private Integer makeCarHugUserId;
	@Column(name = "make_car_hug_user_name")
	private String makeCarHugUserName;
	@Column(name = "society_name")
	private String societyName;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name="seat_number")
	private Integer seatNumber;
	

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	private String membersStr;

	public String getMembersStr() {
		return membersStr;
	}

	public void setMembersStr(String membersStr) {
		this.membersStr = membersStr;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMakeCarHugUserName() {
		return makeCarHugUserName;
	}

	public void setMakeCarHugUserName(String makeCarHugUserName) {
		this.makeCarHugUserName = makeCarHugUserName;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public Integer getMakeCarHugUserId() {
		return makeCarHugUserId;
	}

	public void setMakeCarHugUserId(int makeCarHugUserId) {
		this.makeCarHugUserId = makeCarHugUserId;
	}

	@Column(name = "society_id")
	private Integer societyId;

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	// 上车地点
	@Column(name = "in_car_addr")
	private String inCarAddr;
	// 下车地点（目的地）
	@Column(name = "out_car_addr")
	private String outCarAddr;
	// 旅途价格
	@Column(name = "trip_money")
	private String tripMoney;
	// 时间
	@Column(name = "hug_time")
	private String hugTime;

	public String getHugTime() {
		return hugTime;
	}

	public void setHugTime(String hugTime) {
		this.hugTime = hugTime;
	}

	public String getInCarAddr() {
		return inCarAddr;
	}

	public void setInCarAddr(String inCarAddr) {
		this.inCarAddr = inCarAddr;
	}

	public String getOutCarAddr() {
		return outCarAddr;
	}

	public void setOutCarAddr(String outCarAddr) {
		this.outCarAddr = outCarAddr;
	}

	public String getTripMoney() {
		return tripMoney;
	}

	public void setTripMoney(String tripMoney) {
		this.tripMoney = tripMoney;
	}

	public void setMakeCarHugUserId(Integer makeCarHugUserId) {
		this.makeCarHugUserId = makeCarHugUserId;
	}

}
