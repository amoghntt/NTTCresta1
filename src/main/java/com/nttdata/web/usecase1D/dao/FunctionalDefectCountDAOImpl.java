package com.nttdata.web.usecase1D.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nttdata.web.usecase1D.model.FunctionalDefectCountModel;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
public class FunctionalDefectCountDAOImpl implements FunctionalDefectCountDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FunctionalDefectCountModel> getFunctionalDefectCountAndReleaseData(String userId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_FUNCTIONAL_DEFECT_COUNT;
		List<FunctionalDefectCountModel> functionalDefectCountModelList = jdbcTemplate.query(sqlQuery, new Object[] {Integer.parseInt(userId)}, new RowMapper<FunctionalDefectCountModel>() {
			@Override
			public FunctionalDefectCountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				FunctionalDefectCountModel functionalDefectCountModel = new FunctionalDefectCountModel();
				
				functionalDefectCountModel.setRelease((rs.getInt("release_version")));
				functionalDefectCountModel.setDefectCount((rs.getInt("defect_count")));
				functionalDefectCountModel.setKiloLinesOfCode(rs.getInt("KLOC"));
				functionalDefectCountModel.setTestCaseCount(rs.getInt("test_case_count"));
				functionalDefectCountModel.setApplicationComplexity(rs.getInt("application_complexity"));
				functionalDefectCountModel.setDomainKnowledge(rs.getInt("domain_knowledge"));
				functionalDefectCountModel.setTechnicalSkills(rs.getInt("technical_skills"));
				functionalDefectCountModel.setRequirementQueryCount(rs.getInt("requirements_query_count"));
				functionalDefectCountModel.setCodeReviewComments((rs.getInt("code_review_comments")));
				functionalDefectCountModel.setDesignReviewComments((rs.getInt("design_review_comments")));
				return functionalDefectCountModel;
			}
		});
		return functionalDefectCountModelList;
		
	}
}
