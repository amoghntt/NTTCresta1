package com.nttdata.web.utils;

import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JubatusUtils {

//	private static final String userName = "cresta";
//	private static final String password = "nttdata@123";
//	private static final String serverUrl = CrestaQueryConstants.HOST; //"10.248.3.91";
//	private static final int port = 22;
//	private static final String jubatusEnvironment = "source /opt/jubatus/profile; ";
//
//	public static final String JUBACLASSIFIER = "jubaclassifier";
//	public static final String JUBAREGRESSION = "jubaregression";

//	public static void main(String args[])
//	{
//		startJubatus(CrestaConstants.JUBAREGRESSION, CrestaConstants.JUBAREGRESSION_CONFIG_FILE_PATH,
//				CrestaQueryConstants.PRED_PORT);
//		
//		//check using lsof -t -i:9192 on Jubatus server console
//	}
	
	
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
			
//			((ChannelExec) channel).setCommand(" jubaregression -p "+jubatusPort+" --configpath config.json");
//			channel.connect();
			
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
}
