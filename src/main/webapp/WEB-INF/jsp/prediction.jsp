<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="master.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<title>Line Chart Test</title>
<!-- Start Styles. Move the 'style' tags and everything between them to between the 'head' tags -->
<style type="text/css">
.releaseDetailsTable { background-color:#FFFFE0;border-collapse:collapse;color:#000;font-size:18px;width:1000px }
.releaseDetailsTable th { background-color:#BDB76B;color:white; }
.releaseDetailsTable td, .myOtherTable th { padding:5px;border:0;text-align: left; }
.releaseDetailsTable td { border-bottom:1px dotted #BDB76B; }
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
</head>
<body>

	<table class="tableprediction" align="center" style="font-family: Verdana;font-weight: bold; color:#FF8C00">
		<tr>
			<td>Project</td>
			<td>${configBean.projectBean.projectName}</td>
		</tr>
		<tr>
			<td>Prediction &nbsp;</td>
			<td>${configBean.predictBean.predictDescription}</td>
		</tr>
		<tr>
			<td>Trend</td>
			<td>Release Wise</td>
		</tr>

	</table>
	<br />
	<br />
	<br />
	<form:form method="post">

		<script type="text/javascript"
			src="<c:url value="/resources/js/jquery.min.js"/>"> </script>
			
			<script type="text/javascript"
			src="<c:url value="/resources/js/jquery.jqplot.min.js"/>"> </script>
		<script type="text/javascript"
			src="<c:url value="/resources/js/jqplot.categoryAxisRenderer.min.js"/>"> </script>
		<script type="text/javascript"
			src="<c:url value="/resources/js/jqplot.cursor.min.js"/>"> </script>
		<script type="text/javascript"
			src="<c:url value="/resources/js/jqplot.highlighter.min.js"/>"> </script>
		<script type="text/javascript"
			src="<c:url value="/resources/js/jqplot.canvasAxisTickRenderer.min.js"/>"> </script>
		<script type="text/javascript"
			src="<c:url value="/resources/js/jqplot.canvasAxisLabelRenderer.min.js"/>"> </script>
		<script type="text/javascript"
			src="<c:url value="/resources/js/jqplot.canvasTextRenderer.min.js"/>"> </script>
		<script type="text/javascript"
			src="<c:url value="/resources/js/jqplot.enhancedLegendRenderer.min.js"/>"> </script>

		<script type="text/javascript">
		$(document).ready(function(){
			    $("#myBtn").click(function(){
		    	var tableLegend = $( ".jqplot-table-legend" );
				tableLegend.css('z-index', '0');
		    });
		});
		
		</script>
		<link rel="stylesheet" type="text/css"
			href="https://cdnjs.cloudflare.com/ajax/libs/jqPlot/1.0.8/jquery.jqplot.min.css" />


<div align="center">
		<div id="div_report"
			style="height: 500px; width: 1000px"></div>
			</div>

		<script>
$.jqplot('div_report', ${datasets},
{ 
  
  axes:{
      xaxis:{ 
    	  autoscale:true,
    	  tickOptions:{ 
    	  labelPosition: 'middle', 
    	  textColor: '#FF8C00',
    	  formatString: '%d',
    	  angle: 0
        
      },
      
      tickRenderer:$.jqplot.CanvasAxisTickRenderer,
        label:"${xLabel}", 
        min:${minXValue},
        max:${maxXValue},
      labelOptions:{
        fontFamily:'Helvetica',
        fontSize: '14pt',
        textColor: '#FF8C00'
      },
      labelRenderer: $.jqplot.CanvasAxisLabelRenderer
      }, 
      yaxis:{
    	  autoscale:true,
        tickOptions:{
            labelPosition: 'middle', 
            textColor: '#FF8C00',
            formatString: '%d',
            angle: 0
        },
        
        
        label:"${yLabel}",
        min:${minYValue},
        max:${maxYValue},
        labelOptions:{
            fontFamily:'Helvetica',
            fontSize: '14pt',
            textColor: '#FF8C00'
        },
       
        tickRenderer:$.jqplot.CanvasAxisTickRenderer,
        labelRenderer: $.jqplot.CanvasAxisLabelRenderer
      }
    },

  
  highlighter: {
      show: true
    },
    cursor: {
        show: true,
        showTooltip :false,
        zoom: true
    },

    grid: {
        drawGridLines: true,       
            gridLineColor: '#cccccc',   
            background: '#ffffff',     
            borderColor: '#ffffff',     
            borderWidth: 0.0,          
            
    },
 
  legend:{ 
		renderer: $.jqplot.EnhancedLegendRenderer,
        show: true,
        marginBottom:'50px',
        location: 'n', 
        showSwatches: true,
      
        
        placement: 'outsideGrid',
        	 rendererOptions: {
                 numberRows: 1
             }      
  },  

		 series : [ 
		          
       
            {

                label : "&nbsp;" + "LCL",
                

                color: "rgba(0,0,255,1)",

                xaxis : 'xaxis',

                yaxis : 'yaxis',
                lineWidth:1, 

               markerOptions : {

                    style : 'line'}

                },
                {

                    label :  "&nbsp;" + "UCL",

                    color: "rgba(202,9,223,1)",

                    xaxis : 'xaxis',

                    yaxis : 'yaxis',
                    lineWidth:1, 

                   markerOptions : {

                        style : 'line'}

                },
                {

                    label : "&nbsp;" + "${yLabel}",

                    color: "rgba(0,0,0,1)",

                    xaxis : 'xaxis',

                    yaxis : 'yaxis',
                    
                    lineWidth:1, 
                   
                   markerOptions : {

                        style : 'line'
                   }

                    },
{

    label : "&nbsp;" + "Predicted "+"${yLabel}",

    color: "${predictionColour}",

    xaxis : 'xaxis',

    yaxis : 'yaxis',
    lineWidth:1, 

    markerOptions : {

        style : 'line'}

    }

        ],
		
	
});
</script>

		<br />
		
		
		<div align="center">
	
			<table class="releaseDetailsTable">
			<tr style="border: 1px solid black">
						<th>Release</th>
						<th>Actual</th>
						<th>Predicted</th>
						<th>% Accuracy</th>
					</tr> 
					<c:forEach var="data" items="${releaseDetails}">

						<tr>
							<td>${data.getRelease()}</td>
							<td>${data.getActualValue()}</td>
							<td>${data.getPredictedValue()}</td>
							<td>${data.getAccuracy()}</td>
						</tr>
					</c:forEach>
<br/>
<br/>
</tr>
</table>
</br>
</br>
</br>
</br>
		</div>
		
		
		
		
		
		
	</form:form>

</body>
</html>
