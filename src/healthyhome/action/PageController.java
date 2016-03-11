package healthyhome.action;

import healthyhome.bean.Menu;
import healthyhome.bean.Paging;
import healthyhome.bean.Role;
import healthyhome.bean.UserMessage;
import healthyhome.dao.MenuDao;
import healthyhome.dao.RoleDao;
import healthyhome.dao.UserMessageDao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController extends BaseController {
	@Autowired
	private UserMessageDao uMDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@RequestMapping(value = "/loadDataAction")
	public ModelAndView userMan(HttpServletRequest request,int pageSize,int nowPage) {
		pageSize = 5;
		if(nowPage<1){
			nowPage = 1;
		}
		int pNListNum = uMDao.findByExample(new UserMessage()).size()/pageSize+1;
		if(nowPage>pNListNum){
			nowPage = pNListNum; 
		}
		List<UserMessage> uMList = uMDao.getPageList(pageSize, nowPage);
		Paging page = new Paging(pageSize,nowPage);
		request.getSession().setAttribute("userList", uMList);
		List<Integer> pNList = new ArrayList<Integer>();
		for(int i=0;i<pNListNum;i++){
			pNList.add(i+1);
		}
		request.getSession().setAttribute("pNList", pNList);
		request.getSession().setAttribute("paging", page);
		return new ModelAndView("userMan");
		
	}
	@RequestMapping(value = "/loadRoleDataAction")
	public ModelAndView loadRoleData(HttpServletRequest request,int pageSize,int nowPage) {
		pageSize = 5;
		if(nowPage<1){
			nowPage = 1;
		}
		int pNListNum = roleDao.findByExample(new Role()).size()/pageSize+1;
		if(nowPage>pNListNum){
			nowPage = pNListNum; 
		}
		List<UserMessage> uMList = roleDao.getPageList(pageSize, nowPage);
		Paging page = new Paging(pageSize,nowPage);
		request.getSession().setAttribute("roleList", uMList);
		List<Integer> pNList = new ArrayList<Integer>();
		for(int i=0;i<pNListNum;i++){
			pNList.add(i+1);
		}
		request.getSession().setAttribute("pNList", pNList);
		request.getSession().setAttribute("paging", page);
		return new ModelAndView("roleMan");
		
	}
	@RequestMapping(value = "/loadMenuDataAction")
	public ModelAndView loadMenuData(HttpServletRequest request,int pageSize,int nowPage) {
		pageSize = 5;
		if(nowPage<1){
			nowPage = 1;
		}
		int pNListNum = menuDao.findByExample(new Menu()).size()/pageSize+1;
		if(nowPage>pNListNum){
			nowPage = pNListNum; 
		}
		List<UserMessage> uMList = menuDao.getPageList(pageSize, nowPage);
		Paging page = new Paging(pageSize,nowPage);
		request.getSession().setAttribute("menuTableList", uMList);
		List<Integer> pNList = new ArrayList<Integer>();
		for(int i=0;i<pNListNum;i++){
			pNList.add(i+1);
		}
		request.getSession().setAttribute("pNList", pNList);
		request.getSession().setAttribute("paging", page);
		return new ModelAndView("menuMan");
		
	}
}
