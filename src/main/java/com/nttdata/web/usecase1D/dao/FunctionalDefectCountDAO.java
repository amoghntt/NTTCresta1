package com.nttdata.web.usecase1D.dao;

import java.util.List;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;

public interface FunctionalDefectCountDAO {
	
	public List<FunctionalDefectCountModel> getFunctionalDefectCountAndReleaseData(String userId);

	List<FunctionalDefectCountModel> getFunctionalDefectCountAndReleaseDataTelephonica(String userId);

	List<ReleaseDetails> getLastTenPredictions(int algorithmId);

	List<FunctionalDefectCountModel> getAllData(String userId);

}
