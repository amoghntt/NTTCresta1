package com.nttdata.web.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.web.common.CommonService;
import com.nttdata.web.model.ConfigBean;
import com.nttdata.web.model.LoginBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.service.ConfigServiceImpl;
import com.nttdata.web.service.ProjectServiceImpl;
import com.nttdata.web.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	private ProjectServiceImpl projectService;

	@Autowired
	private ConfigServiceImpl configService;
	
	@Autowired
	private CommonService commonService;

	public String errorMessageProperties = "errormessage";

	@RequestMapping(method = RequestMethod.GET)
	public String defaultLogin(Model model) {
		log.debug("Login Controller default method called");
		UserBean userBean = new UserBean();
		model.addAttribute("loginBean", userBean);
		return "loginPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView validateLogin(@ModelAttribute("loginBean") LoginBean loginBean, BindingResult result,
			HttpServletRequest request) {

		String userName = loginBean.getUserName();
		String password = loginBean.getPassword();
		ModelAndView modelView = new ModelAndView("home");
		try {
			boolean isValidUser = userService.authenticate(userName, password);
			if (isValidUser) {
				System.out.println("User Login Successful");
				HttpSession session = request.getSession();
				UserBean userBean = userService.getUserDetails(userName, password);
				userBean.setProjectBeanList(projectService.getProjectListForUser(userBean.getUserId()));
				List<PredictionBean> predictList = configService.getPredictListForUser();
				ConfigBean configBean = new ConfigBean();
				configBean.setAlgorithmBeanList(commonService.getAlgorithmListForUser());				
				Map<String, List<MetricsBean>> predictMetricMapping = getPredictMetricsMapping();
				session.setAttribute("USERBEAN", userBean);
				session.setAttribute("ALGORITHMLIST", configBean.getAlgorithmBeanList());
				session.setAttribute("PROJECTLIST", userBean.getProjectBeanList());
				session.setAttribute("PREDICTLIST", predictList);
				session.setAttribute("PREDICTMETRICMAPPING", predictMetricMapping);
				configBean.setPredictionBeanList(predictList);
				configBean.setProjectBeanList(userBean.getProjectBeanList());

				modelView.addObject("configBean", configBean);
			} else {
				result.addError(new ObjectError("err", "Invalid Credentials"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return modelView;
	}

	private Map<String, List<MetricsBean>> getPredictMetricsMapping() {
		return configService.getPredictMetricsMappingService();

	}
}

