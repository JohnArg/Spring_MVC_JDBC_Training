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
	<div class="container text-center">
		<article>
			<h5>The page you requested does not exist</h5>
			<a href="< c:url value='/' />"></a>
		</article>
	</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>"></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>"></script>
</html>