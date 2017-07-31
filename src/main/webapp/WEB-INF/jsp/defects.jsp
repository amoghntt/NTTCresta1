<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prediction</title>
</head>
<Style>
.module{
 background-color: DarkRed;
    color: white;
    padding: 8px;
    font-size: 16px;
    border: none;
	text-align:center;
    cursor: pointer;
	width:300px;
	hight:25px;
	border-radius: 5px;}
	
.button{
width: 300px;
font-size: 16px;}

.density{
border-radius: 5px;}

.button{
border-radius: 5px;}

p{
color:Blue;}


</style>
<body>
<img src="<%=request.getContextPath()%>/resources/images/logo.jpg">
<br></br>
<br></br>
<h1 style="margin-left: 80px;">Which modules may have issues post release?</h1>
<br><br><br><br>
<div class="density" align="center" id="graph">
<select class="module">
<option value="module1">Module 1</option><br><br>
<option value="module1">Module 2</option><br><br>
<option value="module1">Module 3</option><br><br>
</select>
<br></br>
<br><br>
<select class="module">
<option value="module1">Module 1</option><br><br>
<option value="module1">Module 2</option><br><br>
<option value="module1">Module 3</option><br><br>
</select>
<br><br>
<br><br>
<form:form method="GET" action="fetchRawData">
	<input type="submit" class="button" name="button1" value="Module Wise Metrics"/>
</form:form>
</div>
</body>
</html>