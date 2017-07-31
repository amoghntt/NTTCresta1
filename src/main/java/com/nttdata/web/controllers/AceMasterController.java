package com.nttdata.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.web.model.LoginBean;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.service.AceMasterService;
import com.nttdata.web.service.UserService;
import com.nttdata.web.utils.CrestaQueryConstants;

@Controller
public class AceMasterController {

	@Autowired
	UserService userService;
	
	@Autowired
	AceMasterService aceMasterService;

	@RequestMapping(value = "/aceMaster", method = RequestMethod.POST)
	public String goToAceMasterController() {

		return "loginAce";
	}

	@RequestMapping(value = "/loginAce", method = RequestMethod.POST)
	public ModelAndView validateLoginAceMaster(@ModelAttribute("loginBean") LoginBean loginBean, BindingResult result,
			HttpServletRequest request) {

		String userName = loginBean.getUserName();
		String password = loginBean.getPassword();
		ModelAndView modelView = new ModelAndView("blank");
		try {
			boolean isValidUser = userService.authenticate(userName, password);
			if (isValidUser) {
				System.out.println("User Login Successful");
				HttpSession session = request.getSession();
				UserBean userBean = (UserBean) session.getAttribute("USERBEAN");
				String labelProject = CrestaQueryConstants.LBL_PROJECT;
				String pieChartResult = aceMasterService.getPieChartData();
				String barChartTestEnvironmentResult = aceMasterService.getTestEnvironmentORSoftwareData("Test Environment");
				String barChartSoftwareResult = aceMasterService.getTestEnvironmentORSoftwareData("Software");
				modelView.addObject("UserBean", userBean);
				modelView.addObject("pieChartResult", pieChartResult);
				modelView.addObject("barChartTestEnvironmentResult", barChartTestEnvironmentResult);
				modelView.addObject("barChartSoftwareResult", barChartSoftwareResult);
				modelView.addObject("labelProject", labelProject);
			} else {
				result.addError(new ObjectError("err", "Invalid Credentials"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		return modelView;
	}
}
