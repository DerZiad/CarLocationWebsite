<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>Login</title>
    <link href="/client/css/styles.css" rel="stylesheet" />
    <link href="/additional.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
</head>

<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand" href="/"><img src="/client/assets/img/navbar-logo.svg" alt="..." /></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                Menu <i class="fas fa-bars ms-1"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/#about">About</a></li>
                    <li class="nav-item"><a class="nav-link" href="/#contact">Contact</a></li>
                    <c:if test="${username eq null}">
                        <li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="/signup">Sign up</a></li>
                    </c:if>
                    <c:if test="${username ne null}">
                        <li class="nav-item" style="color: white">${username}</li>
                        <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Login Form -->
    <section class="page-section" style="margin-top:120px">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6 form-container">
                    <div class="text-center">
                        <h2 class="section-heading text-uppercase">Login</h2>
                    </div>
                    <div class="text-center">
                        <h3 class="section-subheading text-muted">Welcome back! Please log in</h3>
                    </div>

                    <!-- Error Box -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <c:out value="${error}" />
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>

                    <form action="/login" method="post">
                        <div class="form-group mb-3">
                            <input class="form-control" type="text" name="username" placeholder="Username" required />
                        </div>
                        <div class="form-group mb-3">
                            <input class="form-control" type="password" name="password" placeholder="Password" required />
                        </div>
                        <div class="text-center">
                            <button class="btn btn-primary btn-xl text-uppercase" type="submit">Login</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p>Don't have an account? <a href="/signup">Sign up here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
