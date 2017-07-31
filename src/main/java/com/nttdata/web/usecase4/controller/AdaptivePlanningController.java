package com.nttdata.web.usecase4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.pythonexecutor.Java2PythonExecutor;
import com.nttdata.pythonexecutor.ScikitConstants;
import com.nttdata.web.usecase4.model.AdaptivePlanningModel;
import com.nttdata.web.usecase4.service.AdaptivePlanningService;
import com.nttdata.web.utils.CrestaQueryConstants;

@Controller
public class AdaptivePlanningController {
	
	private static Logger log = LoggerFactory.getLogger(AdaptivePlanningController.class);
	
	@Autowired
	private AdaptivePlanningService adaptivePlanningService;
	
	@RequestMapping("/adaptivePlanning")
	public ModelAndView Controller() {
		
		List<Integer> intresult = new ArrayList<Integer>();
		intresult.add(1);
		intresult.add(12);
		intresult.add(13);
		intresult.add(14);
		System.out.print(intresult);
		List<AdaptivePlanningModel> historicalDataList = adaptivePlanningService.getDataFromCSV(CrestaQueryConstants.HISTORICAL_DATA_FILE_PATH);
		
		List<AdaptivePlanningModel> recentDataList = adaptivePlanningService.getDataFromCSV(CrestaQueryConstants.RECENT_DATA_FILE_PATH);
		
		ModelAndView model = new ModelAndView("adaptive");
		model.addObject("historicalData", historicalDataList);
		model.addObject("recentData", recentDataList);
		return model;
    }
	
	@RequestMapping(value = "/predictAdaptivePlanning", method = RequestMethod.POST)
	public ModelAndView predict(@ModelAttribute("adaptivePlanningBean") AdaptivePlanningModel adaptivePlanningBean,BindingResult result, HttpServletRequest request){
		List<Integer> resultList = new ArrayList<Integer>();
		//resultList.add(adaptivePlanningBean.getReleaseId());
		resultList.add(adaptivePlanningBean.getDefectDensity());
		resultList.add(adaptivePlanningBean.getDefectRejection());
		resultList.add(adaptivePlanningBean.getDefectLeakage());
		resultList.add(adaptivePlanningBean.getTestCaseCount());
		resultList.add(adaptivePlanningBean.getApplicationComplexity());
		resultList.add(adaptivePlanningBean.getDomainKnowledge());
		resultList.add(adaptivePlanningBean.getTechnicalSkills());
		resultList.add(adaptivePlanningBean.getRequirementQueryCount());
		resultList.add(adaptivePlanningBean.getCodeReviewComments());
		resultList.add(adaptivePlanningBean.getDesignReviewComments());
		resultList.add(adaptivePlanningBean.getNumberOfResources());
		resultList.add(adaptivePlanningBean.getBudgetInUse().intValue());
		resultList.add(adaptivePlanningBean.getNumberOfDaysToComplete());
		resultList.add(adaptivePlanningBean.getCostOfResource().intValue());
		resultList.add(adaptivePlanningBean.getEfficiency());
		resultList.add(adaptivePlanningBean.getAvailabilityOfBudget());
		
		List<Double> pythonResult = Java2PythonExecutor.getAdaptivePlanning(ScikitConstants.UC4_RANDOM_FOREST_SCRIPT,resultList);
		ModelAndView model = new ModelAndView("adaptiveResult");
		model.addObject("releaseId", adaptivePlanningBean.getReleaseId());
		model.addObject("projectStatus",pythonResult.get(0));
		model.addObject("adaptiveBudget",pythonResult.get(1));
		model.addObject("availableBudget",pythonResult.get(2));
		return model;
	}

}
