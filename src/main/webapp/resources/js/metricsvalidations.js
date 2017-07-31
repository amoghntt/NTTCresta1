function validateForm(){
	var counter = metricLength;

	while(counter!=0)
		{
		counter--;
	 var lcl = +document.getElementById("lclId"+counter+"0").value;
	 var ucl = +document.getElementById("uclId"+counter+"0").value;
	 var weightage = +document.getElementById("weightageId"+counter).value;

 
 if(lcl.length == 0){
	   alert("LCL cannot be blank!");
	   return false;
	 }
 
 else if(lcl < 0){
	   alert("LCL cannot be negative!");
	   return false;
	 }
 else if(!/^[0-9]+$/.test(lcl)){
	   alert("Please enter numeric characters for LCL!");
	   return false;
	 }
 
 else if(ucl.length == 0){
	   alert("UCL cannot be blank!");
	   return false;
	 }

else if(ucl < 0){
	   alert("UCL cannot be negative!");
	   return false;
	 }
else if(!/^[0-9]+$/.test(ucl)){
	   alert("Please enter numeric characters for UCL!");
	   return false;
	 }
 
else if(weightage < 0){
	   alert("Weightage cannot be negative!");
	   return false;
	 }
else if(!/^-?\d+\.?\d*$/.test(weightage)){
	   alert("Please enter numeric characters for weightage!");
	   return false;
	 }
else if(lcl > ucl){
	   alert("LCL should be less than or equal to UCL!");
	   return false;
	 }

		}
	return true;
}
