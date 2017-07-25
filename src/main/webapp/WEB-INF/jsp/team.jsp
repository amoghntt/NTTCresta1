<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type='text/css'>
   
   img {width:60%;height:auto;margin:-3% 100%;border:2px solid black;} 
    
.image { transition: all .2s ease-in-out;}
/* .image:hover { transform: scale(1.4);transition: all .2s ease-in-out;} */


table{
table-layout: fixed;
width:70%;

}
td{
margin:10% 10%;
 
}


body{
 background-image:url("<%=request.getContextPath()%>/resources/Members_images/Creative-Stairs-Wallpapers-HD.jpg");
background-position:center center fixed; 
 
/* transition: all 0.8s ease; */
/* background-repeat: no-repeat; */
	
  }
  
body:hover {
 background-image:url("<%=request.getContextPath()%>/resources/Members_images/texture-background-creative-bubbles-wallpaper.jpg");
background-position:center center fixed; 
 color:red;
 
}
#tagg1,#tagg2,#tagg3,#tagg4,#tagg5,#tagg6,#tagg7,#tagg8,#tagg9,#tagg10,#tagg11,#tagg12,#tagg13,#tagg14
{
display:inline-block;   
visibility:hidden; 
position:relative;
padding:0% 100%;
color:black;
font-size:19px;
font-family:calibri;


}



 </style>
 <script type="text/javascript"> 
  function display1(){
	document.getElementById("tagg1").style.visibility="visible";
	
	document.getElementById("tagg2").style.visibility="hidden"; 
	document.getElementById("tagg3").style.visibility="hidden";
	document.getElementById("tagg4").style.visibility="hidden"; 
	document.getElementById("tagg5").style.visibility="hidden";
	document.getElementById("tagg6").style.visibility="hidden"; 
	document.getElementById("tagg7").style.visibility="hidden";
	document.getElementById("tagg8").style.visibility="hidden";
	document.getElementById("tagg9").style.visibility="hidden"; 
	document.getElementById("tagg10").style.visibility="hidden"; 
	document.getElementById("tagg11").style.visibility="hidden"; 
	document.getElementById("tagg12").style.visibility="hidden"; 
	document.getElementById("tagg13").style.visibility="hidden"; 
	document.getElementById("tagg14").style.visibility="hidden"; 
  }
  function display2(){
		document.getElementById("tagg2").style.visibility="visible";

		document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display3(){
		document.getElementById("tagg3").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";

		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display4(){
	    document.getElementById("tagg4").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden";
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display5(){
	   document.getElementById("tagg5").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
	
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		document.getElementById("tagg8").style.visibility="hidden";
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display6(){
	    document.getElementById("tagg6").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";

		document.getElementById("tagg7").style.visibility="hidden";
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden";
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display7(){
	    document.getElementById("tagg7").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
	
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden";
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display8(){
	    document.getElementById("tagg8").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden";
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display9(){
	    document.getElementById("tagg9").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display10(){
	    document.getElementById("tagg10").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
	
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display11(){
	    document.getElementById("tagg11").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display12(){
	    document.getElementById("tagg12").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";

		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden";
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display13(){
	    document.getElementById("tagg13").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
		document.getElementById("tagg14").style.visibility="hidden"; 
	  }
  function display14(){
	    document.getElementById("tagg14").style.visibility="visible";
	    document.getElementById("tagg1").style.visibility="hidden";
		document.getElementById("tagg2").style.visibility="hidden";
		document.getElementById("tagg3").style.visibility="hidden";
		document.getElementById("tagg4").style.visibility="hidden"; 
		document.getElementById("tagg5").style.visibility="hidden";
		document.getElementById("tagg6").style.visibility="hidden"; 
		document.getElementById("tagg7").style.visibility="hidden";
		
		document.getElementById("tagg9").style.visibility="hidden"; 
		document.getElementById("tagg8").style.visibility="hidden"; 
		document.getElementById("tagg11").style.visibility="hidden"; 
		document.getElementById("tagg10").style.visibility="hidden"; 
		document.getElementById("tagg13").style.visibility="hidden"; 
		document.getElementById("tagg12").style.visibility="hidden"; 
	  }
  
  </script>
</head>
<body>
<h2>Our Team</h2>
<table border-spacing: none;" cellspacing="0" cellpadding="0">

  <tr id="row1">
  <td></td>
  
  
    <td style="padding-bottom:10%;margin-left:9%;"><img   class="image" onclick="display()"  src="<%=request.getContextPath()%>/resources/Members_images/team.jpg"/></td>
    
    <td style="margin-left:30%;"><img class="image" onclick="display1()" src="<%=request.getContextPath()%>/resources/Members_images/Nikhil_joshi.jpg"/>
    </br>
    <pre  id="tagg1">
 Nikhil Joshi
 VICE PRESIDENT
 Project Head 
 Mentor and Guide
</pre> 
    
    </td>
    
    <td><img  class="image" onclick="display2()" src="<%=request.getContextPath()%>/resources/Members_images/Ketan_patil.jpg"/>
    </br>
    <pre id="tagg2">
Ketan Patil
PROJ PROG MGMT.SR CNSLT
Program Manager

</pre> 
    
    
    
    </td>
  </tr>
 <tr>
    <td  style="padding-top:7%;"><img  class="image" onclick="display3()" src="<%=request.getContextPath()%>/resources/Members_images/Amod.jpg"/>
    </br>
    <pre id="tagg3" >
Amod Bhalerao
QUAL AND PERF DIRECTOR
Technical Manager


</pre> 
    </td>
    <td style="padding-top:3%;"><img   class="image" onclick="display4()" src="<%=request.getContextPath()%>/resources/Members_images/Amarnadh_Raavi.jpg"/>
    </br>
    <pre id="tagg4"  >
Amarnadh Raavi
DELIVERY DIRECTOR
Customer Voice
</pre>
    
    </td>
    <td style="padding-top:3%;"><img   class="image" onclick="display5()"  src="<%=request.getContextPath()%>/resources/Members_images/Feroz_Khan1.jpg"/>
    </br>
    <pre  id="tagg5">Feroz Khan                       
TECH.SOLUTIONS ARCH.SR MNGNG.CNSLT
Jubatus and Code
</pre >
    </td>
    <td style="padding-top:3%;"><img    class="image" onclick="display6()" src="<%=request.getContextPath()%>/resources/Members_images/sameer_bagade1.jpg"/>
    </br>
    <pre  id="tagg6" >
Sameer Bagade
APP TESTING AND QA SR CNSLT
Product Definition
</pre> 
    
    
    </td>
<td style="padding-top:3%;"> <img   class="image" onclick="display12()" src="<%=request.getContextPath()%>/resources/Members_images/Seshadri_Subramaniam.jpg"/>
    </br>
    <pre id="tagg12" >
Subramaniam Seshadri 
APP. SOFTWARE DEV. SR ASSOC. CNSLT
Coding
</pre> 
    
    
    </td>
    <td ><img    class="image" onclick="display8()" src="<%=request.getContextPath()%>/resources/Members_images/Saurabh_Soni.jpg"/>
    </br>
    
    <pre  id="tagg8"  >
Saurabh Soni
APP TESTING AND QA CNSLT
Jubatus and Development

</pre >
    
    </td>
  </tr>
  
  <tr>
    <td style="padding-top:3%;"><img   class="image" onclick="display9()" src="<%=request.getContextPath()%>/resources/Members_images/Bharathish_diggavi.jpg"/>
    </br>
    <pre id="tagg9">
Bharathish Diggavi
APP. SOFTWARE DEV. SR ASSOC. CNSLT
UI development
</pre>
    
    
    </td>
    <td style="padding-top:3%;"><img     class="image" onclick="display10()" src="<%=request.getContextPath()%>/resources/Members_images/rsz_nehapandey.jpg"/>
    </br>
    <pre id="tagg10" >
Neha Pandey
APP. SOFTWARE DEV. SR ASSOC. CNSLT
Coding
</pre> 
    </td>
    <td style="padding-top:4%;"><img  class="image" onclick="display11()"  src="<%=request.getContextPath()%>/resources/Members_images/saipriyadarshini_vagvala1.jpg"/>

    <pre id="tagg11" >Saipriyadarshini Vagvala
APP. SOFTWARE DEV. SR ASSOC. CNSLT                        
UI development

</pre>  

   </td>
       <td style="padding-top:3%;"><img   class="image" onclick="display7()" src="<%=request.getContextPath()%>/resources/Members_images/Swati_Nagargoje.jpg"/>
    </br>
    <pre  id="tagg7" >
Swati Nagargoje
APP. SOFTWARE DEV. SR CNSLT
ETL and Database
</pre> 
    
    
    
    </td>
   
    <td style="padding-top:3%;"><img   class="image" onclick="display13()" src="<%=request.getContextPath()%>/resources/Members_images/shruti_kulkarni.png"/>
    </br>
    
    <pre id="tagg13">
Shruti Kulkarni
APP. SOFTWARE DEV. SR ASSOC. CNSLT
UI development

</pre> 
    
    </td>
    <td style="padding-top:3%;"><img   class="image" onclick="display14()" src="<%=request.getContextPath()%>/resources/Members_images/vidhyashree_shettar2.jpg"/>
    </br>
    <pre id="tagg14">
Vidhyashree Shettar
APP. SOFTWARE DEV. SR ASSOC. CNSLT
UI development
</pre> 
    </td>
  </tr>

</table>

</body>
</html>