package com.nttdata.web.usecase2TC.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nttdata.web.usecase2TC.model.TestCaseOptimizationBean;
import com.nttdata.web.usecase2TC.service.TestCaseOptimizationService;
@Controller
public class TestCaseOptimizationController  extends HttpServlet {
	
	@Autowired
	private TestCaseOptimizationService testCaseOptimizationService;

	private static final long serialVersionUID = 1L;
	String projectName = null;
	@RequestMapping(value ="/testoptimization", method = RequestMethod.POST)
	public ModelAndView viewTestCaseOptimization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
	         // Create a factory for disk-based file items
	         DiskFileItemFactory factory = new DiskFileItemFactory();

	         // Create a new file upload handler
	         ServletFileUpload upload = new ServletFileUpload(factory);
	 
	            try {
	             // Parse the request
	             List<FileItem>  items = upload.parseRequest(request);
	                Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();
	                    if (item.isFormField()) {
							if (item.getFieldName().equals("projectName")) {
								projectName = item.getString();
							}

						}
	                    if (!item.isFormField()) {
	                        item.getName();  
	                        String root = "/opt/apache-tomcat-8.0.36/webapps/resources1";
	                        File path = new File(root);
	                        if (!path.exists()) {
	                            path.mkdirs();
	                        }
//	                        File uploadedFile = new File(path + "/" + fileName);
	                        //Hard coded the file name to be uploaded in server repository...
	                        File uploadedFile = new File(path + "/" + "test-case.xlsm");
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        item.write(uploadedFile);
	                    }
	                }
	            } catch (FileUploadException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		List<TestCaseOptimizationBean> testCaseResultList = new ArrayList<TestCaseOptimizationBean>();
		testCaseResultList = testCaseOptimizationService.getOptimizationResult();
		ModelAndView modelView = new ModelAndView("testOptimizationResult");
		modelView.addObject("projectName", projectName);
		modelView.addObject("resultList", testCaseResultList);
		return modelView;
	}
	
	}
