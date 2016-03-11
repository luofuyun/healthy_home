package healthyhome.action;

import healthyhome.bean.HealthKnowledge;
import healthyhome.dao.HealthKnowledgeDao;
import healthyhome.util.ResponseUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HealthKnowledgeController {
	@Autowired
	private HealthKnowledgeDao hKDao;
	@RequestMapping(value = "/healthKnowledge")
	public ModelAndView healthKnowledge(HttpServletRequest request) {
		List<HealthKnowledge> hList = hKDao.findByExample(new HealthKnowledge());
		request.getSession().setAttribute("healthList", hList);
		return new ModelAndView("healthKnowledge");
	}
	@RequestMapping(value = "/loadHealth")
	public void loadHealth(HttpServletRequest request,HttpServletResponse response,String itemId) {
		List<HealthKnowledge> hList = hKDao.findByExample(new HealthKnowledge(itemId));
		try {
			ResponseUtils.printObjToJson(response, hList.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
