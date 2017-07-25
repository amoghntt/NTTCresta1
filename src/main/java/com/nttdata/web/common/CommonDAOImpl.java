package com.nttdata.web.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.web.model.AlgorithmBean;
import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.utils.CrestaQueryConstants;

@Repository
@Transactional(readOnly = true)
public class CommonDAOImpl implements CommonDAO {

	@Autowired
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void deleteDataFromTempDensitytable(int userId, String predictionCode) {

		String sqlQuery = "";
		if (predictionCode.equals("DEFECT_DENSITY")) {
			sqlQuery = CrestaQueryConstants.QRY_USECASE1_TELEPHONICA_DELETE_DATA;
		} else if (predictionCode.equals("DEFECTIVE_MODULES")) {
			sqlQuery = CrestaQueryConstants.QRY_USECASE2_DELETE_DATA;
		}

		else if (predictionCode.equals("DEFECT_LEAKAGE")) {
			sqlQuery = CrestaQueryConstants.QRY_USECASE3_DELETE_DATA;
		} else if (predictionCode.equals("DEFECT_ACCEPTANCE_RATE")) {
			sqlQuery = CrestaQueryConstants.QRY_USECASE1A_TELEPHONICA_DELETE_DATA;

		} else if (predictionCode.equals("FUNCTIONAL_DEFECT_COUNT")) {
			sqlQuery = CrestaQueryConstants.QRY_USECASE1D_TELEPHONICA_DELETE_DATA;
		} else if (predictionCode.equals("DEFECT_DEFERRAL_RATE")) {
			sqlQuery = CrestaQueryConstants.QRY_USECASE1B_TELEPHONICA_DELETE_DATA;
		} else if (predictionCode.equals("DEFECT_COUNT")) {
			sqlQuery = CrestaQueryConstants.QRY_USECASE1C_TELEPHONICA_DELETE_DATA;
		}
		Object[] params = { userId, predictionCode };
		// define SQL types of the arguments
		int[] types = { Types.INTEGER, Types.VARCHAR };

		jdbcTemplate.update(sqlQuery, params, types);

	}

	@Override
	public void saveData(MetricsBean metricsBean) {
		String sqlQuery = CrestaQueryConstants.QRY_UPDATE_DATA;
		int metricsMapping = getMetricsMappingId(metricsBean.getMetricsId(), metricsBean.getPredictionId());
		int[] ucl = metricsBean.getUcl();
		int[] lcl = metricsBean.getLcl();
		Object[] params = { ucl[0], lcl[0], ucl[1], lcl[1], metricsBean.getWeightage(), metricsMapping };
		// define SQL types of the arguments
		int[] types = { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DOUBLE, Types.INTEGER };

		jdbcTemplate.update(sqlQuery, params, types);
	}

	private int getMetricsMappingId(int metricsId, String predictionId) {
		String sqlQuery = CrestaQueryConstants.QRY_GET_METRICS_ID;

		List<Integer> mappingId = new ArrayList<Integer>();

		mappingId = jdbcTemplate.query(sqlQuery, new Object[] { metricsId, predictionId }, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int mapId = rs.getInt("id");

				return mapId;
			}

		});

		return mappingId.get(0);
	}

	@Override
	public List<AlgorithmBean> getAlgorithmListForUser() {
		String sqlQuery = CrestaQueryConstants.QRY_GET_ALGORITHM_LIST;
		List<AlgorithmBean> algorithmList = jdbcTemplate.query(sqlQuery, new Object[] {},
				new RowMapper<AlgorithmBean>() {

					@Override
					public AlgorithmBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						AlgorithmBean algorithmBean = new AlgorithmBean();
						algorithmBean.setAlgorithmId(rs.getInt("id"));
						algorithmBean.setAlgorithmCode(rs.getString("algorithm_code"));
						return algorithmBean;
					}
				});

		return algorithmList;
	}

}
