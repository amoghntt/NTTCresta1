<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="master.jsp"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/button-style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.techbytarun.excelexportjs.js"/>">
	
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jspdf.debug.js"/>">
	
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jspdf.plugin.autotable.src.js"/>">
	
</script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			$("#tblExport").excelexportjs({
				containerid : "tblExport",
				datatype : 'table'
			});
		});
	});
	function generatePDFFromTable() {
		var data = [];

		var doc = new jsPDF('p', 'pt');
		var res = doc.autoTableHtmlToJson(document.getElementById("tblExport"));
		doc.autoTable(res.columns, res.data, {
			startY : 40
		});

		doc.save("table.pdf");
	}
</script>

</script>
<title>Test Case Coverage</title>

</head>

<style type="text/css">

.tableData {
	color: #000;
	font: normal normal 12px Verdana, Geneva, Arial, Helvetica, sans-serif
}

/* end basic styling                                 */

/* define height and width of scrollable area. Add 16px to width for scrollbar          */
div.tableContainer {
	clear: both;
	border: 1px solid #963;
	height: 300px;
	overflow: auto;
	width: 756px;
}

/* Reset overflow value to hidden for all non-IE browsers. */
html>body div.tableContainer {
	overflow: hidden;
	width: 756px
}

/* define width of table. IE browsers only                 */
div.tableContainer table {
	width: 740px
}

/* define width of table. Add 16px to width for scrollbar.           */
/* All other non-IE browsers.                                        */
html>body div.tableContainer table {
	width: 756px
}

thead.fixedHeader tr {
	position: relative
}

html>body thead.fixedHeader tr {
	display: block
}

/* make the TH elements pretty */
thead.fixedHeader th {
	background: #C96;
	border-left: 1px solid #EB8;
	border-right: 1px solid #B74;
	border-top: 1px solid #EB8;
	font-weight: bold;
	font-size: medium;
	padding: 4px 3px;
	text-align: center;
}

/* define the table content to be scrollable                                              */
/* set TBODY element to have block level attributes. All other non-IE browsers            */
/* this enables overflow to work on TBODY element. All other non-IE, non-Mozilla browsers */
/* induced side effect is that child TDs no longer accept width: auto                     */
html>body tbody.scrollContent {
	display: block;
	height: 269px;
	overflow: auto;
	width: 100%
}

tbody.scrollContent td, tbody.scrollContent tr.normalRow td {
	background: #FFF;
	border-bottom: none;
	border-left: none;
	border-right: 1px solid #CCC;
	border-top: 1px solid #DDD;
	padding: 2px 3px 3px 4px
}

tbody.scrollContent tr.alternateRow td {
	background: #EEE;
	border-bottom: none;
	border-left: none;
	border-right: 1px solid #CCC;
	border-top: 1px solid #DDD;
	padding: 2px 3px 3px 4px
}

html>body thead.fixedHeader th {
	width: 200px
}

html>body thead.fixedHeader th+th {
	width: 240px
}

html>body thead.fixedHeader th+th+th {
	width: 316px
}

html>body tbody.scrollContent td {
	width: 200px
}

html>body tbody.scrollContent td+td {
	width: 240px
}

html>body tbody.scrollContent td+td+td {
	width: 300px
}

td {
	text-align: center;
}
</style>
<body>

	<h3>
		<b><p align="center" style="font-family: Verdana; color: white">Test
				Case Coverage for given Requirement is :</p></b>
	</h3>
	<br />


	<div id="tableContainer" class="tableContainer" style="margin: 0 auto;">
		<table id="tblExport" border="0" cellpadding="0" cellspacing="0"
			width="100%" class="scrollTable">
			<thead class="fixedHeader">
				<tr>
					<th>Test Case ID</th>
					<th>Status</th>
					<th>Percentage</th>
				</tr>
			</thead>
			<tbody class="scrollContent">
				<c:forEach items="${resultTestCoverage}" var="item">
					<tr>
						<td class="tableData"><c:out value="${item.testCaseId}" /></td>
						<td class="tableData"><c:out value="${item.testCaseStatus}" /></td>
						<td class="tableData"><c:out value="${item.percentageCoverage}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<br />
	<br />
	<br />
	<center>
		<button id="btnExport">Export to excel</button>
	</center>
	<br />
	<br />
	<center>
		<button id="btnExportPDF" onclick="generatePDFFromTable()">Export
			to PDF</button>
	</center>

</body>
</html>