<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="/"><img src="/home/assets/img/navbar-logo.svg" alt="..." /></a>
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
                    <li class="nav-item"><a class="nav-link"><c:out value="${username}"/></a></li>
                    <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                </c:if>
                <c:if test="${user.isAdmin()}">
                    <li class="nav-item"><a class="nav-link" href="/shared">Dashboard ></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

