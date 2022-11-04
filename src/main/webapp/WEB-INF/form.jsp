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
		<div class="flex">
			<h1>Add a Book to Your Shelf!</h1>
			<a href="/home">Back to the shelves</a>
		</div>
		<div class="section">
			<form:form action="/books/create" method="post" modelAttribute="book">
			<form:hidden path="poster" value= "${user_id }"/>
				<!--current logged in user from session  -->

				<p>
					<form:label path="title">Title:</form:label>
					<form:errors path="title" />
					<form:input path="title" />
				</p>
				<p>
					<form:label path="author">Author:</form:label>
					<form:errors path="author" />
					<form:input path="author" />
				</p>
				<p>
					<form:label path="thought">My thoughts: </form:label>
					<form:errors path="thought" />
					<form:textarea path="thought" />
				</p>

				<input type="submit" value="Submit" />
			</form:form>
		</div>
	</div>
</body>
</html>

