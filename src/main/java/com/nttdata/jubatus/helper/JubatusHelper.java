package com.nttdata.jubatus.helper;

import java.net.UnknownHostException;
import java.util.List;
import org.msgpack.rpc.loop.EventLoop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.web.model.MetricsBean;
import com.nttdata.web.usecase3.model.DefectLeakageModel;
import com.nttdata.web.utils.CrestaConstants;
import com.nttdata.web.utils.CrestaQueryConstants;
import com.nttdata.web.utils.JubatusUtils;

import us.jubat.stat.StatClient;

public class JubatusHelper {
	private static final Logger log = LoggerFactory.getLogger(JubatusHelper.class);
	private StatClient client;

	public JubatusHelper() {
		try {
			client = new StatClient(CrestaQueryConstants.HOST, CrestaQueryConstants.PORT, CrestaQueryConstants.NAME,
					CrestaQueryConstants.CLIENT_TIMEOUT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public String[] startCalcUclLcl(int weightagesId, List<Integer> defectCount) throws Exception {
		int defectCountUcl = 0;
		int defectCountLcl = 0;
		String[] calculatedValueArray = new String[4];
		try {
			String key = null;
			switch (weightagesId) {
			case 1:
				key = "defect_density";
				break;
			case 2:
				key = "defect_leakage";
				break;
			case 3:
				key = "functional_defect_rejection";
				break;
			case 4:
				key = "defect_acceptance_count";
				break;
			case 13:
				key = "defect_deferral_count";
				break;
			case 14:
				key = "functional_defect_count";
				break;
			case 15:
				key = "defect_count";
				break;
			default:
				break;
			}
			
			for (Integer defectCountUcllcl : defectCount) {
				client.push(key, defectCountUcllcl);

			}

			defectCountUcl = (int) Math.round(client.moment(key, 1, 0.0) + 3.0 * client.stddev(key));

			defectCountLcl = (int) Math.round(client.moment(key, 1, 0.0) - 3.0 *  client.stddev(key));
			if (defectCountLcl < 0)
				defectCountLcl = 0;
			if (defectCountUcl < 0)
				defectCountUcl = 0;
		
			System.out.println("UCL :" + key + " " + defectCountUcl);
			System.out.println("LCL :" + key + " " + defectCountLcl);
			calculatedValueArray[0] = String.valueOf(defectCountUcl);
			calculatedValueArray[1] = String.valueOf(defectCountLcl);

		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("Exception in StartCalcUclLcl() method");
		}

		finally {
			client.getClient().close();
		}
		return calculatedValueArray;
	}
	
	
	public String[] startCalcUclLcl1(int weightagesId, List<DefectLeakageModel> defectCount) throws Exception {
		int defectCountUcl = 0;
		int defectCountLcl = 0;
		int defectCountSecondUcl = 0;
		int defectCountSecondLcl = 0;

		String[] calculatedValueArray = new String[4];
		try {
			String key = null;
			if (weightagesId == 2) {
				key = "defect_leakage";
			} 
			for (DefectLeakageModel defectDensityBean  : defectCount) {
				client.push(key, defectDensityBean.getDefectLeakage());
			}

			// This is also for severity i.e. High, Medium and Low.
			// display current result.
			if(key.equals("defect_leakage")){
				defectCountUcl = (int) Math.round(client.moment(key, 1, 0.0) + 3.0 * client.stddev(key));

				defectCountLcl = (int) Math.round(client.moment(key, 1, 0.0) - 3.0 *  client.stddev(key));
			} else {
				defectCountUcl = (int) Math.round(client.moment(key, 1, 0.0) + client.stddev(key));

				defectCountLcl = (int) Math.round(client.moment(key, 1, 0.0) - client.stddev(key));
			}
			
			defectCountSecondUcl = (int) Math.round(client.moment(key, 1, 0.0) + 2.0 * client.stddev(key));
			defectCountSecondLcl = (int) Math.round(client.moment(key, 1, 0.0) - 2.0 * client.stddev(key));
			if (defectCountLcl < 0)
				defectCountLcl = 0;
			if (defectCountUcl < 0)
				defectCountUcl = 0;
			if (defectCountSecondUcl < 0)
				defectCountSecondUcl = 0;
			if (defectCountSecondLcl < 0)
				defectCountSecondLcl = 0;
			System.out.println("UCL :" + key + " " + defectCountUcl);
			System.out.println("LCL :" + key + " " + defectCountLcl);
			System.out.println("Second UCL :" + key + " " + defectCountSecondUcl);
			System.out.println("Second LCL :" + key + " " + defectCountSecondLcl);
			calculatedValueArray[0] = String.valueOf(defectCountUcl);
			calculatedValueArray[1] = String.valueOf(defectCountLcl);
			calculatedValueArray[2] = String.valueOf(defectCountSecondUcl);
			calculatedValueArray[3] = String.valueOf(defectCountSecondLcl);

		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("Exception in StartCalcUclLcl1() method");
		}

		finally {
			client.getClient().close();
		}
		return calculatedValueArray;
	}

	public MetricsBean getCalculateLimit(int weightagesId, List<Integer> defectCount) {
		JubatusUtils.startJubatus(CrestaConstants.JUBASTAT,
				CrestaConstants.JUBASTAT_CONFIG_FILE_PATH, CrestaQueryConstants.PORT);
		MetricsBean metricsBean = new MetricsBean();
		try {
			String[] finalValue = new JubatusHelper().startCalcUclLcl(weightagesId, defectCount);
			int[] ucl = new int[3];
			int[] lcl = new int[3];
			ucl[0] = Integer.parseInt(finalValue[0]);
			lcl[0] = Integer.parseInt(finalValue[1]);
			metricsBean.setUcl(ucl);
			metricsBean.setLcl(lcl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EventLoop.defaultEventLoop().shutdown();
			EventLoop.setDefaultEventLoop(null);
		}
		return metricsBean;
	}
	
	public MetricsBean getCalculateLimit1(int weightagesId, List<DefectLeakageModel> defectCount) {
		MetricsBean metricsBean = new MetricsBean();
		try {
			String[] finalValue = new JubatusHelper().startCalcUclLcl1(weightagesId, defectCount);
			int[] ucl = new int[3];
			int[] lcl = new int[3];
			ucl[0] = Integer.parseInt(finalValue[0]);
			ucl[1] = Integer.parseInt(finalValue[2]);
			lcl[0] = Integer.parseInt(finalValue[1]);
			lcl[1] = Integer.parseInt(finalValue[3]);
			metricsBean.setUcl(ucl);
			metricsBean.setLcl(lcl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EventLoop.defaultEventLoop().shutdown();
			EventLoop.setDefaultEventLoop(null);
		}
		return metricsBean;
	}
}
