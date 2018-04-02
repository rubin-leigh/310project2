<!DOCTYPE html>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "javax.servlet.http.HttpSession" %>
<%@ page import="server.*" %>
	<html>
	<% ArrayList<Collage> previousCollage= (ArrayList<Collage>) session.getAttribute("PreviousCollageList"); %>
	<% Collage mainCollage= (Collage) session.getAttribute("MainCollage");
	%> 
		<head>
			<meta charset="UTF-8">
			<title>User Page</title>
			<link rel="stylesheet" href="CollageViewerPage.css">
		</head>
		<script>
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
	        		var xhttp = new XMLHttpRequest();
	        		var url = "MainController?topic="+document.getElementById("topic").value+"&first=false&shape="+document.getElementById("shape").value+"&first=false";
	            	xhttp.open("GET", url, false);
	            	xhttp.send();
			}
			//checks if the text input is empty and enables the button if it is not
			function IsEmpty() {
				 if(document.getElementById("topic").value == "" && document.getElementById("shape").value == "")
	             {
					 document.getElementById("submitButton").disabled = true;
	             }else{
	            	 	document.getElementById("submitButton").disabled = false;
	             }

			}
		</script>
		
		<body>
			<div id="entirePage">
				<!-- Div to hold the previos collage picker with divs to hold each image -->
				<div id="container" >
					 <%for(int i =0; i<previousCollage.size(); i++){%>
						 <div id=<%=i %> onclick="switchCollage(this)"><img  src="data:image/png;base64,<%=previousCollage.get(i).getImage()%>" width="100%" height="100%" alt="Image Text" /></div>
					<%}%>
				</div>
				
				<!-- Title at top of the page -->
				<h1>Collage For Topic <%= mainCollage.getTopic() %> </h1>
				<!-- Div to hold the main collage viewing area -->
				
				<div class="MainCollageView">
					<!-- Div to hold image that populates the main collage viewer area -->
					<img onclick="exb()" id="mainCollage" src="data:image/png;base64,<%=mainCollage.getImage()%> " width="100%" height="100%"/>
				</div>
				
				<!-- Div to hold all of the buttons and input fields -->
				<form class="BuildAnotherCollageForm">
						<div id="left">
						<label><input type="text" id="topic" name="topic" class="input" placeholder="Enter Topic" oninput="IsEmpty()"  disable onsubmit="buildCollage()"></label>

						<label><input type="text" id="shape" name="shape" class="input" placeholder="Enter Shape" oninput="IsEmpty()"  disable onsubmit="buildCollage()"></br></label>
						<label><input type="submit" id="submitButton" class="buttons" value="Build Collage"></label>
						</div>

						<div id="right">
						<label><input type="submit" id="saveButton" class="buttons" value="Save"></label>
						<label><input type="submit" id="exportButton" class="buttons" value="Export"></label>
						<label><input type="submit" id="deleteButton" class="buttons" value="Delete"></label>
						<label><input type="submit" id="logoutButton" class="buttons" value="Logout" action="/loginPage.jsp"></label>
						
						</div>

						<div id="middle">
						<label><input type="radio" id="none" name="filter" value="none" checked> None    
						<input type="radio" id="blackAndWhite" name="filter" value="blackAndWhite"> Black & White    
						<input type="radio" id="grayscale" name="filter" value="grayscale"> Grayscale    
  						<input type="radio" id="sepia" name="filter" value="sepia"> Sepia</label>

  						<label>Photo Borders <input type="radio" id="borderOn" name="border" value="borderOn" checked> ON
						<input type="radio" id="borderOff" name="border" value="borderOff"> OFF</br></label>

						<label>Photo Rotations <input type="radio" id="rotateOn" name="rotations" value="rotateOn" checked> ON
						<input type="radio" id="rotateOff" name="rotations" value="rotateOff"> OFF</br></label>
						</div>

						
	
					</form>
				
				
			</div>
		</body>
	</html>
