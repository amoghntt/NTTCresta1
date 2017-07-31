package com.nttdata.web.usecase4.service;

import java.util.List;

import com.nttdata.web.usecase4.model.AdaptivePlanningModel;

public interface AdaptivePlanningService {

	List<AdaptivePlanningModel> getDataFromCSV(String filePath);

	List<String> convertIntListToString(List<Integer> resultList);

}
