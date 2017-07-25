package com.nttdata.pythonexecutor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Java2PythonExecutor {
	private static Logger log = LoggerFactory.getLogger(Java2PythonExecutor.class);

	@SuppressWarnings("unchecked")
	public static int getPredictionResult(String python_script_file, String prediction_code, String userId) {
		int result = 0;
		try {
			Resource yourfile = new ClassPathResource(python_script_file);
			String filePath = yourfile.getFile().getAbsolutePath();
			log.info("filePath = " + filePath);
			ProcessBuilder pb = new ProcessBuilder(ScikitConstants.PYTHON_COMMAND, filePath, prediction_code, userId);
			pb.redirectErrorStream(true);
			Process p = pb.start();

			ObjectMapper mapper = null;
			ArrayList<Double> resultList = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = in.readLine();
			log.info("line = " + line);
			if (line != null) {
				mapper = new ObjectMapper();
				resultList = mapper.readValue(line, ArrayList.class);
				result = (int) Math.round(resultList.get(0));
				log.info("Predicted Result = " + result);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}


	@SuppressWarnings("unchecked")
	public static int getPredictionResultBoADefectCount(String python_script_file) {
		int result = 0;
		try {
			Resource yourfile = new ClassPathResource(python_script_file);
			String filePath = yourfile.getFile().getAbsolutePath();
			log.info("filePath = " + filePath);
			ProcessBuilder pb = new ProcessBuilder(ScikitConstants.PYTHON_COMMAND, filePath);
			pb.redirectErrorStream(true);
			Process p = pb.start();

			ObjectMapper mapper = null;
			ArrayList<Double> resultList = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = in.readLine();
			log.info("line = " + line);
			if (line != null) {
				mapper = new ObjectMapper();
				resultList = mapper.readValue(line, ArrayList.class);
				result = (int) Math.round(resultList.get(0));
				log.info("Predicted Result = " + result);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	
	@SuppressWarnings("unchecked")
	public static HashMap<String, Double> getTestCoverage(String pythonScript) {
		HashMap<String, Double> resultList = null;
		try {
			Resource yourfile = new ClassPathResource(pythonScript);
			String filePath = yourfile.getFile().getAbsolutePath();
			log.info("filePath = " + filePath);
			ProcessBuilder pb = new ProcessBuilder(ScikitConstants.PYTHON_COMMAND, filePath);

			pb.redirectErrorStream(true);
			Process p = pb.start();

			ObjectMapper mapper = null;

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = in.readLine();
			if (line != null) {
				mapper = new ObjectMapper();
				resultList = mapper.readValue(line, HashMap.class);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public static List<ArrayList<Double>> getTestOptimization(String pythonScript) {
		List<ArrayList<Double>> resultList = null;
		try {
			Resource yourfile = new ClassPathResource(pythonScript);
			String filePath = yourfile.getFile().getAbsolutePath();
			log.info("filePath = " + filePath);
			ProcessBuilder pb = new ProcessBuilder(ScikitConstants.PYTHON_COMMAND, filePath);

			pb.redirectErrorStream(true);
			Process p = pb.start();

			ObjectMapper mapper = null;

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = in.readLine();
			if (line != null) {
				mapper = new ObjectMapper();
				resultList = mapper.readValue(line, List.class);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Double> getAdaptivePlanning(String pythonScript, List<Integer> inputList) {
		List<String> resultStringList = convertIntListToString(inputList);
		ArrayList<Double> resultList = null;
		try {
			Resource yourfile = new ClassPathResource(pythonScript);
			String filePath = yourfile.getFile().getAbsolutePath();
			log.info("filePath = " + filePath);
			StringBuilder inputData = new StringBuilder();
			String prefix = "";
			inputData.append("[");
			for (String s : resultStringList) {
				inputData.append(prefix);
				prefix = ",";
				inputData.append(s);
			}
			inputData.append("]");
			System.out.println(inputData.toString());
			String[] commands = { ScikitConstants.PYTHON_COMMAND, filePath, inputData.toString() };
			ProcessBuilder pb = new ProcessBuilder(commands);

			pb.redirectErrorStream(true);
			Process p = pb.start();

			ObjectMapper mapper = null;

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = in.readLine();
			if (line != null) {
				mapper = new ObjectMapper();
				resultList = mapper.readValue(line, ArrayList.class);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		resultList.add(inputList.get(15).doubleValue());
		return resultList;
	}

	public static List<String> convertIntListToString(List<Integer> resultList) {
		/* Specify the size of the list up front to prevent resizing. */
		List<String> resultStringList = new ArrayList<String>(resultList.size());
		for (Integer myInt : resultList) {
			resultStringList.add(String.valueOf(myInt));
		}
		return resultStringList;
	}
}
