<!DOCTYPE html>
<%@ page import="javax.servlet.RequestDispatcher"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="server.*"%>
<html>
<%
	//ArrayList<Collage> previousCollage = (ArrayList<Collage>) session.getAttribute("PreviousCollageList");
%>
<%
	//Collage mainCollage = (Collage) session.getAttribute("MainCollage");
%>

<head>
<meta charset="UTF-8">
<title>User Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="CollageViewerPage.css">
</head>
<script>
	var firstTime = true;
	document.addEventListener("DOMContentLoaded", function(e) {
		
		document.getElementById("submitButton").addEventListener("click", function(e) {
			e.preventDefault();
			buildCollage();
		});
	});
	function switchCollage(elem) {
		var xhttp = new XMLHttpRequest();
		var switchCollages = "SwitchCollage.jsp?";
		switchCollages += "index=" + elem.id;
		xhttp.open("GET", switchCollages, false);
		xhttp.send();
		console.log(xhttp.responseText);
		document.getElementById("entirePage").innerHTML = xhttp.responseText;
	}
	
	//function to send the topic to the back end and build the collage then send the user to the next page
	function buildCollage()
	{
		console.log("topic: " + document.getElementById("topic").value);
		var xhttp = new XMLHttpRequest();
		
		var borderRadios = document.getElementsByName("border");
		var borderValue = "false";
		for (var i = 0, length = borderRadios.length; i < length; i++)
		{
			if (borderRadios[i].checked)
			{
		  		if (borderRadios[i].value == "borderOn")
			  		borderValue = "true";
		  		break;
		 	}
		}
		
		var rotateRadios = document.getElementsByName("rotations");
		var rotateValue = "false";
		for (var i = 0, length = rotateRadios.length; i < length; i++)
		{
			if (rotateRadios[i].checked)
			{
		  		if (rotateRadios[i].value == "rotateOn")
			  		rotateValue = "true";
		  		break;
		 	}
		}
		
		var url = "MainController?topic="
				+ document.getElementById("topic").value
				+ "&first=" + firstTime + "&letters="
				+ document.getElementById("shape").value
				+ "&borders=" + borderValue + "&filter=None&rotations=" + rotateValue;
		xhttp.open("GET", url, false);
		xhttp.send();
		var data = xhttp.responseText;
		data = JSON.parse(data);
		document.getElementById("mainCollage").src = "data:image/png;base64," + data.image.image;
		document.getElementById("header").innerHTML = "Collage for Topic " + data.image.topic;
		//update collage for...
		//update image
		//update previous collages
		
	}

	//checks if the text input is empty and enables the button if it is not
	function IsEmpty() {

		if (document.getElementById("topic").value == ""
				&& document.getElementById("shape").value == "")
		{
			document.getElementById("submitButton").disabled = true;
		} else {
			document.getElementById("submitButton").disabled = false;
		}
	}
</script>



<body>
	<div id="entirePage">
		<!-- Title at top of the page -->
		<%
			//if (mainCollage != null) {
		%>
		<h1 id="header">
		
		</h1>
		<%
			//}
		%>

		<!-- Div to hold the main collage viewing area -->
		<div class="MainCollageView">
			<!-- Div to hold image that populates the main collage viewer area -->
			<%
				//if (mainCollage != null) {
			%>
			<img onclick="exb()" id="mainCollage"
				src="data:image/png;base64,"
				width="100%" height="100%" />
			<%
				//} else {
			%>
			<div id="emptyImage"></div>
			<%
				//}
			%>
		</div>

		<!-- Div to hold all of the buttons and input fields -->
		<form class="BuildAnotherCollageForm">
			<div id="left">
				<label>
					<input type="text" id="topic" name="topic"
					class="input" placeholder="Enter Topic" oninput="IsEmpty()">
				</label> 
				<label>
					<input type="text" id="shape" name="shape" class="input"
					placeholder="Enter Shape" oninput="IsEmpty()">
					</br>
				</label> 
				<button id="submitButton" class="buttons"value="Build Collage">
				Build Collage
				</button>
			</div>



			<div id="right">
				<button id="saveButton" class="buttons"
					value="Save">Save</button>
				<button id="exportButton" class="buttons" value="Export">Export</button>
				<button id="deleteButton" class="buttons" value="Delete">Delete</button>
				<button id="logoutButton" class="buttons" value="Logout" action="/loginPage.jsp">Logout</button>
				
			</div>
			<div id="middle">
				<label>
					<input type="radio" id="none" name="filter" value="none" checked>
						None 
					<input type="radio" id="blackAndWhite" name="filter" value="blackAndWhite">
						Black & White 
					<input type="radio" id="grayscale" name="filter"
						value="grayscale"> Grayscale <input type="radio" id="sepia"
						name="filter" value="sepia"> Sepia</label> <label>Photo
						Borders 
					<input type="radio" id="borderOn" name="border"
						value="borderOn" checked>
						ON 
					<input type="radio"
						id="borderOff" name="border" value="borderOff"> 
						OFF
					</br>
				</label> 
				<label id="rotations">
					Photo Rotations 
					<input type="radio" id="rotateOn"name="rotations" value="rotateOn" checked>
						ON 
					<input type="radio" id="rotateOff" name="rotations" value="rotateOff">
						OFF
					</br>
				</label>
			</div>
		</form>
		<!-- Div to hold the previos collage picker with divs to hold each image -->
		<div id="container" >
			<%//if(false) { //fix later
				//for(int i =0; i<previousCollage.size(); i++){%>
			
				 <div id="" onclick="switchCollage(this)"><img  src="data:image/png;base64," width="100%" height="100%" alt="Image Text" /></div>
			<%//}
			//}%>
		</div>
	</div>
</body>
</html>