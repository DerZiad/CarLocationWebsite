<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Agency - Start Bootstrap Theme</title>
<link rel="icon" type="image/x-icon" href="<c:url value="/images/fav.ico"/>" />
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<link href="<c:url value="/home/css/styles.css"/>" rel="stylesheet" />
</head>
<body id="page-top">
    <c:if test="${username ne null }">
        <input id="username" type="hidden" name="username" value="${username}" />
    </c:if>
    <%@ include file="navbar.jsp" %>
    <!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-subheading">Welcome to Our Car Rental Service!</div>
			<div class="masthead-heading text-uppercase">Find Your Perfect Ride</div>
			<p class="lead mt-4" style="font-size:1.25rem;">
            This website was created as a university project during a coding day. It demonstrates a simple car rental platform built for educational purposes.
        </p>
			<a class="btn btn-primary btn-xl text-uppercase" href="#portfolio">Rent Now</a>
		</div>
	</header>
	<!-- Portfolio Grid-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Available Cars</h2>
				<h3 class="section-subheading text-muted">Browse our current stock and choose the car that fits your needs.</h3>
			</div>
			<div class="row">
				<c:forEach items="${voitures}" var="car">
					<div class="col-lg-4 col-sm-6 mb-4">
						<!-- Portfolio item 1-->
						<div class="portfolio-item">
							<a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal1">
								<div class="portfolio-hover">
									<div class="portfolio-hover-content">
										<i class="fas fa-plus fa-3x" onclick="setUpVoiture(${car.id})"></i>
									</div>
								</div>
								<img class="img-fluid" src="data:image/jpeg;base64,${car.base64Image}" alt="..." />
							</a>
							<div class="portfolio-caption">
								<div class="portfolio-caption-heading"><c:out value="${car.brand.displayName}"/> - <c:out value="${car.category.displayName}"/> </div>
								<div class="portfolio-caption-subheading text-muted">Rental Price per Day : ${car.price}</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- About-->
	<section class="page-section" id="about">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">About Us</h2>
				<h3 class="section-subheading text-muted">Discover our journey and commitment to quality service.</h3>
			</div>
			<ul class="timeline">
				<li>
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/home/assets/img/about/1.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>2009-2011</h4>
							<h4 class="subheading">Our Humble Beginnings</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">We started with a small fleet and a big vision: to make car rental easy, affordable, and reliable for everyone.</p>
						</div>
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/home/assets/img/about/2.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>March 2011</h4>
							<h4 class="subheading">An Agency is Born</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">Our agency was officially launched, expanding our services and reaching more customers every year.</p>
						</div>
					</div>
				</li>
				<li>
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/home/assets/img/about/3.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>December 2015</h4>
							<h4 class="subheading">Transition to Full Service</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">We expanded our fleet and introduced new services, making us a one-stop solution for all your car rental needs.</p>
						</div>
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/home/assets/img/about/4.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>July 2020</h4>
							<h4 class="subheading">Phase Two Expansion</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">We embraced technology and innovation, offering online booking and seamless customer experiences.</p>
						</div>
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<h4>
							Become<br /> Part of<br /> Our Story!
						</h4>
					</div>
				</li>
			</ul>
		</div>
	</section>
	<!-- Footer-->
	<footer class="footer py-4">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-4 text-lg-start">Copyright &copy; Car Rental Agency 2022</div>
				<div class="col-lg-4 my-3 my-lg-0">
					<a class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-twitter"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-facebook-f"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-linkedin-in"></i></a>
				</div>
				<div class="col-lg-4 text-lg-end">
					<a class="link-dark text-decoration-none me-3" href="#!">Privacy
						Policy</a> <a class="link-dark text-decoration-none" href="#!">Terms
						of Use</a>
				</div>
			</div>
		</div>
	</footer>
	<!-- Portfolio Modals-->
	<!-- Portfolio item 1 modal popup-->
	<div class="portfolio-modal modal fade" id="portfolioModal1"
		tabindex="-1" role="dialog" aria-hidden="true">
		<input id="idVoiture" type="hidden" name="idVoiture" value="" />
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-bs-dismiss="modal">
					<img src="/home/assets/img/close-icon.svg" alt="Close modal" />
				</div>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<div class="modal-body">
								<!-- Project details-->
								<h2 id="marqueModal" class="text-uppercase"></h2>
								<p id="categorieModal" class="item-intro text-muted"></p>
								<img id="imageModal" class="img-fluid d-block mx-auto"
									src="/home/assets/img/portfolio/1.jpg" alt="Car Image" />

								<ul class="list-inline">
									<li><strong>Year:</strong> <span id="annee"></span></li>
								</ul>
								<input type="number" class="form-control" id="delai"
									name="delai" placeholder="Rental Duration (days)" min="1" max="30" value="" />
								<p id="errorRes" style="color:red"></p>
								<p id="confirmation" style="color:green"></p>
								<button id="reserver"
									class="btn btn-primary btn-xl text-uppercase">
									Reserve</button>
								<button class="btn btn-primary btn-xl text-uppercase"
									data-bs-dismiss="modal" type="button">
									<i class="fas fa-times me-1"></i> Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value="/home/js/scripts.js"/>"></script>
	<script src="<c:url value="/home/js/reactiveTemp.js"/>"></script>
	<script src="<c:url value="/assets/js/jquery.js"/>"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
