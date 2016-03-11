package healthyhome.util;

import healthyhome.bean.UserMessage;

public class UserUtil {
	private static UserUtil uu;
	private static UserMessage um;

	private UserUtil() {
		
	}
	public UserUtil getInstance() {
		if(UserUtil.uu==null){
			return new UserUtil();
		}
		return uu;
	}
	/*public UserMessage getNowUser(){
		if(UserUtil.um==null){
			return 
		}
	}*/
}
