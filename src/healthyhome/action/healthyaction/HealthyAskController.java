package healthyhome.action.healthyaction;

import healthyhome.action.BaseController;
import healthyhome.bean.HugCustomer;
import healthyhome.bean.UserMessage;
import healthyhome.bean.healthy.HealthyAsk;
import healthyhome.dao.HealthyAskDao;
import healthyhome.util.ResponseUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HealthyAskController extends BaseController {
	@Autowired
	private HealthyAskDao hADao;
	//获取健康咨询数据
	@RequestMapping(value = "/healthyAsk")
	public ModelAndView healthyAsk(HttpServletRequest request, Integer menuId)
			throws ParseException {
		UserMessage user = (UserMessage) request.getSession().getAttribute("user");
		if(user.getRoleId()==2){
			List<HealthyAsk> hAList = hADao.findByExample(new HealthyAsk(user.getId()));
			request.getSession().setAttribute("healthyAskList", hAList);
			return new ModelAndView("healthyask/healthyAsk");
		}else{
			List<HealthyAsk> hAList = hADao.findByExample(new HealthyAsk());
			request.getSession().setAttribute("healthyAskList", hAList);
			return new ModelAndView("healthyask/adminHealthyAsk");
		}
	}
	@RequestMapping(value = "/answer")
	public void answer(HttpServletRequest request,
			HttpServletResponse response, HealthyAsk ha,Integer itemId) throws ParseException {
		String answer = ha.getAnswer();
		ha = hADao.findById(itemId,HealthyAsk.class.getName());
		ha.setAnswer(answer);
		ha.setIsAsk("是");
		hADao.attachDirty(ha);
		try {
			ResponseUtils.write(response, "答复成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/submitAsk")
	public void submitAsk(HttpServletRequest request,
			HttpServletResponse response, HealthyAsk ha) throws ParseException {
		Date dt=new Date();
	    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		ha.setCreateTime(matter.format(dt));
		ha.setIsAsk("否");
		ha.setIsValid(1);
		UserMessage um = (UserMessage) request.getSession().getAttribute("user");
		ha.setUserId(um.getId());
		hADao.save(ha);
		try {
			ResponseUtils.write(response, "答复成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
