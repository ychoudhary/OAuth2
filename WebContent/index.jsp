<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OAuth Application</title>
</head>
<body>
	<div align="center">
		<h3>OAuth 2.0 Test Application: Using GitHub in OAuth Server Mode</h3>
		<a href="authz?git=true" class="button">Login using Github OAuth 2.0</a>
	</div>
	<br><br>
	<div align="center">
		<h3>OAuth 2.0 Test Application: Using ISAM in OAuth Server Mode</h3>
		<a href="authz?isam=true" class="button">Login using ISAM OAuth 2.0</a>
	</div>
</body>
</html>