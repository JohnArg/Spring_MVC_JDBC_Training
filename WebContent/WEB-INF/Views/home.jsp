<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Ebook Library</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/bootstrap.min.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/default.css' />">
</head>
<body>
	<%@ include file="header.html" %>
	<div id="main_container" class="container-fluid text-center">
		<div class="jumbotron text-center">
			<div class="container">
				<h2 class="jumbotron-heading shadow_text">Welcome To EBook Library</h2>
				<p class="lead shadow_text">A place where you can store and read ebooks online. Here you can store and read your ebooks online!</p>
				<p id="timer"></p>
				<a class="nav_link" href="<c:url value='library'/>">See all our books</a>
			</div>
		</div>
	</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>"></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>"></script>
<script src="<c:url value='/scripts/home_manager.js'/>"></script>
</html>