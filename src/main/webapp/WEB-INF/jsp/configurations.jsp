<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>

<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"> </script>

<script type="text/javascript">
function openConfigPage(evt, ConfigPageName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(ConfigPageName).style.display = "block";
    evt.currentTarget.className += " active";
}


function calculate(counter) {
 $.ajax({
  type: "POST",
  url: "calulateLimit", 
  data:'weightagesId=' + counter,
  dataType: "json",
  success: function(data){
	$("#uclId"+counter).val(data.ucl);
	$("#lclId"+counter).val(data.lcl);
  },
  error: function(){      
   	alert('Error while request..');
  }
 });
	
}
</script>
<style>
#menu{
width:65%;
}
div{
width : 63%;
}
.ConfigPage {display:none;}

#predict{
height:30px;
 width:350px;
 color:blue;
 background-color:#DEF5FF ;
 }
 body {font-family: "Lato", sans-serif;}

ul.tab {
    list-style-type: none;
    margin: 0;
    overflow: hidden;
    border: 2px solid black;
    background-color: #f1f1f1;
}

/* Float the list items side by side */
ul.tab li {float: left;}

/* Style the links inside the list items */
ul.tab li a {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 14px 38px;
    text-decoration: none;
    transition: 0.3s;
    font-size: 17px;
	
}

/* Change background color of links on hover */
ul.tab li a:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
ul.tab li a:focus, .active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}

table {
    border-collapse: separate;
    border-spacing: 0 1em;
	padding-left:50px;
}
#defects{
border: 1px solid black;
width:63%;
}
select:focus::-ms-value {
  background: transparent;
  color: inherit;
  outline-width: thin;
}
input.button{
height:25px;
width:80px;
border-radius:5px;
color:black;
background-color:#FAAFBE ;
}
.textb{
height:25px;
width:80px;
text-align:center;
}
.button1{
background-color:#EDDA74;
height:25px;
width:80px;
border-radius:5px;
align:center;
}
#predict:hover{
background-color:#E5E4E2 ;
}

</style>

</head>

<body>
<img src="<%=request.getContextPath()%>/resources/images/logo.jpg">
<br><br><br><br>


<form method="POST" action="refresh" >
<input  class="button1" type="submit" value="Refresh" />	
	</form>						
</script>
</body>
</html>
