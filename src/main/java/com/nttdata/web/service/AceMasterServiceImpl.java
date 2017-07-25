package com.nttdata.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.common.CommonService;
import com.nttdata.web.dao.AceMasterDao;
import com.nttdata.web.model.TelephonicaBean;

@Service
public class AceMasterServiceImpl implements AceMasterService {

	@Autowired
	private AceMasterDao aceMasterDAO;
	
	@Autowired
	private CommonService commonService;
	

	@Override
	public String getPieChartData() {
		List<TelephonicaBean> resultList = aceMasterDAO.getPieChartData();
		HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
		for(TelephonicaBean element : resultList){
			resultMap.put(element.getCategory(), element.getCountOfBugs());
		}
		String resultString = commonService.convertToJSON(resultMap);
		return resultString;
		}
	
	@Override
	public String getTestEnvironmentORSoftwareData(String category) {
		List<TelephonicaBean> resultList = aceMasterDAO.getTestEnvironmentORSoftwareData(category);
		HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
		for(TelephonicaBean element : resultList){
			resultMap.put(element.getProjectName(), element.getCountOfBugs());
		}
		String resultString = commonService.convertToJSON(resultMap);
		return resultString;
		
	}
}
