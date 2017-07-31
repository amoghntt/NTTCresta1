package com.nttdata.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.nttdata.web.service.ConfigService;

@Controller
public class RefreshController {

	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/configurations",method = RequestMethod.POST)
	public String goToConfigPage(Model model) {
		return "configurations";
	}
	
	@RequestMapping(value = "/refresh",method = RequestMethod.POST)
	public ModelAndView refreshData(HttpServletRequest request,HttpSession session) {
		//will refresh all project we get by using etl
		configService.processTask("REFRESH", 0,null);
		ModelAndView modelView = new ModelAndView();
		return modelView;
	}
}
