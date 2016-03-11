package healthyhome.bean.responseobject;

import healthyhome.bean.CarHug;
import healthyhome.bean.Society;
import healthyhome.bean.UserMessage;

public class MyCarHugResponseObj {
	private Society society;
	private CarHug carHug;
	private UserMessage userMessage;
	public UserMessage getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(UserMessage userMessage) {
		this.userMessage = userMessage;
	}
	public Society getSociety() {
		return society;
	}
	public void setSociety(Society society) {
		this.society = society;
	}
	public CarHug getCarHug() {
		return carHug;
	}
	public void setCarHug(CarHug carHug) {
		this.carHug = carHug;
	}
	public MyCarHugResponseObj(Society society,CarHug carHug){
		this.society = society;
		this.carHug = carHug;
	}
	public MyCarHugResponseObj(Society society,CarHug carHug,UserMessage userMessage){
		this.society = society;
		this.carHug = carHug;
		this.userMessage = userMessage;
	}
}
