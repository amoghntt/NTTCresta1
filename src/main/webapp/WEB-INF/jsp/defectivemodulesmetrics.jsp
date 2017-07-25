<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="master.jsp"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/button-style.css">
<style>
.loader {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url("<%=request.getContextPath()%>/resources/images/processing.gif") 50% 50% no-repeat rgb(249,249,249);
}
</style>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/metricsvalidations.js"/>"> </script>
<script type="text/javascript">

function check()
{
	var count = "${metricsSize}";
		if(count==1)
		{
		document.getElementById('weightageDiv').style.display='none';
		document.getElementById('weightage').style.display='none';
		
		}
	}
function calculateLimits(counter) {
	//alert('11');
	var metricsId = $("#metricsId"+counter).val();

	//alert(metricsId);
 $.ajax({
  type: "POST",
  url: "calulateLimit", 
  data:'metricsId=' + metricsId,
  dataType: "json",
  success: function(data){
	$("#uclId"+counter+"0").val(data.ucl[0]);
	$("#lclId"+counter+"0").val(data.lcl[0]);
	$("#uclId"+counter+"1").val(data.ucl[1]);
	$("#lclId"+counter+"1").val(data.lcl[1]);
  },
  error: function(){      
   	alert('Error while request..');
  }
 });
	
}
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
	metricLength = "${metricsSize}";
})
</script>

</head>

<body onload="check()">
   
<div class="loader"></div>
<div id="defects" class="tabcontent">
	<table align="center" style="font-family: Verdana;font-weight: bold;color:#FF8C00">
<tr>
<td> Project
</td>
<td>
${configBean.projectBean.projectName}

</td>
</tr>
<tr>
<td>Prediction  &nbsp;</td>
<td> 
${configBean.predictBean.predictDescription}
 	
	</td>
</tr>
<tr>
<td>Trend
</td>
<td>
Release Wise
</td>
</tr>
</table>
 <br/>
	 <br/>
<c:if test="${fn:length(configBean.predictBean.metricsList) gt 0}">

<form:form method="POST" action="predictdefectmodule">
	
		<table align="center" style="font-family: Verdana;font-weight: bold;">
		<tr><td></td><td></td><td align="center">LCL</td>
		<td align="center">UCL</td>
		<td align="center"><div id="weightage">Weightage</div></td>
		</tr>
				<c:forEach items="${configBean.predictBean.metricsList}" var="metrics" varStatus="counter">
					<tr> 
						<td>
							<c:out value="${metrics.metricsName} :"/> &nbsp;
						</td>										
						<%-- <td>
							<input  class="textb" type="text" id="weightagesId${counter.count}" name="weightages" value="<c:out value="${metrics.weightage}"/>">
						</td> --%>
						
						<td>
							<input  class="button" type="button" id="calculate" onclick="calculateLimits(${counter.count  - 1});" value="Reset" style="font-weight: bold;" /> &nbsp;
							<input type="hidden" id="metricsId${counter.count -1}" name="predictBean.metricsList[${counter.count - 1}].metricsId" value="${metrics.metricsId}"/>
							<input  class="textb" type="hidden" id="uclId${counter.count-1}1" name="predictBean.metricsList[${counter.count  - 1}].ucl[1]" value="<c:out value="${metrics.ucl[1]}"/>">
							<input  class="textb" type="hidden" id="lclId${counter.count-1}1" name="predictBean.metricsList[${counter.count  - 1}].lcl[1]" value="<c:out value="${metrics.lcl[1]}"/>">
							
							
							</td>	
						<td>
							<input  class="textb"  type="text" id="lclId${counter.count-1}0" name="predictBean.metricsList[${counter.count  - 1}].lcl[0]" value="<c:out value="${metrics.lcl[0]}"/>"> &nbsp;
							
							
						</td>
						<td>
							<input  class="textb" type="text" id="uclId${counter.count-1}0" name="predictBean.metricsList[${counter.count  - 1}].ucl[0]" value="<c:out value="${metrics.ucl[0]}"/>">
					</td>
							<td>
							<div id="weightageDiv">
							<input  class="textb" type="text" id="weightageId${counter.count-1}" name="predictBean.metricsList[${counter.count  - 1}].weightage" value="<c:out value="${metrics.weightage}"/>">
							</div>	
							
						</td>	
								
					</tr>
				</c:forEach>
				<tr ><td align="left" colspan="2" rowspan="2"> Algorithm : &nbsp;</td>
						<td rowspan="2" ><select name="projectBean.redmineProjectId" style="width: 170px;">
								<option value=0>Jubatus Regression</option>
						</select></td>
					</tr>
				<tr></tr>
					<tr>
						<td colspan="4" align="center">
						
								<input  class="myButton" type="submit" value="Predict" onclick="return validateForm()" />
						
						</td>
											
				</tr>	
		</table>
		</form:form>

</c:if>
</div>
<div id="testcases" class="tabcontent"></div>

</body>
</html>
