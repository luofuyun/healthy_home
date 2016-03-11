package healthyhome.action;

import healthyhome.bean.Menu;
import healthyhome.bean.Role;
import healthyhome.bean.RoleMenu;
import healthyhome.bean.UserMessage;
import healthyhome.bean.UserRole;
import healthyhome.dao.MenuDao;
import healthyhome.dao.RoleDao;
import healthyhome.dao.RoleMenuDao;
import healthyhome.dao.UserMessageDao;
import healthyhome.dao.UserRoleDao;
import healthyhome.service.UserMessageService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {
	@Autowired
	private UserMessageService accountService;
	@Autowired
	private UserMessageDao UMDao;
	@Autowired
	private UserRoleDao URDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleMenuDao rMDao;
	@Autowired
	private MenuDao menuDao;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, UserMessage account)
			throws ParseException {
		// 点击登陆，进入此处，用户输入的用户名与密码保存于参数account中
		List<UserMessage> accountList = accountService.findByExample(account);
		if (accountList.size() > 0) {
			// 账号密码验证成功，将用户的id、用户名称（realName）保存于页面中
			account = accountList.get(0);
			Role r = new Role();
			try {
				UserRole ur = new UserRole();
				ur.setUserId(account.getId());
				ur = URDao.findByExample(ur).get(0);

				r = roleDao.findById(ur.getRoleId());

				RoleMenu rm = new RoleMenu();
				rm.setRoleId(r.getId());
				List<RoleMenu> rmList = rMDao.findByExample(rm);

				List<Menu> menuList = new ArrayList<Menu>();
				for (RoleMenu rmTemp : rmList) {
					menuList.add(menuDao.findById(rmTemp.getMenuId()));
				}
				List<Menu> menuListF = new ArrayList<Menu>();
				List<Menu> menuListS = new ArrayList<Menu>();
				for(Menu menu : menuList){
					if(menu.getMenuLevel()==2){
						menuListF.add(menu);
					}
					if(menu.getMenuLevel()==3){
						menuListS.add(menu);
					}
				}

				//UserUtil.
				
				request.getSession().setAttribute("user", account);
				request.getSession().setAttribute("role", r);
				request.getSession().setAttribute("menuList", menuList);
				request.getSession().setAttribute("menuListF", menuListF);
				request.getSession().setAttribute("menuListS", menuListS);
			} catch (Exception e) {
			}
			if(r!=null){
				if(r.getId()==1){
					return new ModelAndView("adminJSP/admin", "message", "登录成功");
				}else{
					return new ModelAndView("main", "message", "登录成功");
				}
			}
		}
		return new ModelAndView("login", "message", "登录失败");
	}

	@RequestMapping(value = "/register.action")
	public ModelAndView register(HttpServletRequest request, UserMessage account)
			throws ParseException {
		// 点击登陆，进入此处，用户输入的用户名与密码保存于参数account中
		try {
			account.setRealName("新用户");//默认值为新用户
			account.setNickName("新用户");//默认值为新用户
			account.setRoleId(2);
			account.setRoleName("普通用户");
			UMDao.save(account);
			UserRole ur = new UserRole(account.getId(),2);//默认将新注册的用户设置为普通用户
			URDao.save(ur);
			return new ModelAndView("login", "message", "注册成功");
		} catch (Exception e) {
			return new ModelAndView("register", "message", "注册失败");
		}
	}
}
