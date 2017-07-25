package com.nttdata.web.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JubatusUtils {

	public static boolean startJubatus(String jubatusServer, String configpath, int jubatusPort) {
		boolean result = false;

		StringBuilder sb = new StringBuilder(CrestaConstants.JUBATUS_SERVER_ENVIRONMENT_FILE);
		sb.append(" ");
		sb.append(jubatusServer);
		sb.append(" ");
		sb.append(" --configpath ");
		sb.append(configpath);
		sb.append(" --rpc-port ");
		sb.append(jubatusPort);
		sb.append(" & ");
		String startCommand = sb.toString();
		System.out.println(startCommand);

		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(CrestaConstants.JUBATUS_SERVER_OS_USERNAME, CrestaConstants.JUBATUS_SERVER_HOST_IP);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.setPort(CrestaConstants.JUBATUS_SERVER_JSCH_PORT);
			session.setPassword(CrestaConstants.JUBATUS_SERVER_OS_PASSWORD);

			session.connect();
			Channel channel = session.openChannel("exec");
			
			((ChannelExec) channel).setCommand(startCommand);
			channel.connect();
			Thread.sleep(3000);
			
			channel.disconnect();
			session.disconnect();
			System.out.println(jubatusServer + " started on port " + jubatusPort);

			result = true;
			
		} catch (JSchException | InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean stopJubatus(int jubatusPort) {
		boolean result = false;

		String stopCommand = String.format("kill -9 $(lsof -t -i:%d)", jubatusPort);
		System.out.println(stopCommand);

		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(CrestaConstants.JUBATUS_SERVER_OS_USERNAME, CrestaConstants.JUBATUS_SERVER_HOST_IP);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.setPort(CrestaConstants.JUBATUS_SERVER_JSCH_PORT);
			session.setPassword(CrestaConstants.JUBATUS_SERVER_OS_PASSWORD);
			session.connect();
			
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(stopCommand);
			channel.connect();
			
			Thread.sleep(1000);
			
			channel.disconnect();
			session.disconnect();
			System.out.println("Jubatus server stopped on port : " + jubatusPort);

			result = true;
			
		} catch (JSchException | InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	public static String readPropertiesFile(String key) {
		String propertyValue = null;
		try {
			InputStream input = new FileInputStream(CrestaQueryConstants.PROPERTIES_FILE_PATH);
			if (input == null) {
				System.out.println("Sorry, unable to find properties file");

			}
			Properties prop = new Properties();
			prop.load(input);
			propertyValue = prop.getProperty(key);
			System.out.println("Read value from config file "+ propertyValue);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return propertyValue;
	}
}
