package healthyhome.service;

import healthyhome.bean.UserMessage;
import healthyhome.dao.UserMessageDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccountService")
public class UserMessageService {
	@Autowired
	private UserMessageDao UMDao;
	/*@Autowired
	private UserDao userDao;*/

	/*public List<UserMessage> getPageList(int pageSize,int nowPage){
		List<UserMessage> uMList = UMDao.findByExample(new UserMessage());
		
	}*/
	public boolean isValidUser(UserMessage user) {
		List temp = UMDao.findByExample(user) ;
		if (UMDao.findByExample(user).size() != 0)
			return true;
		else
			return false;
	}

	public boolean createAccount(UserMessage account) {
		try {
			UMDao.save(account);
			UserMessage user = new UserMessage();
			UMDao.save(user);
			//account.setUserId(user.getId());
			UMDao.attachDirty(account);
			return true;
		} catch (RuntimeException re) {
			return false;
		}
	}
	public List<UserMessage> findByExample(UserMessage instance) {
		return UMDao.findByExample(instance);
	}
	/**
	 * 编写测试用例
	 * @param args
	 */
	/*public static void main(String[] args){
		Account account = new Account();
		
	}*/
}
