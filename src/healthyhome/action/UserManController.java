package healthyhome.action;

import healthyhome.bean.HealthKnowledge;
import healthyhome.bean.UserMessage;
import healthyhome.dao.UserMessageDao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserManController extends BaseController {
	@Autowired
	private UserMessageDao uMDao;
	@RequestMapping(value = "/userMan")
	public ModelAndView userMan(HttpServletRequest request) {
		List<UserMessage> uMList = uMDao.findByExample(new UserMessage());
		request.getSession().setAttribute("userList", uMList);
		return new ModelAndView("userMan");
	}
}
