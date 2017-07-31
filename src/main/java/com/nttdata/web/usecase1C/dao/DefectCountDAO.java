package com.nttdata.web.usecase1C.dao;


import java.util.List;

import com.nttdata.web.usecase1C.model.DefectCountModel;

public interface DefectCountDAO {
	List<DefectCountModel> getDefectCountAndReleaseData(String userId);
	public List<DefectCountModel> getDefectCountData(int userid);
}
