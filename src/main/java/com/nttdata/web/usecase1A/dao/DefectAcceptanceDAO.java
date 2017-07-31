package com.nttdata.web.usecase1A.dao;

import java.util.List;

import com.nttdata.web.model.ReleaseDetails;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModel;
import com.nttdata.web.usecase1A.model.DefectAcceptanceModelTelephonica;

public interface DefectAcceptanceDAO {
	public List<DefectAcceptanceModel> getDefectAcceptanceAndReleaseData(String userId);
	public List<DefectAcceptanceModel> getDefectAcceptanceData(int userid) ;
	
	public List<DefectAcceptanceModelTelephonica> getDefectAcceptanceAndReleaseDataTelephonica(String userId);
	public List<DefectAcceptanceModelTelephonica> getDefectAcceptanceDataTelephonica(int userid) ;
	List<ReleaseDetails> getLastTenPredictions();
}
