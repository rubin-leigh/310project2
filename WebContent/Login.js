/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("loginButton").addEventListener("click", function() {
		var form = document.getElementById("login-form");
		var formData = $(form).serialize();
		$.ajax({
            type: 'POST',
            url: "LoginServlet",
            data: formData,
            async: true
		
        }).done(function(data) {
        	
	})
});