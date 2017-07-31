package com.nttdata.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.web.talend.MySQLFetchDefectDensity;

@Controller
public class DataRetrieveController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	/*@Autowired
	@Resource(name = "etlExecutorBean")*/
	//private EtlExecutorBean etlExecutorBean;
	
	
	
	@RequestMapping(value="/fetchRawData", method=RequestMethod.GET)
	public String retrieveData(){		
		try {
			MySQLFetchDefectDensity defectDensityJob = new MySQLFetchDefectDensity();
			defectDensityJob.runJob(new String []{});
			//etlExecutorBean.execute();
			/*try {
				FindDefectsService.predictData(0);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		} catch (Exception e) { 
		//} catch (EtlExecutorException etlExecutorException) { 
			log.debug("Error executing ETL file" + e);
			e.printStackTrace();
		}
		return "defects";
	}
}
