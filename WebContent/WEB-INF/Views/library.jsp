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
			<p class="lead">Browse through the available books! Missing something? Go to the homepage and add the book, or delete a book that you don't need anymore.</p>
			<a href="<c:url value='/'/>">Go To Home Page</a>	
			<p class="margin_top">Your Library</p>
			<nav aria-label="Page Navigation">
			  	<ul id="pagination_list" class="pagination">
			  		<li class="page-item disabled"><a id="page_btn_prev" class="page-link" href="#">Previous</a></li>
			  		<c:forEach begin="1" end="${pages}" varStatus="loop">
			  			<!-- Choose if this is the selected page or not -->
			  			<li 
			  				<c:choose>
			  					<c:when test="${selected_page == loop.index}">
			  						class="page-item active"
			  					</c:when>
			  					<c:otherwise>
			  						class="page-item"
			  					</c:otherwise>
			  				</c:choose>
			  			><a class="page-link page_btn" href="#">${loop.index}</a></li>
			  		</c:forEach>
					<li class="page-item"><a id="page_btn_next" class="page-link" href="#">Next</a></li>
				</ul>
			</nav>
			<div id="library_page" class="container library-self">
				<c:forEach var="book" items="${book_list}">
					<div class="card">
						<div class="card-body">
				    		<h5 class="card-title">${book.title}</h5>
				    		<p class="card-text">Written by ${book.author} at ${book.getPublishDate()}</p>
				   	 		<a href="#" class="btn btn-danger">Delete</a>
				  		</div>
					</div>
				</c:forEach>
			</div>
		</article>
	</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>"></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>"></script>
<script src="<c:url value='/scripts/library_manager.js'/>"></script>
</html>