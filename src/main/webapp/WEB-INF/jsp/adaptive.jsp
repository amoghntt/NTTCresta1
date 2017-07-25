<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="master.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adaptive Planning</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript">
	function show(elementId) {
		document.getElementById("id1").style.display = "block";
		document.getElementById("id1").disabled = false;

	}
	function validateForm() {
		var releaseId = document.getElementById("releaseId").value;
		var defectDensity = document.getElementById("defectDensity").value;
		var defectLeakage = document.getElementById("defectLeakage").value;
		var defectRejection = document.getElementById("defectRejection").value;
		var testCaseCount = document.getElementById("testCaseCount").value;
		var domainKnowledge = document.getElementById("domainKnowledge").value;
		var technicalSkills = document.getElementById("technicalSkills").value;
		var codeReviewComments = document.getElementById("codeReviewComments").value;
		var numberOfResources = document.getElementById("numberOfResources").value;
		var applicationComplexity = document
				.getElementById("applicationComplexity").value;
		var requirementQueryCount = document
				.getElementById("requirementQueryCount").value;
		if (releaseId == null || releaseId == "") {

			alert("Please select Release!");
			return false;
		}
		if (numberOfResources <= 0) {
			alert("Number of Resources should be greater than 0!");
			return false;
		} else if (numberOfResources == "" || numberOfResources == null
				|| isNaN(numberOfResources)) {
			alert("Please Enter Number of Resources");
			return false;
		}

		return true;
	}
</script>

<style type="text/css">
<%--
body {
	background:
		url("<%=request.getContextPath()%>/resources/images/background1.png")
		center center fixed;
	background-size: cover;
}

--%>
.example {
	width: 90%;
	height: 10% !important;
	cellspacing: 0px;
}

.example thead th {
	color: white;
	text-align: center;
}

.example tbody td {
	color: white;
	text-align: center;
}

.paddingCell {
	padding: 5px;
}
</style>
</head>
<body>

	<h1 align="center" style="color: lightgreen">Adaptive Planning</h1>
	<!-- <div style="width:100%"> -->

	<div style="width: 100%; height: 200px; overflow-y: scroll;"
		align="center">
		<table class="example" align="center"
			style="width: 100%; border: 1px solid black; border-collapse: collapse;">

			<thead>
				<tr>
					<th class="paddingCell">Release</th>
					<th class="paddingCell">Defect Density</th>
					<th class="paddingCell">Defect Leakage</th>
					<th class="paddingCell">Defect Rejection</th>
					<th width="30px"></th>
					<th class="paddingCell"></th>
					<th class="paddingCell">Test Case Count</th>
					<th class="paddingCell">Application Complexity</th>
					<th class="paddingCell">Domain Knowledge</th>
					<th class="paddingCell">Technical Skills</th>
					<th class="paddingCell">Req. Query Count</th>
					<th class="paddingCell">Code Review Comments</th>
					<th class="paddingCell">Design Review Comments</th>
					<th class="paddingCell">No. of Resources</th>
					<th class="paddingCell">Budget in Use</th>
					<th class="paddingCell">ETA (Days)</th>
					<th class="paddingCell">Cost of Resource</th>
					<th class="paddingCell">Efficiency</th>
					<th class="paddingCell">Project Status</th>
					<th class="paddingCell">Availability of Budget</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${historicalData}" var="item">
					<tr id="${item.releaseId }">
						<td><c:out value="${item.releaseId}" /></td>
						<td><c:out value="${item.defectDensity}" /></td>
						<td><c:out value="${item.defectLeakage}" /></td>
						<td><c:out value="${item.defectRejection}" /></td>
						<td width="30px"></td>
						<td></td>
						<td><c:out value="${item.testCaseCount}" /></td>
						<td><c:out value="${item.applicationComplexity}" /></td>
						<td><c:out value="${item.domainKnowledge}" /></td>
						<td><c:out value="${item.technicalSkills}" /></td>
						<td><c:out value="${item.requirementQueryCount}" /></td>
						<td><c:out value="${item.codeReviewComments}" /></td>
						<td><c:out value="${item.designReviewComments}" /></td>
						<td><c:out value="${item.numberOfResources}" /></td>
						<td><c:out value="${item.budgetInUse}" /></td>
						<td><c:out value="${item.numberOfDaysToComplete}" /></td>
						<td><c:out value="${item.costOfResource}" /></td>
						<td><c:out value="${item.efficiency}" /></td>
						<td><c:out value="${item.projectStatus}" /></td>
						<td><c:out value="${item.availabilityOfBudget}" /></td>
				</c:forEach>
				</tr>

			</tbody>
		</table>
	</div>

	</div>
	<br>
	<br>

	<div style="width: 100%; height: 200px; overflow-y: scroll;"
		align="center">
		<table class="example" id="recentData" align="center"
			style="width: 100%; border: 1px solid black; border-collapse: collapse;">
			<thead>
				<tr>
					<th class="paddingCell"></th>
					<th class="paddingCell">Release</th>
					<th class="paddingCell">Defect Density</th>
					<th class="paddingCell">Defect Leakage</th>
					<th class="paddingCell">Defect Rejection</th>
					<th width="30px"></th>
					<th class="paddingCell"></th>
					<th class="paddingCell">Test Case Count</th>
					<th class="paddingCell">Application Complexity</th>
					<th class="paddingCell">Domain Knowledge</th>
					<th class="paddingCell">Technical Skills</th>
					<th class="paddingCell">Req. Query Count</th>
					<th class="paddingCell">Code Review Comments</th>
					<th class="paddingCell">Design Review Comments</th>
					<th class="paddingCell">No. of Resources</th>
					<th class="paddingCell">Budget in Use</th>
					<th class="paddingCell">ETA (Days)</th>
					<th class="paddingCell">Cost of Resource</th>
					<th class="paddingCell">Efficiency</th>
					<th class="paddingCell">Project Status</th>
					<th class="paddingCell">Availability of Budget</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${recentData}" var="item">
					<tr id="${item.releaseId }">
						<td><input type="radio" class="myradio" name="myradio"
							data-id="${item.releaseId }"></td>
						<td><c:out value="${item.releaseId}" /></td>
						<td><c:out value="${item.defectDensity}" /></td>
						<td><c:out value="${item.defectLeakage}" /></td>
						<td><c:out value="${item.defectRejection}" /></td>
						<td width="30px"></td>
						<td></td>
						<td><c:out value="${item.testCaseCount}" /></td>
						<td><c:out value="${item.applicationComplexity}" /></td>
						<td><c:out value="${item.domainKnowledge}" /></td>
						<td><c:out value="${item.technicalSkills}" /></td>
						<td><c:out value="${item.requirementQueryCount}" /></td>
						<td><c:out value="${item.codeReviewComments}" /></td>
						<td><c:out value="${item.designReviewComments}" /></td>
						<td><c:out value="${item.numberOfResources}" /></td>
						<td><c:out value="${item.budgetInUse}" /></td>
						<td><c:out value="${item.numberOfDaysToComplete}" /></td>
						<td><c:out value="${item.costOfResource}" /></td>
						<td><c:out value="${item.efficiency}" /></td>
						<td><c:out value="${item.projectStatus}" /></td>
						<td><c:out value="${item.availabilityOfBudget}" /></td>
				</c:forEach>
				</tr>

			</tbody>

		</table>
	</div>

	<script>
		$(".myradio").on('click', function() {
			$id = $(this).data('id');

			// getting parent element of the selected radio button
			$parent = $(this).parent().parent();
			$("#editGrid").show();

			$totalRecords = $('td', $parent).length;

			for ($count = 0; $count < $totalRecords; $count++) {
				$input = $("#editGrid td").eq($count);

				if ($('input', $input).length > 0) {
					$('input', $input).val($('td', $parent).eq($count).html());
				}
			}

		});
	</script>

	<br></br>
	<form:form id="predictForm" method="post"
		action="predictAdaptivePlanning" modelAttribute="adaptivePlanningBean">
		<div id="editGrid" style="width: 100%; display: none;" align="center">
			<table id="selectedTable" align="center" style="width: 100%;">
				<tr>
					<td style="width: 50px;"></td>
					<td><input type="hidden" min="0" step="1"
						style="width: 45px; text-align: center" name="releaseId"
						id="releaseId" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="defectDensity"
						id="defectDensity" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="defectLeakage"
						id="defectLeakage" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="defectRejection"
						id="defectRejection" /></td>
					<td><input type="hidden" min="0" step="1"
						style="width: 45px; text-align: center" /></td>
					<td width="30px"></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="testCaseCount"
						id="testCaseCount" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center"
						name="applicationComplexity" id="applicationComplexity" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="domainKnowledge"
						id="domainKnowledge" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="technicalSkills"
						id="technicalSkills" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center"
						name="requirementQueryCount" id="requirementQueryCount" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="codeReviewComments"
						id="codeReviewComments" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center"
						name="designReviewComments" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="numberOfResources"
						id="numberOfResources" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="budgetInUse" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center"
						name="numberOfDaysToComplete" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="costOfResource" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="efficiency" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center" name="projectStatus" /></td>
					<td><input type="number" min="0" step="1"
						style="width: 45px; text-align: center"
						name="availabilityOfBudget" /></td>
				</tr>
			</table>
		</div>

<br/><br/>

		<div align="center" onclick="show('id1');" style="width: 100%;">
			<input type="submit" class="button" id="btnhome" value="Predict"
				onclick="return validateForm()" />
		</div>
	</form:form>

	<br>
	<br>

	</div>



</body>


</html>