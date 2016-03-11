package healthyhome.action.personal;

import healthyhome.action.BaseController;
import healthyhome.bean.UserMessage;
import healthyhome.bean.message.Message;
import healthyhome.dao.MessageDao;
import healthyhome.dao.UserMessageDao;
import healthyhome.util.ResponseUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class PersonalController extends BaseController {
	@Autowired
	private MessageDao mDao;
	@Autowired
	private UserMessageDao uMDao;
	@RequestMapping(value = "/personalAction")
	public ModelAndView myLeaveMessage(HttpServletRequest request,
			Integer menuId, Integer userId) throws ParseException {
		UserMessage um = (UserMessage) request.getSession().getAttribute("user");
		if(um.getRoleId()==1){
			return new ModelAndView("adminJSP/admin", "message", "登录成功");
		}
		return new ModelAndView("personalJSP");
	}
	@RequestMapping(value = "/personalMessage")
	public ModelAndView personalMessage(HttpServletRequest request) throws ParseException {
		UserMessage um = (UserMessage) request.getSession().getAttribute("user");
		Message m = new Message();
		m.setRecieveUserId(um.getId());
		List<Message> mList = mDao.findByExample(m);
		request.getSession().setAttribute("messageList", mList);
		return new ModelAndView("personal/personalMessage");
	}
	
	@RequestMapping(value = "/answerRecieveMyMessage")
	public void answerRecieveMyMessage(HttpServletRequest request,
			HttpServletResponse response, Message m) throws ParseException {
		Message message = mDao.findById(m.getId());
		message.setAnswerContent(m.getAnswerContent());
		mDao.attachDirty(message);
		try {
			ResponseUtils.write(response, "答复成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/changePersonalMessage")
	public void changePersonalMessage(HttpServletRequest request,
			HttpServletResponse response, UserMessage um) throws ParseException {
		UserMessage user = (UserMessage) request.getSession().getAttribute("user");
		user.setNickName(um.getNickName());
		user.setRealName(um.getRealName());
		user.setAge(um.getAge());
		user.setSocietyName(um.getSocietyName());
		user.setPhoneNumber(um.getPhoneNumber());
		uMDao.attachDirty(user);
		try {
			ResponseUtils.write(response, "修改成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
