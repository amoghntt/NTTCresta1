<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="master.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adaptive Planning Report</title>
<style type="text/css">
.tableCells{
text-align: center;
}
.tableHead{
font-size: large;
text-align: center;
}
</style>
<script type="text/javascript"
	src="<c:url value="/resources/js/jspdf.debug.js"/>">
	
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jspdf.plugin.autotable.src.js"/>">
	
</script>
<script type="text/javascript">
function generatePDFFromTable() {
	var data = [];

	var doc = new jsPDF('p', 'pt');
	var res = doc.autoTableHtmlToJson(document.getElementById("tableResult"));
	doc.autoTable(res.columns, res.data, {
		startY : 40
	});

	doc.save("table.pdf");
}
</script>
</head>
<body>
<center>

<div style="background-color: white; width: 40%" >
<table class = "table table-bordered" id="tableResult">
<thead>
<tr>
<th class="tableHead">Parameter</th>
<th class="tableHead">Value</th></tr>
</thead>
<tbody>
<tr><td class="tableCells">Release ID&nbsp;:</td>
<td class="tableCells">${releaseId }</td>
</tr>
<tr>
<td class="tableCells">Project Status&nbsp;:</td>
<td class="tableCells">${projectStatus }</td>
</tr>
<tr>
<td class="tableCells">Adaptive Budget&nbsp;:</td>
<td class="tableCells">${adaptiveBudget }</td>
</tr>
<tr>
<td class="tableCells">Available Budget&nbsp;:</td>
<td class="tableCells">${availableBudget }</td>
</tr>

</tbody>





</table>
</div>
<br/><br/>
<button id="btnExportPDF" onclick="generatePDFFromTable()">Export
			to PDF</button>
</center>
</body>
</html>