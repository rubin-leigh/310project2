/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
	
	var form = document.getElementById("signup-form");
	$(form).submit(function(event) {
		event.preventDefault();
		var form = document.getElementById("signup-form");
		var formData = $(form).serialize();
		$.ajax({
            type: 'POST',
            url: "SignUpServlet",
            data: formData,
            async: true
		
        }).done(function(data) {
        	data = JSON.parse(data);
        	var signUpStatus = data.signUpStatus;
        	var completedStatus = data.completedStatus;
        	$("#error1").addClass("hidden");
        	$("#error1").text("Invalid username");
    		$("#error2").addClass("hidden");
        	$("#error2").text("Invalid password");
        	if (signUpStatus === "successful") {
        		console.log("running this");
        		window.location.href = "CollageViewerPage.jsp";
        	} else if (signUpStatus === "bothIncomplete") {
        		$("#error2").removeClass("hidden");
        	} else if (signUpStatus === "incompleteUsername"){
        		$("#error1").removeClass("hidden");
        	}
        	if (completedStatus === "bothIncomplete") {
        		//show both missing field errors
        		$("#error1").removeClass("hidden");
        		$("#error1").text("Please enter username");
        		$("#error2").removeClass("hidden");
        		$("#error2").text("Please enter password");
        		
        	} else if (completedStatus === "incompleteUsername") {
        		$("#error1").removeClass("hidden");
        		$("#error1").text("Please enter username");
        		//show missing username
        	} else if (completedStatus === "incompletePassword") {
        		$("#error2").removeClass("hidden");
        		$("#error2").text("Please enter password");
        		//show missing password
        	} 
        });
        	
	});
});