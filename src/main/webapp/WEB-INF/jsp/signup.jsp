<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Sign Up</title>
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
        </div>
    </nav>

    <!-- Signup Form -->
    <section class="page-section" style="margin-top:100px">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6 form-container">
                    <div class="text-center">
                        <h2 class="section-heading text-uppercase">Sign Up</h2>
                        <h3 class="section-subheading text-muted">Create your account</h3>
                    </div>
                    <!-- General Error Box -->
                    <c:if test="${not empty errorMessage and errorMessage ne ''}">
                        <div class="alert alert-danger alert-dismissible fade" role="alert">
                             <c:out value="${errorMessage}" />
                        </div>
                    </c:if>
                    <form action="/signup" method="post">
                        <div class="form-group mb-3">
                            <input class="form-control" type="email" name="email" placeholder="Email" required />
                            <c:if test="${not empty errors.email}">
                                <span class="text-danger"><c:out value="${errors.email}" /></span>
                            </c:if>
                        </div>
                        <div class="form-group mb-3">
                            <input class="form-control" type="text" name="username" placeholder="Username" required />
                            <c:if test="${not empty errors.username}">
                                <span class="text-danger"><c:out value="${errors.username}" /></span>
                            </c:if>
                        </div>
                        <div class="form-group mb-3">
                            <input class="form-control" type="password" name="password" placeholder="Password" required />
                            <c:if test="${not empty errors.password}">
                                <span class="text-danger"><c:out value="${errors.password}" /></span>
                            </c:if>
                        </div>
                        <div class="text-center">
                            <button class="btn btn-primary btn-xl text-uppercase" type="submit">Sign Up</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p>Already have an account? <a href="/login">Login here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
