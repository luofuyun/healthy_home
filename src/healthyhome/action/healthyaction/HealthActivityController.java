package healthyhome.action.healthyaction;

import healthyhome.action.BaseController;
import healthyhome.bean.UserMessage;
import healthyhome.bean.healthy.HealthyActivity;
import healthyhome.bean.healthy.HealthyActivityUser;
import healthyhome.bean.healthy.HealthyAsk;
import healthyhome.dao.HealthyActivityDao;
import healthyhome.dao.HealthyActivityUserDao;
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
public class HealthActivityController extends BaseController {
	@Autowired
	private HealthyActivityDao hADao;
	@Autowired
	private HealthyActivityUserDao hAUDao;
	@Autowired
	private UserMessageDao uMDao;

	@RequestMapping(value = "/healthActivity")
	public ModelAndView healthyActivity(HttpServletRequest request,
			Integer menuId) throws ParseException {
		List<HealthyActivity> hAList = hADao
				.findByExample(new HealthyActivity());
		hAList = initActivityMembers(hAList);
		request.getSession().setAttribute("healthyActivityList", hAList);
		UserMessage um = (UserMessage) request.getSession()
				.getAttribute("user");
		for(int i = 0;i<hAList.size();i++){
			HealthyActivityUser hau = new HealthyActivityUser();
			hau.setHealthyActivityId(hAList.get(i).getId());
			hau.setUserId(um.getId());
			if(hAUDao.findByExample(hau).size()!=0){
				hAList.get(i).setIsPart("已参加");
			}else{
				hAList.get(i).setIsPart("未参加");
			}
		}
		if (um.getRoleId() == 2) {
			return new ModelAndView("healthy/healthyActivity");
		} else {
			return new ModelAndView("healthy/adminHealthyActivity");
		}
	}
	@RequestMapping(value = "/submitActivity")
	public void submitActivity(HttpServletRequest request,
			HttpServletResponse response, HealthyActivity ha) throws ParseException {
		hADao.save(ha);
		try {
			ResponseUtils.write(response, "发布成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/takePartIn")
	public void takePartIn(HttpServletRequest request,
			HttpServletResponse response, HealthyActivityUser hau) throws ParseException {
		UserMessage um = (UserMessage) request.getSession().getAttribute("user");
		hau.setUserId(um.getId());
		hADao.save(hau);
		try {
			ResponseUtils.write(response, "恭喜你，你已成功参加该活动");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delActive")
	public void delActive(HttpServletRequest request,
			HttpServletResponse response, Integer id) throws ParseException {
		HealthyActivity ha = hADao.findById(id,HealthyActivity.class.getName());
		hADao.delete(ha);
		HealthyActivityUser hau = new HealthyActivityUser();
		hau.setHealthyActivityId(ha.getId());
		List<HealthyActivityUser> hauList = hAUDao.findByExample(hau);
		for(HealthyActivityUser temp : hauList){
			hAUDao.delete(temp);
		}
		try {
			ResponseUtils.write(response, "删除成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private List<HealthyActivity> initActivityMembers(
			List<HealthyActivity> hAList) {
		String head = "<ul class='list-group'>";
		String end = "</ul>";
		String eachHead = "<li class='list-group-item'>";
		String eachEnd = "</li>";
		for (int i = 0; i < hAList.size(); i++) {
			String members = head;
			HealthyActivity ha = (HealthyActivity) hAList.get(i);
			Integer activityId = ha.getId();
			HealthyActivityUser hau = new HealthyActivityUser();
			hau.setHealthyActivityId(activityId);
			List<HealthyActivityUser> hAUList = hAUDao.findByExample(hau);
			for (int j = 0; j < hAUList.size(); j++) {
				HealthyActivityUser innerHau = (HealthyActivityUser) hAUList
						.get(j);
				UserMessage innerUm = (UserMessage) uMDao.findById(
						innerHau.getUserId(), UserMessage.class.getName());
				members = members + eachHead;
				String nickName = innerUm.getNickName()+"(联系方式："+innerUm.getPhoneNumber()+")";
				members = members + nickName;
				members = members + eachEnd;
			}
			members = members + end;
			hAList.get(i).setMembersStr(members);
		}
		return hAList;
	}
}
