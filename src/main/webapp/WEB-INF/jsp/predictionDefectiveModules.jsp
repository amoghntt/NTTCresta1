<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="master.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta charset="UTF-8">
<title>Line Chart Test</title>

</head>
<body>


	<form:form method="post">
		<table  align="center"
			style="font-family: Verdana; font-weight: bold; color:#FF8C00">
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
			<tr>
				<td colspan="2" align="center" bgcolor="${colourOfModule}">
					<h4>Defect data for ${moduleName}</h4>
				</td>
			</tr>

		</table>
		<br />
		<br />
		<br />
	</form:form>

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
	<link rel="stylesheet" type="text/css"
		href="https://cdnjs.cloudflare.com/ajax/libs/jqPlot/1.0.8/jquery.jqplot.min.css" />
	<div align="center">
		<div id="div_report1" align="center"
			style="height: 500px; width: 1000px"></div>

		<div id="div_report2" align="center"
			style="height: 500px; width: 1000px"></div>

		<div id="div_report3" align="center"
			style="height: 500px; width: 1000px"></div>
	</div>

	<script>
$.jqplot('div_report1', ${datasets},
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
		        
		        
		        label:"${defectDensity}",
		        min:${minYValueDD},
		        max:${maxYValueDD},
		        labelOptions:{
		            fontFamily:'Helvetica',
		            fontSize: '14pt',
		            textColor: '#FF8C00',
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
		        show:true,
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

		                    label : "&nbsp;" + "${defectDensity}",

		                    color: "rgba(0,0,0,1)",

		                    xaxis : 'xaxis',

		                    yaxis : 'yaxis',
		                    
		                    lineWidth:1, 
		                   
		                   markerOptions : {

		                        style : 'line'
		                   }

		                    },
		{

		    label : "&nbsp;" + "Predicted "+"${defectDensity}",

		    color: "${predictionColourDD}",

		    xaxis : 'xaxis',

		    yaxis : 'yaxis',
		    lineWidth:1, 

		    markerOptions : {

		        style : 'line'}

		    }

		        ],
				
			
		});
		
		
		
$.jqplot('div_report2',${datasets1},
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
		        
		        
		        label:"${defectLeakage}",
		        min:${minYValueDL},
		        max:${maxYValueDL},
		        labelOptions:{
		            fontFamily:'Helvetica',
		            textColor: '#FF8C00',
		            fontSize: '14pt'
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
		        show:true,
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

		                    label : "&nbsp;" + "${defectLeakage}",

		                    color: "rgba(0,0,0,1)",

		                    xaxis : 'xaxis',

		                    yaxis : 'yaxis',
		                    
		                    lineWidth:1, 
		                   
		                   markerOptions : {

		                        style : 'line'
		                   }

		                    },
		{

		    label : "&nbsp;" + "Predicted "+"${defectLeakage}",

		    color: "${predictionColourDL}",

		    xaxis : 'xaxis',

		    yaxis : 'yaxis',
		    lineWidth:1, 

		    markerOptions : {

		        style : 'line'}

		    }

		        ],
				
			
		});
		
		
		
		
$.jqplot('div_report3', ${datasets2},
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
		        
		        
		        label:"${defectRejection}",
		        min:${minYValueDR},
		        max:${maxYValueDR},
		        labelOptions:{
		            fontFamily:'Helvetica',
		            textColor: '#FF8C00',
		            fontSize: '14pt'
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
		        show:true,
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

		                    label : "&nbsp;" + "${defectRejection}",

		                    color: "rgba(0,0,0,1)",

		                    xaxis : 'xaxis',

		                    yaxis : 'yaxis',
		                    
		                    lineWidth:1, 
		                   
		                   markerOptions : {

		                        style : 'line'
		                   }

		                    },
		{

		    label : "&nbsp;" + "Predicted "+"${defectRejection}",

		    color: "${predictionColourDR}",

		    xaxis : 'xaxis',

		    yaxis : 'yaxis',
		    lineWidth:1, 

		    markerOptions : {

		        style : 'line'}

		    }

		        ],
				
			
		});
		</script>



</body>
</html>
