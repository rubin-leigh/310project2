<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="Login.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script type="text/javascript" src="logins.js"></script>
		<title>Login Page</title>
	</head>
	
	<body>
		<div class="header">
			<h1>Login</h1>
		</div>
		
		<div class="mainBlock">
		</br>
			<form id="login-form" method="post">
				</br>
				<label id="error1" class="hidden error">Invalid Username</label>
				</br>
				<input type="text" id="uName" name="username" class="uName" placeholder="Username"></br></br>
				<label id="error2" class="hidden error">Invalid Password</label>
				</br>
				<input type="text" id="pWord" name="password" class="pWord" placeholder="Password"></br></br>
				<input type="submit" id="loginButton" class="loginButtonClass" value="Login">
			</form>
		</div>
	
	</body>
</html>