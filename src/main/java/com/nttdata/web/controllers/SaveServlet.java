/*package com.nttdata.web.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*//**
 * Servlet implementation class SaveServlet
 *//*
@WebServlet(description = "LoadPropertiesFileServlet", urlPatterns = { "/saveServlet" })
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	*//**
	 * @see HttpServlet#HttpServlet()
	 *//*
	public SaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		OutputStream output = new FileOutputStream("resources/config.properties");
		InputStream stream = classLoader.getResourceAsStream("config.properties");
		Properties properties = new Properties();
		properties.load(stream);
		properties.setProperty("redundancy", request.getParameter("textRedundancyCriteria"));
		properties.setProperty("duplicate", request.getParameter("textDuplicateCriteria"));
		properties.store(output, null);
		output.close();

	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
*/