<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="master.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css"> --%>


<script type="text/javascript">
		function exec() {
			document.getElementById("back").style.display = "none";
			document.getElementById("back").disabled = true;
			document.getElementById("home").style.display = "none";
			document.getElementById("home").disabled = true;
	}
  
</script>

<script type="text/javascript">
function show(elementId) { 
 document.getElementById("id1").style.display="none";
 document.getElementById("id2").style.display="none";
 document.getElementById("id3").style.display="none";
 document.getElementById("id1").disabled=true;
 document.getElementById(elementId).style.display="block";
}

function validateForm1(){

if(document.getElementById("uploadBox1").value == '') {
	  alert("Please select a file!");
	  return false;
	}else if(document.getElementById("requirementText").value == ''){
		alert("Please Enter Requirements!");
		return false;
	}
return true;
}


function checkFileUpload(){
	if(document.getElementById("uploadBox").value == '') {
		  alert("Please select a file!");
		  return false;
		}else{
	return true;
		}
}
</script>
<style type="text/css">

.rightBorder		{ border-right: 2px solid #000; padding-right: 60px; }
.tableLeftPadding		{ padding-left: 30px;}
.myButton {
	
-moz-box-shadow:inset 0px 1px 0px 0px #9acc85;
	
-webkit-box-shadow:inset 0px 1px 0px 0px #9acc85;
	
box-shadow:inset 0px 1px 0px 0px #9acc85;
	
background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #74ad5a), 
color-stop(1, #68a54b));
	
background:-moz-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
	
background:-webkit-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
	
background:-o-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
	
background:-ms-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
	
background:linear-gradient(to bottom, #74ad5a 5%, #68a54b 100%);
	
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#74ad5a', endColorstr='#68a54b',GradientType=0);
	
background-color:#74ad5a;
	
border:1px solid #3b6e22;
	
display:inline-block;
	
cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	
font-size:13px;
	font-weight:bold;
	padding:6px 12px;
	
text-decoration:none;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #68a54b), color-stop(1, #74ad5a));
	
	
background:-moz-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
	background:-webkit-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
	
background:-o-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
	background:-ms-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
	
background:linear-gradient(to bottom, #68a54b 5%, #74ad5a 100%);
	
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#68a54b', endColorstr='#74ad5a',GradientType=0);
	
background-color:#68a54b;
}
	.myButton:active {
	position:relative;
	top:1px;
}

.requirement-doc {
	/* position: fixed; */
	overflow: auto;
    background-color: powderblue;
    
     display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 200px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}
.req-content {
    background-color: #fefefe;
    margin: auto;
    padding:0px;
    border: 1px solid #888;
    width: 80%;
    height:75%;
    top: 10%;
}

.closereq {
    color: blue;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.closereq:hover,
.closereq:focus {
    color: orange;
    text-decoration: none;
    cursor: pointer;
}

a.active{
color:red;

}
.testoptimisation
{
position:relative;
}



</style>
</head>
<body>
<div>
<table cellspacing="0" cellpadding="0" width="60%" align="center" id="navigation">
<tr>
	<td width="40%" class="rightBorder">
		<div class="Predque">
	
			<table cellspacing="5" border="0" cellpadding="0" align="center" >
			
			
				<tr><td><b><a a type="button" onclick="show('id1');"><font face="Arial" size="5" style="color:#FF8C00;cursor:pointer;" id="QP"> Quality Prediction </font></a></b></td></tr>
				
				<tr><td> <form:form method="GET" name="masterForm"
						action="test"> <b><a type="button" onclick="show('id3');"><font face="Arial" size="5" style="color:#FF8C00; cursor:pointer;"> Test Optimization </font></a></b></form:form></td></tr>
				<tr><td><b><a a type="button" onclick="show('id2');"><font face="Arial" size="5" style="color:#FF8C00; cursor:pointer;" id="TC"> Test Coverage </font></a></b></td></tr>
				<tr><td><b><a href="<c:url value="/adaptivePlanning" />" ><font face="Arial" size="5" style="color:#FF8C00; cursor:pointer;"> Adaptive Planning </font></a></b></td></tr>
			</table>
	</div>
	</td>
	
	
	<td>
	<div id="id1">
	    <form:form method="POST" action="metrics">
			<table class="tableLeftPadding" >
					<tr><td align="right"><b><font face="Arial" size="5" style="color:#FF8C00;"> Project: &nbsp; </font></b></td>
						<td><select name="projectBean.redmineProjectId" style="width: 170px;">
								<c:forEach var="data" items="${configBean.projectBeanList}"
									varStatus="status">
									<option value="${data.redmineProjectId}">${data.projectName}</option>
								</c:forEach>

						</select></td>
					</tr>
					<tr><td align="right"><b><font face="Arial" size="5" style="color:#FF8C00;"> Prediction:&nbsp; </font></b></td>
						<td><select name="predictBean.predictionId" id="predictCode" style="width: 170px;">
								<c:forEach var="data" items="${configBean.predictionBeanList}"
									varStatus="status">
									<option value="${data.predictionId}">${data.predictDescription}</option>
								</c:forEach>

						</select></td>
					<tr><td align="right"><b><font face="Arial" size="5" style="color:#FF8C00;"> Trend: &nbsp;</font></b></td>
			
						<td><select name="predictBean.trendParameterBean.parameterId" style="width: 170px;">
								<option value="1">Release Wise</option>	
								<option value="2">Module</option>
								<option value="3">Sprint</option>
								<option value="4">Submodule</option>
								<option value="5">Page</option>
								<option value="6">Project wise</option>
								<option value="7">Customer wise</option>
								<option value="8">Over the period of Time</option>
								<option value="9">Month</option>
								<option value="10">Quarter</option>
								<option value="11">Year</option>
						</select></td>
					</tr>
					<tr/>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="myButton" id="btnhome"
							value="Metrics" /></td>
					</tr>
			</table>
		</form:form>
		</div>
		<div id="id2" style="display:none;">
		<form:form method="POST" action="testcoverage" enctype="multipart/form-data" >
	<table class="tableLeftPadding" >
	<tr><td align="right"><b><font face="Arial" size="5" color=" #FF8C00  "> Project: &nbsp; </font></b></td>
                                         <td><select name="projectName" style="width: 170px;">
                                                       <c:forEach var="data" items="${configBean.projectBeanList}"
                                                              varStatus="status">
                                                              <option value="${data.projectName}">${data.projectName}</option>
                                                       </c:forEach>
                                         </select></td>
                                    </tr>
						              <tr><td align="right"><b><font face="Arial" size="5" color=" #FF8C00  "> Requirement: &nbsp; </font></b><form action="action_page.php"></form></td>
										<td colspan="2"><input type="button" class="myButton" id="requirementbtn" value="Requirement" />
										<tr><td></td> <td><textarea name="requirementText" id="requirementText" rows="5" cols="40"></textarea></td>
										
										<tr><td align="right"><b><font face="Arial" size="5" color=" #FF8C00  "> Test Case:&nbsp; </font></b></td>
                                         
						<td style="padding-top: 20px"><%-- <form onsubmit="return checkFileUpload()"> --%>
<input type="file" name="file" size="25" id="uploadBox1"/><br></td></tr>
<tr><td align="right"><b><font face="Arial" size="5" color=" #FF8C00  ">Algorithm:&nbsp; </td> <td><select name="algorithmName" style="width: 170px;">
                                                      		  <option value="1">Latent Semantic Analysis</option>
                                                              <option value="2">Gensim Word2Vec</option>   
                                         </select></td></tr>

						                <tr><td></td><td><input type="checkbox" name="1" style="font-size: 3;"> Historical Data</input><br><br></td></tr>
										<tr><td></td><td colspan="2" align="center"><input type="submit" class="myButton" id="btnhome" value="Test Coverage" onclick="return validateForm1()"/></td></tr>

</table>                      
	</form:form>
	</div>
	<div id="id3" class="testoptimisation" style="display:none;">
	
	<form:form method="POST" action="testoptimization" enctype="multipart/form-data">
	
	<table class="tableLeftPadding">
	<td width="40%" class="rightBorder">
                        <tr><td align="right"><b><font face="Arial" size="5" color=" #FF8C00  "> Project: &nbsp; </font></b></td>
                                         <td><select name="projectName" style="width: 170px;">
                                                       <c:forEach var="data" items="${configBean.projectBeanList}"
                                                              varStatus="status">
                                                              <option value="${data.projectName}">${data.projectName}</option>
                                                       </c:forEach>    
                                         </select></td>
                                    </tr>
                                    
                                    
                                    <tr><td align="right"><b><font face="Arial" size="5" color=" #FF8C00  "> Test Case:&nbsp; </font></b></td>
                                         
						<td>
<input type="file" name="file" size="25" id="uploadBox"/></font></b></td></tr>
<tr><td align="right"><b><font face="Arial" size="5" color=" #FF8C00  ">Algorithm:&nbsp; </td> <td><select name="algorithmName" style="width: 170px;">
                                                      
                                                              <option value="Word2Vec">Gensim Word2Vec</option>   
                                         </select></td></tr>

						                    <tr>     
						      
								     	<td><td colspan="2"><input type="submit" class="myButton" id="Categoriesbtn" value="Categories" onclick="return checkFileUpload()"/></td>  </tr>      
						              
                                         
						
       </table>               
	</form:form>
	</div>
					
	</td>
	
	</td>
		<td><div id="requirement-document" class="requirement-doc" style="display:none";>
		 <div class="req-content">
										<span id="closereq" class="closereq" style="cursor:pointer;">&times;</span>
										<iframe  src="<%=request.getContextPath()%>/resources/documents/CRESTA_Software Test Acceleration_v_1.3.pdf" style="width:100%; height:100%;" ></iframe>
										
										</div>
										</div>
						                </td>	
</tr>
</table>
</div>
</div>
	<script>
// get requirement doc
var doc= document.getElementById("requirement-document");

//get the button
var reqBtn=document.getElementById("requirementbtn");

//det close button
var closereq=document.getElementById("closereq");

//when user clicks on Requirement
reqBtn.onclick= function(){
	doc.style.display="block";
	
}

//when clicks on close
closereq.onclick=function(){
	doc.style.display="none";
}
window.onclick = function(event) {
    if (event.target == doc) {
        doc.style.display = "none";
    }
}
   

</script>
</body>
</html>
