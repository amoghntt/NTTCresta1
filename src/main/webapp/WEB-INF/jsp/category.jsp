<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="master.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/button-style.css">
<meta charset="UTF-8">
<title>Line Chart Test</title>

</head>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/metricsvalidations.js"/>"> </script>
<body>

<form:form method="post">
<div id="defects" class="tabcontent">
		<table  align="center" style="font-family: Verdana;font-weight: bold; color:#FF8C00">
			<tr>
				<td>Project</td>
				<td>${configBean.projectBean.projectName}</td>
			</tr>
			<tr>
				<td>Prediction  &nbsp;</td>
				<td>${configBean.predictBean.predictDescription}</td>
			</tr>
			<tr>
				<td>Trend</td>
				<td>Module Wise</td>
			</tr>
			</table >
			<table  align="center" border = "1">
			<tr>
				<td bgcolor="red" align="center" style="padding-left: 10px;;padding-right: 10px">High Risk</td>
				<td bgcolor="amber" style="padding-left: 15px;padding-right: 15px" align="center">Risk</td>
				<td bgcolor="green" style="padding-left: 10px;;padding-right: 10px" align="center">Normal</td>
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
</div>
	</form:form>
</body>
</html>
