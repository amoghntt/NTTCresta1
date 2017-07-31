<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="master.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta charset="UTF-8">
<title>Line Chart Test</title>

</head>
<body>

<form:form method="post">
		<table class="table" align="center" >
			<tr>
				<td>Project</td>
				<td>${configBean.projectBean.projectName}</td>
			</tr>
			<tr>
				<td>Prediction</td>
				<td>${configBean.predictBean.predictDescription}</td>
			</tr>
			<tr>
				<td>Trend</td>
				<td>MODULE WISE</td>
			</tr>
			</table >
			<table  align="center" border = "1">
			<tr>
				<td bgcolor="red">High Risk</td>
				<td bgcolor="amber">Risk</td>
				<td bgcolor="green">Normal</td>
			</tr>

			<c:forEach var="displayList" items="${moduleDisplayList}"
				varStatus="displayListCounter">
				<tr>
					<c:forEach items="${displayList}" var="moduleDetails"
						varStatus="counter">
						<td>
						<c:if test="${not empty  moduleDetails}">
							<a href='<c:url value="/predictDefectiveModules?moduleName=${moduleDetails.moduleName}&ddPredictedValue=${moduleDetails.defectDensity}&dlPredictedValue=${moduleDetails.defectLeakage}&drPredictedValue=${moduleDetails.defectRejected}&category=${moduleDetails.category}"/>'>${moduleDetails.moduleName}</a>
						
						</c:if>
						</td>






					</c:forEach>
				</tr>
			</c:forEach>


		</table>

	</form:form>
</body>
</html>
