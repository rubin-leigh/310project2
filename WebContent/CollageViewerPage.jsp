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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js" integrity="sha384-CchuzHs077vGtfhGYl9Qtc7Vx64rXBXdIAZIPbItbNyWIRTdG0oYAqki3Ry13Yzu" crossorigin="anonymous"></script>
<link rel="stylesheet" href="CollageViewerPage.css">
</head>
<script>
	var firstTime = true;
	document.addEventListener("DOMContentLoaded", function(e) {
		
		document.getElementById("submitButton").addEventListener("click", function(e) {
			e.preventDefault();
			buildCollage();
		});
		document.getElementById("saveButton").addEventListener("click", function(e) {
			e.preventDefault();
			saveCollage();
			
		});
		document.getElementById("deleteButton").addEventListener("click", function(e) {
			e.preventDefault();
			deleteCollage();
		});
			
		loadData();
	});
	function deleteCollage() {
		var xhttp = new XMLHttpRequest();
		var switchCollages = "DeleteServlet";
		xhttp.open("GET", switchCollages, false);
		xhttp.send();
		var response = xhttp.responseText;
		response = JSON.parse(response);
		
		update(response)
	}
	function loadData() {
		var xhttp = new XMLHttpRequest();
		var location = "LoadServlet";
		xhttp.open("GET", location, false);
		xhttp.send();
		var response = xhttp.responseText;
		response = JSON.parse(response);
		
		if (!response.isEmpty) {
			document.getElementById("MainCollageView").innerHTML = "<img id='mainCollage' src='' width='100%' height='100%' alt='Image Text' /></div>"
			firstTime = false;
			update(response);
		} else {
			
			firstTime = true;
		}
		
		console.log(response);
	};
	function saveCollage() {
		var xhttp = new XMLHttpRequest();
		var switchCollages = "SaveServlet";
		xhttp.open("GET", switchCollages, false);
		xhttp.send();
		var response = xhttp.responseText;
		console.log(response);
		alert("Saved Collages!");
		//$("#saveSuccess").removeClass("hidden");
		
	}
	function update(data) {
		if (!data.isEmpty) {
			document.getElementById("mainCollage").src = "data:image/png;base64," + data.image.image;
			document.getElementById("pngDownload").href =  "data:image/png;base64," + data.image.image;
			
			
			
			
			
			document.getElementById("pdfDownload").addEventListener("click", function() {
				var doc = new jsPDF();
			    var imgData = 'data:image/png;base64,'+ data.image.image;
			   
			    doc.addImage(imgData, 'PNG', 15, 40, 180, 160);
			    doc.save('test');
			}.bind(this, data));
			
			
			document.getElementById("header").innerHTML = "Collage for Topic " + data.image.topic;
			var previousCollageDiv = document.getElementById("container");
			document.getElementById("container").innerHTML = "";
			
			
			for (var i = 0; i < data.previousCollages.length; i++) {
				var newImageDiv = document.createElement("div");
				newImageDiv.id = i + "div";
				newImageDiv.className = "previousCollage";
				var newImage = document.createElement("img");
				newImage.src = 'data:image/png;base64,' + data.previousCollages[i].image;
				newImage.className = "previousCollageImage";
				newImage.alt = "Image Text";
				newImage.id = i + "image";
				newImage.addEventListener("click", function(x) {
					//update backend
					//use backend response to update front end
					var url = "SwitchCollage?id=" + x;
					var xhttp = new XMLHttpRequest();
					xhttp.open("GET", url, false);
					xhttp.send();
					var data = xhttp.responseText;
					data = JSON.parse(data);
					update(data);
					
				}.bind(this, i) );
				newImageDiv.appendChild(newImage);
				document.getElementById("container").appendChild(newImageDiv);
			}
		
		} else {
			document.getElementById("MainCollageView").innerHTML = "<div id='emptyImage'></div>";
			firstTime = true;
		}
	}

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
		
		var loaderDiv = document.createElement("div");
		loaderDiv.id = "loader";
		loaderDiv.style.visibility = "visible";
		var mainCollageView = document.getElementById("MainCollageView");
		if (firstTime) {
			var emptyImage = document.getElementById("emptyImage");
			mainCollageView.removeChild(emptyImage);
			
		} else {
			var collage = document.getElementById("mainCollage");
			mainCollageView.removeChild(collage);
		}
		mainCollageView.appendChild(loaderDiv);
		//document.getElementById("loader").style.visibility= 'visible';

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
		
		var filterRadios = document.getElementsByName("filter");
		var filter = "none";
		for (var i = 0, length = filterRadios.length; i < length; i++)
		{
			if (filterRadios[i].checked)
			{
			  	filter = filterRadios[i].value;
		  		break;
		 	}
		}
		
		var url = "MainController?topic="
				+ document.getElementById("topic").value
				+ "&first=" + firstTime + "&letters="
				+ document.getElementById("shape").value
				+ "&borders=" + borderValue + "&filter=" + filter + "&rotations=" + rotateValue + "&height=" + document.getElementById("height").value
				+ "&width=" + document.getElementById("width").value;

		xhttp.open("GET", url, true);
		xhttp.onreadystatechange = function() {
			if(xhttp.readyState === 4) {
				var data = xhttp.responseText;
				
				
				data = JSON.parse(data);
				console.log(data);
				if (firstTime) {
					firstTime = false;	
					
				}
				document.getElementById("MainCollageView").innerHTML = "<img id='mainCollage' src='' width='100%' height='100%' alt='Image Text' /></div>"
				update(data);
				var mainCollageView = document.getElementById("MainCollageView");
				
				document.getElementById("mainCollage").src = "data:image/png;base64," + data.image.image;
				document.getElementById("header").innerHTML = "Collage for Topic " + data.image.topic;
			}
			
			
			
		}
		
		xhttp.send();
		var data = xhttp.responseText;
		data = JSON.parse(data);
		
		//console.log(data);
		if (firstTime) {
			firstTime = false;	
			document.getElementById("MainCollageView").innerHTML = "<img id='mainCollage' src='' width='" + document.getElementById("width").value + "px' height='" + document.getElementById("height").value + "px' alt='Image Text' /></div>"
		}
		update(data);

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

	//displays value on the slider of the height
	function changeHeight() {
		var sliderH = document.getElementById("height");
		var currentVal = sliderH.value;
		document.getElementById('demoHeight').innerHTML = currentVal;

		document.getElementById('MainCollageView').setAttribute("height", currentVal);
	}

	//displays value on the slider of the width
	function changeWidth() {
		var sliderW = document.getElementById("width");
		var currentVal = sliderW.value;
		document.getElementById('demoWidth').innerHTML = currentVal;

		document.getElementById('MainCollageView').setAttribute("width", currentVal);
	}
</script>



<body>
	<div id="entirePage">
	<a href="Login.jsp" target="_self" class="logoutLine">Logout</a>
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
		<div id="MainCollageView">
			<!-- Div to hold image that populates the main collage viewer area -->
			
			<div id="emptyImage"><div id="loader"></div></div>
			
		</div>

		<!-- Div to hold all of the buttons and input fields -->
		<form class="BuildAnotherCollageForm">
			<div id="top">
				<label>
					<input type="text" id="topic" name="topic" class="input" placeholder="Enter Topic" oninput="IsEmpty()">
				</label> 
				<label>
					<input type="text" id="shape" name="shape" class="input" placeholder="Enter Shape" oninput="IsEmpty()">
				</label> 
				
				<div id="right">
				<button id="submitButton" class="buttons"value="Build Collage">Build Collage</button>
				<button id="saveButton" class="buttons" value="Save">Save</button>
				<span id="saveSuccess" class="hidden">Save Succeeded!</span>
				<div class="dropdown">
					<button id="exportButton" class="buttons" value="Export">Export</button>
						<div class="dropdown-content">
    							<a id='pngDownload' href="data:image/png;base64," download="test.png">Export as PNG</a>
    							<a id='pdfDownload' href="data:image/pdf;base64," download="test.pdf">Export as PDF</a>
 				 		</div>
				</div>
				
				<button id="deleteButton" class="buttons" value="Delete">Delete</button>
				
				</div>
				
			</div>

			<div class="slidecontainer">
				Height
  				<input type="range" min="200" max="1500" value="600" class="slider" id="height" onchange="changeHeight()" >
 				 <h5>Value: <label id="demoHeight"></label></h5>
			</div>

			<div class="slidecontainer">
				Width
  				<input type="range" min="200" max="1500" value="800" class="slider" id="width" onchange="changeWidth()">
 				<h5> Value: <span id="demoWidth"></span></h5>
			</div>

			<div id="middle">
				<label>
					<b>Photo Filters    </b>
					<input type="radio" id="none" name="filter" value="none" checked>
						None 
					<input type="radio" id="blackAndWhite" name="filter" value="blackAndWhite">
						Black & White 
					<input type="radio" id="grayscale" name="filter"
						value="grayscale"> Grayscale <input type="radio" id="sepia"
						name="filter" value="sepia"> Sepia</label> 
					<label>
					<br>
					<b>Photo Borders    </b>
					<input type="radio" id="borderOn" name="border"
						value="borderOn" checked>
						ON 
					<input type="radio"
						id="borderOff" name="border" value="borderOff"> 
						OFF
					</br>
				</label> 
				<label id="rotations">
					<b>Photo Rotations </b>
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
			

		</div>
	</div>
</body>
</html>
