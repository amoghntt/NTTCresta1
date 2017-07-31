package com.nttdata.web.usecase4.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nttdata.web.usecase4.model.AdaptivePlanningModel;
import com.opencsv.CSVReader;

@Service
public class AdaptivePlanningServiceImpl implements AdaptivePlanningService {
	
	@Override
	public List<AdaptivePlanningModel> getDataFromCSV(String filePath){
		
		 
		 CSVReader reader = null;
		 List<AdaptivePlanningModel> resultList = new ArrayList<AdaptivePlanningModel>();
	        try {
	            reader = new CSVReader(new FileReader(filePath));
	            String[] line;
	            reader.readNext();
	            while ((line = reader.readNext()) != null) {
	            	AdaptivePlanningModel adaptiveModelBean = new AdaptivePlanningModel();
	            	
	            	adaptiveModelBean =  createBeanFromCSV(line);
	            	resultList.add(adaptiveModelBean);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return resultList;
	}

	private AdaptivePlanningModel createBeanFromCSV(String[] line) {
		AdaptivePlanningModel adaptiveModelBean = new AdaptivePlanningModel();
		adaptiveModelBean.setReleaseId(Integer.parseInt(line[0]));
		adaptiveModelBean.setDefectDensity(Integer.parseInt(line[1]));
		adaptiveModelBean.setDefectRejection(Integer.parseInt(line[2]));
		adaptiveModelBean.setDefectLeakage(Integer.parseInt(line[3]));
		adaptiveModelBean.setTestCaseCount(Integer.parseInt(line[4]));
		adaptiveModelBean.setApplicationComplexity(Integer.parseInt(line[5]));
		adaptiveModelBean.setDomainKnowledge(Integer.parseInt(line[6]));
		adaptiveModelBean.setTechnicalSkills(Integer.parseInt(line[7]));
		adaptiveModelBean.setRequirementQueryCount(Integer.parseInt(line[8]));
		adaptiveModelBean.setCodeReviewComments(Integer.parseInt(line[9]));
		adaptiveModelBean.setDesignReviewComments(Integer.parseInt(line[10]));
		adaptiveModelBean.setNumberOfResources(Integer.parseInt(line[11]));
		adaptiveModelBean.setBudgetInUse(Double.parseDouble(line[12]));
		adaptiveModelBean.setNumberOfDaysToComplete(Integer.parseInt(line[13]));
		adaptiveModelBean.setCostOfResource(Double.parseDouble(line[14]));
		adaptiveModelBean.setEfficiency(Integer.parseInt(line[15]));
		adaptiveModelBean.setProjectStatus(Integer.parseInt(line[16]));
		adaptiveModelBean.setAvailabilityOfBudget(Integer.parseInt(line[17]));
		return adaptiveModelBean;
	}

	@Override
	public List<String> convertIntListToString(List<Integer> resultList) {
				/* Specify the size of the list up front to prevent resizing. */
				List<String> resultStringList = new ArrayList<String>(resultList.size()); 
				for (Integer myInt : resultList) { 
					resultStringList.add(String.valueOf(myInt)); 
				}
		return resultStringList;
	}

}
