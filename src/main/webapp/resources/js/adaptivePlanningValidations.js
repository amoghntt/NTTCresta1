function validateForm1() {
	var releaseId = document.getElementById("releaseId").value;
	var defectDensity = document.getElementById("defectDensity").value;
	var defectLeakage = document.getElementById("defectLeakage").value;
	var defectRejection = document.getElementById("defectRejection").value;
	var testCaseCount = document.getElementById("testCaseCount").value;
	var domainKnowledge = document.getElementById("domainKnowledge").value;
	var technicalSkills = document.getElementById("technicalSkills").value;
	var codeReviewComments = document.getElementById("codeReviewComments").value;
	var numberOfResources = document.getElementById("numberOfResources").value;
	var applicationComplexity = document.getElementById("applicationComplexity").value;
	var requirementQueryCount = document.getElementById("requirementQueryCount").value;
	if (releaseId == null || releaseId == "") {

		alert("Please select Release!");
		return false;
	}
	if(numberOfResources<=0){
		alert("Number of Resources should be greater than 0!");
		return false;
	}
	else if(numberOfResources == "" || numberOfResources==null ||numberOfResources = Nan){
		alert("Please Enter Number of Resources");
		return false;
	}

	return true;
}
