<%@ page language="java" import="java.util.*" %> 
<%@page import="java.io.InputStream" %>
<%@page import="java.util.Properties" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="master.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Case Optimization</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<script type="text/javascript" 
		src="<c:url value="/resources/js/jquery.techbytarun.excelexportjs.js"/>">
		

	
</script>
<script type="text/javascript">
$(document).ready(function () {
	
	$('#').hide();
	
    $("#btnExport").click(function () {
        $("#table-1").excelexportjs({
            containerid: "table-1"
           , datatype: 'table'
        }); 
        
    });
});


</script>
<style type="text/css">
tr.blank_row td {
  border: 0;
}
.blank_row
{
    height: 40px !important; /* Overwrite any previous rules */
    background-color: #FFFFFF;
}
.paddingCell{
padding: 10px;
}


</style>
</head>
<body>

<%
ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream("config.properties");
    Properties prop = new Properties();
    prop.load(stream);
    Double testRedundancyCriteria = Double.parseDouble(prop.getProperty("redundancy"));
    Double testDuplicateCriteria = Double.parseDouble(prop.getProperty("duplicate"));
    pageContext.setAttribute("redundancyCriteria", testRedundancyCriteria);
    pageContext.setAttribute("duplicateCriteria", testDuplicateCriteria);
%>
<center>
	<div style="background-color: white; width:900px ;height: 600px;overflow: scroll;">
		<table id="table-1" border="1px" bordercolor="black">
			<thead class="fixedHeader">
				<tr>
					<th class="paddingCell"></th>
					<c:forEach items="${resultList}" var="item">
						<td class="paddingCell" align="center" style="font-weight: bold;"><c:out
								value="${item.testCaseId}" /></td>
					</c:forEach></tr></thead>
					<tbody class="resultTable">
					<c:forEach items="${resultList}" var="item">
						<tr>
							<td class="paddingCell" align="center" style="font-weight: bold;"><c:out
									value="${item.testCaseId}" /></td>

							<c:forEach items="${item.testCaseSimilarity}" var="similarity">

								<c:choose>
									<c:when test="${similarity == 100.00}">
										<td class="paddingCell" align="center"><c:out value="${similarity}" />%</td>
									</c:when>
									<%-- <c:when test="${similarity > 90.00}"> --%>
									<c:when test="${similarity > duplicateCriteria}">
										<td class="paddingCell" align="center" style="background-color: red"><c:out
												value="${similarity}" />%</td>
									</c:when>
									<%-- <c:when test="${similarity > 70.00 && similarity < 90.00}"> --%>
									<c:when test="${similarity > redundancyCriteria}">
									<c:if test="${similarity < duplicateCriteria }">

										<td class="paddingCell" align="center" style="background-color: #ffb84d"><c:out
												value="${similarity}" />%</td>
</c:if>
									</c:when>
									<c:otherwise>
										<td class="paddingCell" align="center" style="background-color: #aeeaae"><c:out
												value="${similarity}" />%</td>
									</c:otherwise>
								</c:choose>
							</c:forEach>

						</tr>
					</c:forEach>
					<tr class="blank_row">
					<c:forEach items="${resultList}" var="item">
					 <td colspan="1"></td>
					</c:forEach>
					

				</tr>

				
	<tr><td style="background-color: red ;width: 20px"></td><td style="padding-left: 5px;width: 160px">Duplicate Test Cases</tr>
	<tr>
	<td style="background-color: #ffb84d;width: 20px"></td><td style="padding-left: 5px;width: 160px">Redundant Test Cases</td>
	</tr>
	<tr><td style="background-color: #aeeaae ;width: 20px"></td><td style="padding-left: 5px;width: 160px">Distinct Test Cases</tr>
			</tbody>
		</table>
		

	</div>
	<br><br>
	<div style="background-color: white; width: 180px ">
	<table border="1px" bordercolor="black" style="border-spacing: 10px;">
	
	<tr>
	<tr><td style="background-color: red ;width: 20px"></td><td style="padding-left: 5px;width: 160px">Duplicate Test Cases</tr>
	<tr>
	<td style="background-color: #ffb84d;width: 20px"></td><td style="padding-left: 5px;width: 160px">Redundant Test Cases</td>
	</tr>
	<tr><td style="background-color: #aeeaae ;width: 20px"></td><td style="padding-left: 5px;width: 160px">Distinct Test Cases</tr>
	
	</table></div>
	<br/>
		<button id="btnExport">Export to excel</button>
</center>
</body>
</html>