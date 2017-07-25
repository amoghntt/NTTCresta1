package com.nttdata.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.web.model.ConfigBean;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.service.ConfigServiceImpl;
import com.nttdata.web.service.ProjectServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private ProjectServiceImpl projectService;

	@Autowired
	private ConfigServiceImpl configService;

	@RequestMapping(value = "/redirect", params = "logout", method = RequestMethod.POST)
	public ModelAndView redirectToHomePage(HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView("loginPage");
		HttpSession session = request.getSession();
		session.invalidate();
		return modelView;
	}

	@RequestMapping(value = "/redirect", params = "home", method = RequestMethod.POST)
	public ModelAndView logOut(HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView("home");
		modelView = getConfigDetails(request);
		return modelView;
	}

	public ModelAndView getConfigDetails(HttpServletRequest request) {
		ConfigBean configBean = new ConfigBean();
		ModelAndView modelView = new ModelAndView("home");
		try {
			HttpSession session = request.getSession();
			UserBean userBean = (UserBean) session.getAttribute("USERBEAN");
			userBean.setProjectBeanList(projectService.getProjectListForUser(userBean.getUserId()));
			List<PredictionBean> predictList = configService.getPredictListForUser();
			session.setAttribute("PREDICTLIST", predictList);
			configBean.setPredictionBeanList(predictList);
			configBean.setProjectBeanList(userBean.getProjectBeanList());

			modelView.addObject("configBean", configBean);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return modelView;
	}
	
	@RequestMapping(value = "/viewTeam", method = RequestMethod.GET)
	public String viewCRESTATeamPage(Model model) 
	{       

		return "team";
	}

}
