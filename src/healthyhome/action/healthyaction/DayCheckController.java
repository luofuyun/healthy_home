package healthyhome.action.healthyaction;

import healthyhome.action.BaseController;
import healthyhome.bean.UserMessage;
import healthyhome.bean.healthy.DayCheck;
import healthyhome.dao.DayCheckDao;
import healthyhome.util.ResponseUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DayCheckController extends BaseController {
	@Autowired
	private DayCheckDao dCDao;
	@RequestMapping(value = "/dayCheck")
	public ModelAndView dayCheck(HttpServletRequest request, Integer menuId)
			throws ParseException {
		DayCheck dc = new DayCheck();
		UserMessage user = (UserMessage) request.getSession().getAttribute("user");
		dc.setUserId(user.getId());
		List<DayCheck> dcList = dCDao.findByExample(dc);
		request.getSession().setAttribute("dayCheckList", dcList);
		return new ModelAndView("healthy/dayCheck");
	}
	@RequestMapping(value = "/todayCheck")
	public void todayCheck(HttpServletRequest request,HttpServletResponse response, DayCheck dc)
			throws ParseException {
		Date dt=new Date();
	    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		dc.setDate(matter.format(dt));
		dc = judgeHealth(dc);
		UserMessage um = (UserMessage) request.getSession().getAttribute("user");
		dc.setUserId(um.getId());
		dCDao.save(dc);
		try {
			ResponseUtils.write(response, dc.getHealthStatus());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private DayCheck judgeHealth(DayCheck dc) {
		String bad = "差，请注意健康。";
		String middle = "中，健康尚待改善。";
		String good = "良，健康状况不错！";
		String veryGood = "优，请继续保持！";
		Float height = Float.parseFloat(dc.getHeight());
		Float weight = Float.parseFloat(dc.getWeight());
		Float value = weight/height/height;
		if(value<18.5){
			dc.setHealthStatus(middle);
		}else if(value>=18.5&&value<24.9){
			dc.setHealthStatus(veryGood);
		}else if(value>=25&&value<29.9){
			dc.setHealthStatus(good);
		}else if(value>=30){
			dc.setHealthStatus(bad);
		}
		return dc;
	}
}
