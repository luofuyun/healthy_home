package healthyhome.action;

import healthyhome.bean.CarHug;
import healthyhome.bean.HugCustomer;
import healthyhome.bean.Menu;
import healthyhome.bean.Society;
import healthyhome.bean.UserMessage;
import healthyhome.bean.healthy.HealthyActivity;
import healthyhome.bean.healthy.HealthyActivityUser;
import healthyhome.bean.responseobject.MyCarHugResponseObj;
import healthyhome.dao.MenuDao;
import healthyhome.dao.UserMessageDao;
import healthyhome.dao.cardao.CarHugDao;
import healthyhome.dao.cardao.HugCustomerDao;
import healthyhome.dao.cardao.SocietyDao;
import healthyhome.util.ResponseUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarHugController extends BaseController {
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private SocietyDao societyDao;
	@Autowired
	private HugCustomerDao hCDao;
	@Autowired
	private CarHugDao cHDao;
	@Autowired
	private UserMessageDao uMDao;

	@RequestMapping(value = "/societyCarHug")
	public ModelAndView societyCarHug(HttpServletRequest request, Integer menuId)
			throws ParseException {
		// 社区拼车进入二级菜单
		/*String parentMenuId = "9";
		request.getSession()
				.setAttribute("menuList", getMenuList(parentMenuId));*/
		return new ModelAndView("main");
	}

	private List<Menu> getMenuList(String parentMenuId) {
		return menuDao.findByExample(new Menu(parentMenuId));
	}

	// 对应我的拼车
	@RequestMapping(value = "/myCarHug")
	public ModelAndView myCarHug(HttpServletRequest request, Integer menuId,
			Integer userId) throws ParseException {
		// 社区拼车进入二级菜单
		/*String parentMenuId = "9";
		request.getSession()
				.setAttribute("menuList", getMenuList(parentMenuId));*/
		List<HugCustomer> hCList = hCDao.findByExample(new HugCustomer(userId,
				"user"));
		List<MyCarHugResponseObj> responseList = new ArrayList<MyCarHugResponseObj>();
		for (HugCustomer hc : hCList) {
			Integer innerHugId = hc.getHugId();
			CarHug innerCarHug = (CarHug) cHDao.findById(innerHugId, CarHug.class.getName());
			Society innerSociety = new Society();
			UserMessage um = uMDao.findById(innerCarHug.getMakeCarHugUserId(),UserMessage.class.getName());
			responseList.add(new MyCarHugResponseObj(innerSociety, innerCarHug,um));
		}
		responseList = initList(responseList);
		request.getSession().setAttribute("carHugList", responseList);
		return new ModelAndView("carhug/carHug");
	}

	private List<MyCarHugResponseObj> initList(
			List<MyCarHugResponseObj> responseList) {
		String head = "<ul class='list-group'>";
		String end = "</ul>";
		String eachHead = "<li class='list-group-item'>";
		String eachEnd = "</li>";
		for (int i = 0; i < responseList.size(); i++) {
			String members = head;
			MyCarHugResponseObj ha = (MyCarHugResponseObj) responseList.get(i);
			CarHug ch = ha.getCarHug();
			HugCustomer hc = new HugCustomer();
			hc.setHugId(ch.getId());
			List<HugCustomer> hCList = hCDao.findByExample(hc);
			for (int j = 0; j < hCList.size(); j++) {
				HugCustomer innerHC = hCList.get(j);
				UserMessage innerUm = (UserMessage) uMDao.findById(
						innerHC.getUserId(), UserMessage.class.getName());
				members = members + eachHead;
				String phoneNumber = innerUm.getPhoneNumber();
				if(phoneNumber==null){
					phoneNumber = "未填写号码";
				}
				String nickName = innerUm.getNickName()+"(联系电话："+innerUm.getPhoneNumber()+")";
				members = members + nickName;
				members = members + eachEnd;
			}
			members = members + end;
			responseList.get(i).getCarHug().setMembersStr(members);
		}
		return responseList;
	}

	// 我的拼车，点击详情
	@RequestMapping(value = "/myCarHugDetail")
	public void myCarHugDetail(HttpServletRequest request,
			HttpServletResponse response, Integer hugId) throws ParseException {
		// 查看详情，同往的有些什么人？
		// 返回一个userList
		List<UserMessage> togetherUserList = new ArrayList<UserMessage>();
		List<HugCustomer> hCList = hCDao.findByExample(new HugCustomer(hugId,
				"hug"));
		for (HugCustomer hc : hCList) {
			togetherUserList.add(uMDao.findByExample(
					new UserMessage(hc.getUserId())).get(0));
		}
		try {
			ResponseUtils.printArrayList(response, togetherUserList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 取消拼车
	@RequestMapping(value = "/cancelCarHug")
	public void cancelCarHug(HttpServletRequest request,
			HttpServletResponse response, HugCustomer hc) {
		CarHug ch = cHDao.findById(hc.getHugId(),CarHug.class.getName());
		UserMessage um = (UserMessage) request.getSession().getAttribute("user");
		HugCustomer hugC = hCDao.findByExample(hc).get(0);
		hCDao.delete(hugC);
		if (um.getId()==ch.getMakeCarHugUserId()) {
			ch = cHDao.findById(hc.getHugId(),CarHug.class.getName());
			HugCustomer innerHC = new HugCustomer();
			innerHC.setHugId(ch.getId());
			List<HugCustomer> hCList = hCDao.findByExample(innerHC);
			for(HugCustomer tempHC : hCList){
				hCDao.delete(tempHC);
			}
			cHDao.delete(ch);
		}
		try {
			ResponseUtils.write(response, "取消成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 对应拼车列表
	@RequestMapping(value = "/carHugList")
	public ModelAndView carHugList(HttpServletRequest request, Integer menuId)
			throws ParseException {
		CarHug firstCarHug = new CarHug();
		List<CarHug> cHList = cHDao.findByExample(firstCarHug);
		List<MyCarHugResponseObj> responseList = new ArrayList<MyCarHugResponseObj>();
		for (CarHug ch : cHList) {
			Society innerSociety = new Society();
			UserMessage innerUser = (UserMessage) uMDao.findById(
					ch.getMakeCarHugUserId(), UserMessage.class.getName());
			responseList.add(new MyCarHugResponseObj(innerSociety, ch,
					innerUser));
		}
		responseList = initList(responseList);
		request.getSession().setAttribute("carHugList", responseList);
		return new ModelAndView("carhug/carHugList");
	}

	// 拼车列表，点击详情，查询加入拼车人
	@RequestMapping(value = "/carHugListDetail")
	public void carHugListDetail(HttpServletRequest request,
			HttpServletResponse response, Integer hugId) {
		// 查看详情，同往的有些什么人？
		// 返回一个userList
		List<UserMessage> togetherUserList = new ArrayList<UserMessage>();
		List<HugCustomer> hCList = hCDao.findByExample(new HugCustomer(hugId,
				"hug"));
		for (HugCustomer hc : hCList) {
			togetherUserList.add((UserMessage) uMDao.findById(hc.getUserId(),
					UserMessage.class.getName()));
		}
		try {
			ResponseUtils.printArrayList(response, togetherUserList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 加入拼车
	@RequestMapping(value = "/joinCarHug")
	public void joinCarHug(HttpServletRequest request,
			HttpServletResponse response,HugCustomer hc) {
		String responseStr = "加入成功";
		Integer carHugId = hc.getHugId();
		CarHug ch = cHDao.findById(carHugId,CarHug.class.getName());
		Integer maxSeat = ch.getSeatNumber();
		Integer nowSeat;
		HugCustomer newHc = new HugCustomer();
		newHc.setHugId(hc.getHugId());
		List<HugCustomer> hcList = hCDao.findByExample(newHc);
		nowSeat = hcList.size();
		if(nowSeat+1>maxSeat){
			responseStr = "该拼车团已满";
		}else{
			ch.setSeatNumber(ch.getSeatNumber()+1);
			cHDao.attachDirty(ch);
			hCDao.save(hc);
		}
		try {
			ResponseUtils.write(response, responseStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 发起拼车页面
	@RequestMapping(value = "/makeCarHug")
	public ModelAndView makeCarHug(HttpServletRequest request, Integer menuId)
			throws ParseException {
		// 社区拼车进入二级菜单
		/*String parentMenuId = "9";
		request.getSession()
				.setAttribute("menuList", getMenuList(parentMenuId));*/
		return new ModelAndView("carhug/makeCarHug");
	}

	// 提交发起拼车
	@RequestMapping(value = "/makeCarHugAction")
	public void makeCarHugAction(HttpServletRequest request,
			HttpServletResponse response, CarHug carHug, Integer userId)
			throws ParseException {
		UserMessage um = (UserMessage) request.getSession().getAttribute("user");
		carHug.setSocietyName(um.getSocietyName());
		carHug.setSocietyId(um.getSocietyId());
		carHug.setMakeCarHugUserName(um.getNickName());
		cHDao.save(carHug);
		hCDao.save(new HugCustomer(carHug.getMakeCarHugUserId(), carHug.getId()));
		try {
			ResponseUtils.write(response, "添加成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
