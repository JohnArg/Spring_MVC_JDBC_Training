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
	<div id="main_container" class="container-fluid">
		<div class="jumbotron text-center">
			<div class="container">
				<h2 class="jumbotron-heading shadow_text">Welcome To EBook Library</h2>
				<p class="lead shadow_text">A place where you can store and read ebooks online. Browse through the available ebooks! Missing something? Add a book, or delete a book that you don't need anymore.</p>
				<p><a class="nav_link" href="<c:url value='/'/>">Go To Home Page</a></p>	
				<!-- Button trigger modal -->
				<button id="modalOpener" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
						Add Ebook
				</button>
			</div>
		</div>
		<section class="container text-center">
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
				    		<p class="card-text">Written by ${book.author} at ${book.getPublishDateString()}</p>
							<div class="card_btn_container">
								<a href="#" class="btn btn-primary">Read</a>	
								<a href="#" class="btn btn-danger delete_book_btn" id="${book.id}">Delete</a>
							</div>
				  		</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
			<h5 class="modal-title" id="exampleModalLabel">Add Book</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label for="input_title">Title</label>
						<input type="text" class="form-control" id="input_title"/>
					</div>
					<div class="form-group">
						<label for="input_author">Author</label>
						<input type="text" class="form-control" id="input_author"/>
					</div>
					<div class="form-group">
						<label for="input_date">Published</label>
						<input type="text" class="form-control" id="input_date" placeholder="Like day/month/year"/>
					</div>
				</form>
			</div>
			<div class="modal-footer">
			<button id="add_book_btn" type="button" class="btn btn-primary">Submit</button>
			</div>
		</div>
		</div>
	</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>"></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>"></script>
<script src="<c:url value='/scripts/moment.min.js'/>"></script>
<script src="<c:url value='/scripts/library_manager.js'/>"></script>
</html>