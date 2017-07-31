package com.nttdata.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.service.CalculateUCLLCLService;

@Controller
public class CalculateUCLLCLController {

	private static final Logger log = LoggerFactory.getLogger(CalculateUCLLCLController.class);

	@Autowired
	private CalculateUCLLCLService calculateUCLLCLService;

	@RequestMapping(value = "/calulateLimit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MetricsBean getCalculateLimit(@RequestParam("metricsId") int metricsId,
			HttpServletRequest request) {
		log.debug("CalculateUCLLCLConroller call.");
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("USERBEAN");
		Integer userId = Integer.parseInt(userBean.getUserId());
		String predictionCode = (String) session.getAttribute("SELECTEDPREDICTION");
		Integer projectId = (Integer) session.getAttribute("SELECTEDPROJECT");
		MetricsBean metricsBean = new MetricsBean();
		metricsBean = calculateUCLLCLService.getDefectCountMetricsBean(userId, predictionCode, metricsId, projectId);

		return metricsBean;
	}
}
