package com.nttdata.web.usecase3A.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nttdata.pythonexecutor.Java2PythonExecutor;
import com.nttdata.pythonexecutor.ScikitConstants;
import com.nttdata.web.usecase3A.model.TestCaseCoverageBean;

@Service
public class TestCaseCoverageServiceImpl implements TestCaseCoverageService {
	private static Logger log = LoggerFactory.getLogger(TestCaseCoverageServiceImpl.class);

	public void writeRequirementToFile(FileItem item) {
		BufferedWriter writer = null;
		try {
			File file = new File("/opt/apache-tomcat-8.0.36/webapps/resources1/textrequirement.txt");
			System.out.println(file.getCanonicalPath());
			writer = new BufferedWriter(new FileWriter(file));
			String outputToFile = item.getString();
			outputToFile = outputToFile.replace("\n", "").replace("\r", "");
			writer.write(outputToFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what
				// happens...
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public List<TestCaseCoverageBean> getTestCaseCoverageResult() {

		Double testCoverageCriteria = null;
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "config.properties";
			input = TestCaseCoverageServiceImpl.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);

			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// get the property value and print it out
			testCoverageCriteria = Double.parseDouble(prop.getProperty("coverage")) / 100;

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		HashMap<String, Double> resultList = new HashMap<String, Double>();
		resultList = Java2PythonExecutor.getTestCoverage(ScikitConstants.UC3_LSA_SCRIPT);
		log.info("resultList = " + resultList);
		if (resultList != null) {
			Map<String, Double> sortedResultListMap = new TreeMap<>(
					Comparator.comparingInt(key -> Integer.parseInt(key.substring(2))));
			for (Map.Entry<String, Double> entry : resultList.entrySet()) {
				sortedResultListMap.put(entry.getKey(), entry.getValue());
			}
			System.out.println(sortedResultListMap);
			List<TestCaseCoverageBean> testCaseResultList = new ArrayList<TestCaseCoverageBean>();
			String relevantTestCase = "O";
			String irrelevantTestCase = "X";
			String indeterminateTestCase = "Indeterminate";

			for (Map.Entry<String, Double> entry : sortedResultListMap.entrySet()) {
				Double value = entry.getValue();

				if (value > testCoverageCriteria) {
					DecimalFormat df = new DecimalFormat("#.00");
					Double percentageResult = Double.parseDouble(df.format(value * 100));
					TestCaseCoverageBean testCaseCoverageBean = new TestCaseCoverageBean();
					testCaseCoverageBean.setPercentageCoverage(percentageResult.toString() + "%");
					testCaseCoverageBean.setTestCaseStatus(relevantTestCase);
					testCaseCoverageBean.setTestCaseId(entry.getKey());
					testCaseResultList.add(testCaseCoverageBean);
				} else if (value > 0.00) {
					DecimalFormat df = new DecimalFormat("#.00");
					Double percentageResult = Double.parseDouble(df.format(value * 100));
					TestCaseCoverageBean testCaseCoverageBean = new TestCaseCoverageBean();
					testCaseCoverageBean.setPercentageCoverage(percentageResult.toString() + "%");
					testCaseCoverageBean.setTestCaseStatus(irrelevantTestCase);
					testCaseCoverageBean.setTestCaseId(entry.getKey());
					testCaseResultList.add(testCaseCoverageBean);
				} else {
					TestCaseCoverageBean testCaseCoverageBean = new TestCaseCoverageBean();
					testCaseCoverageBean.setPercentageCoverage("NA");
					testCaseCoverageBean.setTestCaseStatus(indeterminateTestCase);
					testCaseCoverageBean.setTestCaseId(entry.getKey());
					testCaseResultList.add(testCaseCoverageBean);
				}
			}
			return testCaseResultList;
		} else {
			List<TestCaseCoverageBean> testCaseResultList = new ArrayList<TestCaseCoverageBean>();
			testCaseResultList = null;
			return testCaseResultList;
		}
	}
}
