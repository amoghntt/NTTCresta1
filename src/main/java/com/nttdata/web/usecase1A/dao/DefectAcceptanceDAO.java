package com.nttdata.web.usecase1A.dao;

import java.util.List;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModelTelephonica;

public interface DefectAcceptanceDAO {

	public List<DefectAcceptanceModelTelephonica> getDefectAcceptanceAndReleaseDataTelephonica(String userId);
	public List<DefectAcceptanceModelTelephonica> getDefectAcceptanceDataTelephonica(int userid) ;
	List<ReleaseDetails> getLastTenPredictions(int algorithmId);
}
