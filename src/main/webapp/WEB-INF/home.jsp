<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tacos</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>


	<div class="container">
		<h1>Welcome ${thisUser.userName }</h1>
		<div class="flex">
			<h2>Books from evreyone's shelves:</h2>
			<div>
				<div>
					<a href="/logout">logout</a>
				</div>
				<a href="/books/new">+ Add a Book to my shelf!</a>
			</div>
		</div>

		<div class="section">
			<table class="table">
				<thead>
					<tr>
						<th><h1>ID</h1></th>
						<th><h1>Title</h1></th>
						<th><h1>Author Name</h1></th>
						<th><h1>Posted By</h1></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allBooks }" var="book">
						
							<tr>
								<td>${book.id }</td>
								<td><a href="/books/${book.id}">${book.title }</a></td>
								<td>${book.author }</td>
								<td>${book.poster.userName }</td>
							</tr>
						
					</c:forEach>

				</tbody>
			</table>


			<!-- loop over all the books to show the details as in the wireframe! -->

		</div>
	</div>
</body>
</html>

