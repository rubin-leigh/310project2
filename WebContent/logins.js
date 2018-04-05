/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
	var form = document.getElementById("login-form");
	$(form).submit(function(event) {
		event.preventDefault();
		var form = document.getElementById("login-form");
		var formData = $(form).serialize();
		$.ajax({
            type: 'POST',
            url: "LoginServlet",
            data: formData,
            async: true
		
        }).done(function(data) {
        	data = JSON.parse(data);
        	var loginStatus = data.loginStatus;
        	var completedStatus = data.completedStatus;
        	$("#error1").addClass("hidden");
        	$("#error1").text("Invalid username");
    		$("#error2").addClass("hidden");
        	$("#error2").text("Invalid password");
        	if (loginStatus === 0) {
        		console.log("running this");
        		window.location.href = "CollageViewerPage.jsp";
        	} else if (loginStatus === 1) {
        		$("#error2").removeClass("hidden");
        	} else if (loginStatus === 2){
        		$("#error1").removeClass("hidden");
        	}
        	if (completedStatus === 1) {
        		//show both missing field errors
        		$("#error1").removeClass("hidden");
        		$("#error1").text("Please enter username");
        		$("#error2").removeClass("hidden");
        		$("#error2").text("Please enter password");
        		
        	} else if (completedStatus === 2) {
        		$("#error1").removeClass("hidden");
        		$("#error1").text("Please enter username");
        		//show missing username
        	} else if (completedStatus === 3) {
        		$("#error2").removeClass("hidden");
        		$("#error2").text("Please enter password");
        		//show missing password
        	} 
        });
        	
	});
});