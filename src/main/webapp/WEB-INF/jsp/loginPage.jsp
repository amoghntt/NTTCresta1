<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/button-style.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js">
	
</script>
<style type="text/css">
#footer {
	color: #0059b3;
	position: fixed;
	bottom: 24%;
	left: 0;
}

#myTable {
	position: absolute;
	bottom: 50%;
	right: 45%;
}

body {
	background: 
		<%-- url("<%=request.getContextPath()%>/resources/images/login_main.png"); --%>
	 url("<%=request.getContextPath()%>/resources/images/Creative-Letterhead-Powerpoint-Slides.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	overflow-y: hidden;
	overflow-x: hidden;
}

#main-footer {
	color: black;
}
tr.spaceUnder > td
{
  padding-bottom: 2em;
}

</style>
<script type="text/javascript">
	function exec() {
		var loginResult = document.getElementById("loginResult").value;
		if(loginResult){
			 alert("Please enter correct Username / Password!");
		}
		
	}
</script>

</head>
<body onload="exec();">

	<div align="center">
		<form:form id="loginForm" method="post" action="login"
			modelAttribute="loginBean">

			<table id="myTable">

				<tbody>
					<tr>
						<th colspan="4" align="center"><h1  style="font-family: Verdana;">CRESTA</h1></th>
					</tr>
					<tr class="spaceUnder">

						<td align="center" ><b><font face="Arial" size="5">Username :
									&nbsp;</td><td > <input type="text" name="userName" />
							</font></b></td>
					</tr>
					
					<tr class="spaceUnder">

						<td align="center" ><b><font face="Arial" size="5">Password :
									&nbsp;</td><td> <input class="pw" type="password" name="password" />
							</font></b></td>
					</tr>
					<tr><td><input type="hidden" value="${loginResult}" id="loginResult"></td></tr>
<tr class="spaceUnder">
						<td align="center" colspan="4"><input class="myButton" type="submit"
							value="Login" /></td>
					</tr>
				</tbody>
			</table>

		</form:form>
	</div>
	<div id="footer">
		<b> CRESTA is a Next Generation QAT tool currently being lead and
			developed by NTT DATA Inc. and NTT DATA India. It is a web-based
			solution for analyzing historical test artifacts such as test cases,
			defects and defect metrics. It integrates with project and defect
			management tools like Redmine, ALM, JIRA and Bugzilla to collect
			project defects data, metrics and other artifacts. It then leverages
			intelligent robotic automation technologies such as machine learning,
			AI and text analytics for predictive defects analysis and test
			optimization. It presents user-friendly results in the form of
			dashboards, graphs, charts and tables </b>
	</div>
</body>
</html>