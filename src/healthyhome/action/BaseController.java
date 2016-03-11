package healthyhome.action;

import healthyhome.bean.UserMessage;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	public void accountLogin(HttpServletRequest request, UserMessage account) {
		request.getSession().setAttribute("account", account);
	}
}
