<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.InputStream" %>
<%@page import="java.util.Properties" %>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js">
	</script>
	
<style>
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 30px;
    border: 1px solid #888;
    width: 40%;
    top: 15%
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: orange;
    text-decoration: none;
    cursor: pointer;
}

body {
	background:
		url("<%=request.getContextPath()%>/resources/images/background1.png")center center fixed;
	background-size: cover;
	background-repeat: no-repeat;
	overflow-y: visible;
	overflow-x: visible;

}

.nttlogo {
	opacity: 0.5;
	filter: alpha(opacity = 50);
}

table {
	border-collapse: separate;
	border-spacing: 0 1em;
	float: center;
}

input.pw {
	padding-right: 9px;
}

input.sb {
	margin-left: 60px;
	height: 30px;
	width: 60px;
	border-radius: 5px;
	background-color: #EDDA74;
}

.btn-link {
	  background-color: none;
	text-decoration: underline;
	border: none;
	color:#FF0000;
	cursor: pointer;
	 font-family: inherit;
	  font-size: inherit;
	outline: none;
}
a:link    {color:blue; background-color:transparent; text-decoration:none}
a:hover   {color:red; background-color:transparent; text-decoration:none}
a:active  {color:yellow; background-color:transparent; text-decoration:none}

 .navbar-nav > li > a:hover
 { display:block;

} 

.navbar-nav > li li:hover > ul
 { 
    display:block; 
    margin-left: 1px;
     left: 100%;
     top: -1px;
     
} 
.navbar-nav > li li:hover > ul li  > a:hover
 { 
 display:block;
    
     
} 
.navbar-nav > li li:hover > li > a:hover
 { 
 display:block;
    
     
} 
/* span.caret1{
 


/* position:absolute;  */
 
-webkit-flex-wrap:wrap;
    flex-wrap: wrap;
    -webkit-align-content: center;
    
    display:flex;

display:inline-block;

float:right;
/* vertical-align:middle;   */

border-left:  4px dashed;
border-top: 4px solid transparent;
border-bottom:4px solid transparent; 

} */
.wrapper {
	    text-align: bottom;
	}
 footer {
	    position: absolute;
	    bottom: 0;
	    width: 100%;
	    height: 0px;
	    color: white;
	    
	 
}

}
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 500px; /* Location of the box */
    left: 0;
    top: 20;
    width: 200%; /* Full width */
    height: 200%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: lightblue; /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 5px solid #888;
    width: 50%;
    height: 300px;
    clear: both;
}

/* The Close Button */
.close {
    color: blue;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: orange;
    text-decoration: none;
    cursor: pointer;
}

#tableLeft {
	display: inline;
	position: relative;
	width: 50%;
	float: left;
}

#tableRight {
	display: inline;
	position: absolute;
	width: 50%;
	float: right;
}

</style>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>

	<div id="ntt_logo">
		<img src="<%=request.getContextPath()%>/resources/images/NTT_logo.png"
			class="nttlogo" height="40" width="250" />

	</div>
	<%-- <img src="<%=request.getContextPath()%>/resources/images/logo.jpg">
	<br><br><br> --%>
	

	<div>

		<table style="float: left; font-family: Verdana; font-weight: bold;"">
			<tr>
				<td><input type="button" id="back" class="btn-link"
					value="Back" onclick="history.back()" /></td>
				<td><form:form method="POST" name="masterForm"
						action="redirect">
						<input type="submit" class="btn-link" id="home" name="home"
							value="Home" />

					</form:form></td><td><form:form method="POST" name="masterForm" action="aceMaster"><input type="submit" class="btn-link" id="ace" name="ace"
							value="AceMaster" /></form:form></td>


			</tr>
		</table>
		<table style="float: right;">
			<td><form:form method="POST" name="masterForm" action="redirect">
					<input type="submit" class="btn-link" id="logout" name="logout"
						value="Logout" />
				</form:form></td>
		</table>
	</div>

	<div align="center" id="headerCresta">
		<table>
			<thead>
				<tr>
					<th></th>
					<th style="font-family: Verdana; color:#FF8C00">
						<h1 align="center" id="cresta">CRESTA</h1>
					</th>
					<th></th>
				</tr>
				<tr>
					<th></th>
					<th style="color:#FF8C00;font-family: Verdana;">
						<h4>Comprehensive Robotic Engine for Software Test
							Acceleration</h4>
					</th>
					<th></th>
				</tr>
			</thead>

		</table>
	</div>

	<nav class="navbar navbar-default" id="navbarid">
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" >
				<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">About <span class="caret"></span></a>
						<ul class="dropdown-menu">
						 <li><a href="http://americas.nttdata.com/blogs/modernization/2016/november/can-machine-learning-help-you-change-future" target="_blank">Can Machine Learning help you?</a></li>
						<!--  <li><a  href="viewTeam" target="_blank">Cresta Team</a></li> -->
						 </ul>
						 </li>

		             <li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">CRESTA Features <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/resources/documents/CRESTA_Use Cases.pdf" target="_blank">CRESTA Use Cases</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/CRESTA _UseCases_Website_rev02.pdf" target="_blank">CRESTA UseCase for future</a></li>
						</ul></li>
							<%-- <li><a href="<%=request.getContextPath()%>/resources/documents/CRESTA Scope.pdf" target="_blank">CRESTA Scope</a></li> --%>
							<%-- <li><a href="#" target="_blank">CRESTA Scope</a></li> --%>
						
		
			<%-- <li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Customers <span class="caret"></span></a>
						<ul  class="dropdown-menu">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">BOA <span class="caret1"></span></a>
                       
                       <ul  class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/resources/documents/BoA_Cresta_SOW_rev02.pdf" target="_blank">BoA CRESTA SOW</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/Defect Predictive Modeling(POC)_v03.pdf" target="_blank">Defect Predictive Modeling</a></li>
							<li><a href="#" target="_blank">BoA CRESTA SOW</a></li>
							<li><a href="#" target="_blank">Defect Predictive Modeling</a></li>
						</ul>
					</li>
						 <li><a href="#">customer2 <span class="caret1"></span></a></li>
                    </ul>
					 </li>  --%>
					 
					 
					 
					 
			<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Knowledge <span class="caret"></span></a>
						<ul  class="dropdown-menu">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">LSI Algorithm <span class="caret1"></span></a>
                       
                       <ul  class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/resources/documents/ABSTRACT.pdf" target="_blank">Abstract</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/A multi-objective technique to prioritize test cases based on latent sem....pdf" target="_blank">How to prioritize the Test Cases?</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/Reconstructing Requirements Coverage Views from Design and Test using Tr....pdf" target="_blank">Reconstruction of Requirement Coverage</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/Static test case prioritization using topic models.pdf" target="_blank">Static TestCase prioritisation using topic models</a></li>
							</ul>
					</li>
						 <li><a href="<%=request.getContextPath()%>/resources/documents/Jubatus.pdf" target="_blank">Jubatus</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/Jubatus_Summary.pdf" target="_blank">Jubatus Summary</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/JUBATUS-2ndcall.pdf" target="_blank">Jubatus 2nd Call</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/How_AI_Will_Change_Software Testing.pdf" target="_blank">How AI Will Change Software Testing</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/ClicTest Whitepaper_Predictive Analytics in Software Testing.pdf" target="_blank">Predictive Analysis in Software Testing</a></li>
							<li><a href="<%=request.getContextPath()%>/resources/documents/IJCSI-9-5-2-288-296_LiteratureAnalysis.pdf" target="_blank">Literature Analysis</a></li>
                    </ul>
					 </li> 
						<li class="dropdown"><a href="#" id="myBtn" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Settings</a>
						<!-- Trigger/Open The Modal -->


<div id="myModal" class="modal">

  <!-- Modal content -->
  <div style="height: 70%;" class="modal-content">
  <span class="close">&times;</span>
    
    <div id="tableLeft">
    <!-- <h2>Project</h2>
    <h2 style = "color:gray;"> Cresta</h2> -->
    </div>
    
    <div></div>
    
    <!-- <div id="tableRight"> -->
    <div>
    <table border="0" align="center">
    <tr>
     <td><h2> Quality Matrices</h2></td>
    </tr>
    <!-- <input type="checkbox" name="1"> Defect Desity</input><br>
    <input type="checkbox" name="2"> Defect Leakage </input><br>   
    <input type="checkbox" name="3"> Defect Defferal</input><br>  
    <input type="checkbox" name="4"> Defect Rejection</input><br> -->
	</table>
	</div>
	
	<div>
	<table border="0" width="60%" align="center">
	    <tr>
			<th>Project</th>
			<th>CRESTA</th>
		</tr>
		<tr>
			<th></th>
		</tr>
		
	    <tr>
			<th>Quality Prediction</th>
			<td><input type="checkbox" name="1"> Defect Desity</input><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="checkbox" name="2"> Defect Leakage </input><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="checkbox" name="3"> Defect Defferal</input><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="checkbox" name="4"> Defect Rejection</input><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="checkbox" name="4"> Defect Acceptance</input><br></td>
		</tr>
	
	    <tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th></th>
			<th>Redundancy %</th>
			<th>Duplicate %</th>
		</tr>
		<tr>
			<th>Test Optimisation</th>
			<td><input type="text" value="" id="textRedundancyCriteria" name="textRedundancyCriteria"></td>
			<td><input type="text" value="" id="textDuplicateCriteria" name="textDuplicateCriteria"></td>
		</tr>
		<tr>
			<th></th>
			<th>Coverage criteria %</th>
			<th></th>
		</tr>
		<tr>
			<th>Test Coverage</th>
			<td><input type="text" value="" id="textCoverageCriteria"></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th></th>
			<td><input type="button" value="Save" name="btnSave">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Cancel"></td>
			<!-- <td><input type="button" value="Cancel"></td> -->
		</tr>
		</form>
	</table>
	
	</div> 
    
  </div>

</div>

<script>

// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
/* window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";}d
        else{
        	modal.style.display = "none";
        }
        	
    
} */

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "block";
        
    } 
        
}
</script>
	

						
					
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Copyrights <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/resources/documents/NTTData_Legal_Notices.pdf" target="_blank">NTT Data Legal Notices</a></li>
							
						</ul>
						</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
</script>


<!-- <footer> Copyrights@1993, 2016, NTTData and/or its affiliates. All rights reserved</footer> -->
</html>