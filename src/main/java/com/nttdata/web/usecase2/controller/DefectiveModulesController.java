package com.nttdata.web.usecase2.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.nttdata.web.common.CommonService;
import com.nttdata.web.model.ConfigBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.model.ModuleDetails;
import com.nttdata.web.model.PredictionBean;
import com.nttdata.web.model.ProjectBean;
import com.nttdata.web.model.UserBean;
import com.nttdata.web.service.ConfigService;
import com.nttdata.web.usecase2.service.DefectiveModulesService;
import com.nttdata.web.utils.ColorEnum;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.Utils;

@Controller
public class DefectiveModulesController {

	@Autowired
	private ConfigService configService;

	@Autowired
	private DefectiveModulesService defectiveModuleService;
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/predictdefectmodule", method = RequestMethod.POST)
	public ModelAndView getPredictResultForUseCase2(@ModelAttribute("configBean") ConfigBean configBean,
			HttpServletRequest request) {
		List<MetricsBean> metricsList = configBean.getPredictBean().getMetricsList();
		HttpSession session = request.getSession();
		session.setAttribute("metricsList", metricsList);
		for(MetricsBean metricBean : metricsList){
			metricBean.setPredictionId("2");
			commonService.saveData(metricBean);
		}
		UserBean userBean = (UserBean) session.getAttribute("USERBEAN");
		String userId = userBean.getUserId();
		String predictionCode = (String) session.getAttribute("SELECTEDPREDICTION");
		Integer redmineProjectId = (Integer) session.getAttribute("SELECTEDPROJECT");
		session.setAttribute("USECASE2_METRICS_LIST", metricsList);
		commonService.deleteDataFromTempDensitytable(Integer.parseInt(userBean.getUserId()), predictionCode);
		List<List<ModuleDetails>> moduleDisplayList = new ArrayList<List<ModuleDetails>>();
		moduleDisplayList = defectiveModuleService.processTask(predictionCode, "defectiveModules", userId,
				redmineProjectId, metricsList);

		ModelAndView modelView = new ModelAndView("category");
		modelView.addObject("moduleDisplayList", moduleDisplayList);

		configBean.getProjectBean().setProjectName(commonService.getProjectName(redmineProjectId,
				(List<ProjectBean>) session.getAttribute("PROJECTLIST")));
		configBean.getPredictBean().setPredictDescription(commonService.getPredictionName(predictionCode,
				(List<PredictionBean>) session.getAttribute("PREDICTLIST")));
		modelView.addObject("configBean", configBean);
		return modelView;

	}

	@RequestMapping(value = "/predictDefectiveModules", method = RequestMethod.GET)
	public ModelAndView predictDefectiveModules(@RequestParam("moduleName") String moduleName,
			@RequestParam("ddPredictedValue") int ddPredictedValue,
			@RequestParam("dlPredictedValue") int dlPredictedValue,
			@RequestParam("drPredictedValue") int drPredictedValue, @RequestParam("category") String category,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<MetricsBean> metricsList = (List<MetricsBean>) session.getAttribute("USECASE2_METRICS_LIST");
		MetricsBean metricBean = metricsList.get(0);
		int[] uclDD = metricBean.getUcl();
		int[] lclDD = metricBean.getLcl();
		metricBean = metricsList.get(1);
		int[] uclDL = metricBean.getUcl();
		int[] lclDL = metricBean.getLcl();
		metricBean = metricsList.get(2);
		int[] uclDR = metricBean.getUcl();
		int[] lclDR = metricBean.getLcl();
		String predictionCode = (String) session.getAttribute("SELECTEDPREDICTION");
		Integer redmineProjectId = (Integer) session.getAttribute("SELECTEDPROJECT");
		UserBean userBean = (UserBean) session.getAttribute("USERBEAN");
		String userId = userBean.getUserId();
		int[] predictedValues = new int[3];
		predictedValues[0] = ddPredictedValue;
		predictedValues[1] = dlPredictedValue;
		predictedValues[2] = drPredictedValue;
		List<int[][][]> resultGraphBeanList = defectiveModuleService.getModuleDetails(moduleName, predictionCode,
				Integer.parseInt(userId), metricsList, predictedValues);

		
		String defectDensity = commonService.convertToJSON(resultGraphBeanList.get(0));
		String defectLeakage = commonService.convertToJSON(resultGraphBeanList.get(1));
		String defectRejection = commonService.convertToJSON(resultGraphBeanList.get(2));
		

		ModelAndView modelView = null;
		modelView = new ModelAndView("predictionDefectiveModules");

		modelView.addObject("datasets", defectDensity);
		modelView.addObject("defectDensity",CrestaQueryConstants.DEFECT_DENSITY);

		modelView.addObject("colourOfModule", category);

		modelView.addObject("datasets1", defectLeakage);
		modelView.addObject("defectLeakage",CrestaQueryConstants.DEFECT_LEAKAGE);

	
		modelView.addObject("datasets2", defectRejection);
		modelView.addObject("defectRejection",CrestaQueryConstants.DEFECT_REJECTION);
		modelView.addObject("moduleName", moduleName);

		ConfigBean configBean = new ConfigBean();
		configBean.getProjectBean().setProjectName(commonService.getProjectName(redmineProjectId,
				(List<ProjectBean>) session.getAttribute("PROJECTLIST")));
		configBean.getPredictBean().setPredictDescription(commonService.getPredictionName(predictionCode,
				(List<PredictionBean>) session.getAttribute("PREDICTLIST")));
		modelView.addObject("configBean", configBean);
		
List<Integer> releaseCountList = new ArrayList<Integer>();
	
		
		int[][] releaseData= new int[resultGraphBeanList.get(0).length][];
		releaseData = resultGraphBeanList.get(0)[2];

		for(int i = 0 ; i<releaseData.length;i++)
		{
			int[] data = (int[]) releaseData[i];
			releaseCountList.add(data[0]);
			
		}
				
		releaseCountList.add(releaseCountList.get(releaseCountList.size()-1)+1);
		ColorEnum colorEnumDD = Utils.getColorEnum(uclDD[0], lclDD[0],
				predictedValues[0]);
		ColorEnum colorEnumDL = Utils.getColorEnum(uclDL[0], lclDL[0],
				predictedValues[1]);
		ColorEnum colorEnumDR = Utils.getColorEnum(uclDR[0], lclDR[0],
				predictedValues[2]);
		
		modelView.addObject("predictionColourDD", colorEnumDD.getColorValue());
		modelView.addObject("predictionColourDL", colorEnumDL.getColorValue());
		modelView.addObject("predictionColourDR", colorEnumDR.getColorValue());
		
		
		int maxRelease = Collections.max(releaseCountList);
		maxRelease++;
		
		int minRelease = Collections.min(releaseCountList);
		int[] maxAndMinForDefectDensity = getMinAndMaxForDefects(resultGraphBeanList.get(0)[2],uclDD[0],lclDD[0],predictedValues[0]);
		int[] maxAndMinForDefectLeakage = getMinAndMaxForDefects(resultGraphBeanList.get(1)[2],uclDL[0],lclDL[0],predictedValues[1]);
		int[] maxAndMinForDefectRejection = getMinAndMaxForDefects(resultGraphBeanList.get(2)[2],uclDR[0],lclDR[0],predictedValues[2]);
		modelView.addObject("maxYValueDD",maxAndMinForDefectDensity[0]);
		modelView.addObject("minYValueDD",maxAndMinForDefectDensity[1]);
		
		modelView.addObject("maxYValueDL",maxAndMinForDefectLeakage[0]);
		modelView.addObject("minYValueDL",maxAndMinForDefectLeakage[1]);
		
		modelView.addObject("maxYValueDR",maxAndMinForDefectRejection[0]);
		modelView.addObject("minYValueDR",maxAndMinForDefectRejection[1]);
		
		modelView.addObject("maxXValue",maxRelease);
		modelView.addObject("minXValue",minRelease);
		modelView.addObject("xLabel",CrestaQueryConstants.RELEASE);
		
		return modelView;

	}
	
	public int[] getMinAndMaxForDefects(int[][] defectsAndReleaseData,int ucl,int lcl,int predictedValue)
	{
		List<Integer>defects = new ArrayList<Integer>();
		for(int i = 0 ; i<defectsAndReleaseData.length;i++)
		{
			int[] data = (int[]) defectsAndReleaseData[i];
			defects.add(data[1]);
			
		}
		int maxAndMinValues[] = new int[2];
		maxAndMinValues[0] = Collections.max(defects);
		maxAndMinValues[0] = (ucl > maxAndMinValues[0]) ? ((ucl > predictedValue) ? ucl : predictedValue) : ((maxAndMinValues[0] > predictedValue) ? maxAndMinValues[0] : predictedValue);
		int lastStepY = CrestaQueryConstants.STEP_SIZE - (maxAndMinValues[0] % CrestaQueryConstants.STEP_SIZE);
		maxAndMinValues[0] = maxAndMinValues[0] + lastStepY;

		maxAndMinValues[1] = Collections.min(defects);
		
		maxAndMinValues[1] = lcl < maxAndMinValues[1] ? (lcl < predictedValue ? lcl : predictedValue) : (maxAndMinValues[1]  < predictedValue ? maxAndMinValues[1] : predictedValue);
		int firstStep = maxAndMinValues[1] % CrestaQueryConstants.STEP_SIZE;
		if(firstStep == 0 && maxAndMinValues[1]>=CrestaQueryConstants.STEP_SIZE)
			maxAndMinValues[1] = maxAndMinValues[1]-CrestaQueryConstants.STEP_SIZE;
		
		else if(firstStep == 0 && maxAndMinValues[1]<CrestaQueryConstants.STEP_SIZE)
			maxAndMinValues[1] = 0;
		
		else
			maxAndMinValues[1] = maxAndMinValues[1] - firstStep;
		
		return maxAndMinValues;
		
		
	}

}
