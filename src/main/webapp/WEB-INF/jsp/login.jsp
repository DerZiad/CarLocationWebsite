<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="/fonts/icomoon/style.css">

<link rel="stylesheet" href="/css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="/css/style.css">

<title>WIE BATOUTA </title>
</head>
<body>


	<div class="d-lg-flex half">
		<div class="bg order-1 order-md-2"
			style="background-image: url('/images/bg_2.jpg');"></div>
		<div class="contents order-2 order-md-1">

			<div class="container">
				<div class="row align-items-center justify-content-center">
					<div class="col-md-7">
						<h3>Login to your <strong>Private space</strong></h3>
						<p class="mb-4">This is the secured portal for the
							Administrator authentication</p>
						<form action="<c:url value="/admin/login"/>" method="post">
							<div class="form-group first">
								<label for="username">Username</label> <input name="username"
									type="text" class="form-control" placeholder="Username"
									id="username">
							</div>
							<div class="form-group last mb-3">
								<label for="password">Password</label> <input name="password"
									type="password" class="form-control" placeholder="Password"
									id="password">
							</div>

							<div class="d-flex mb-5 align-items-center">
								<label class="control control--checkbox mb-0"><span
									class="caption">Remember me</span> <input type="checkbox"
									checked="checked" /> </label> <span class="ml-auto"><a href="#"
									class="forgot-pass">Forgot Password</a></span>
							</div>

							<input type="submit" value="Log In"
								class="btn btn-block btn-primary">

						</form>
						<br>
						<c:if test="${error ne null}">
							<div class="alert alert-danger" role="alert" style="color:red">
								<p>
									<c:out value="${error}" />
								</p>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>


	</div>



	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/main.js"></script>
</body>
</html>