<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
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
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/client/css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<c:if test="${username ne null }">
		<input id="username" type="hidden" name="username" value="${username}" />
	</c:if>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top"><img
				src="/client/assets/img/navbar-logo.svg" alt="..." /></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars ms-1"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link" href="#portfolio">Voitures</a></li>
					<li class="nav-item"><a class="nav-link" href="#about">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#contact">Contact</a></li>
					<c:if test="${username eq null}">
						<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
						<li class="nav-item"><a class="nav-link" href="/signup">Sign
								up</a></li>
					</c:if>
					<c:if test="${username ne null}">
						<li class="nav-item" style="color: white">${username}</li>
						<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-subheading">Welcome To Our Studio!</div>
			<div class="masthead-heading text-uppercase">It's Nice To Meet
				You</div>
			<a class="btn btn-primary btn-xl text-uppercase" href="#services">Tell
				Me More</a>
		</div>
	</header>
	<!-- Portfolio Grid-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Voitures</h2>
				<h3 class="section-subheading text-muted">Nos voitures que nous
					avons dans le stocke</h3>
			</div>
			<div class="row">
				<c:forEach items="${voitures}" var="voiture">
					<div class="col-lg-4 col-sm-6 mb-4">
						<!-- Portfolio item 1-->
						<div class="portfolio-item">
							<a class="portfolio-link" data-bs-toggle="modal"
								href="#portfolioModal1">
								<div class="portfolio-hover">
									<div class="portfolio-hover-content">
										<i class="fas fa-plus fa-3x"
											onclick="setUpVoiture(${voiture.id})"></i>
									</div>
								</div> <img class="img-fluid"
								src="data:image/jpeg;base64,${voiture.base64}" alt="..." />
							</a>
							<div class="portfolio-caption">
								<div class="portfolio-caption-heading">Threads</div>
								<div class="portfolio-caption-subheading text-muted">Illustration</div>
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
				<h2 class="section-heading text-uppercase">About</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
			<ul class="timeline">
				<li>
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/client/assets/img/about/1.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>2009-2011</h4>
							<h4 class="subheading">Our Humble Beginnings</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
								adipisicing elit. Sunt ut voluptatum eius sapiente, totam
								reiciendis temporibus qui quibusdam, recusandae sit vero unde,
								sed, incidunt et ea quo dolore laudantium consectetur!</p>
						</div>
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/client/assets/img/about/2.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>March 2011</h4>
							<h4 class="subheading">An Agency is Born</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
								adipisicing elit. Sunt ut voluptatum eius sapiente, totam
								reiciendis temporibus qui quibusdam, recusandae sit vero unde,
								sed, incidunt et ea quo dolore laudantium consectetur!</p>
						</div>
					</div>
				</li>
				<li>
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/client/assets/img/about/3.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>December 2015</h4>
							<h4 class="subheading">Transition to Full Service</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
								adipisicing elit. Sunt ut voluptatum eius sapiente, totam
								reiciendis temporibus qui quibusdam, recusandae sit vero unde,
								sed, incidunt et ea quo dolore laudantium consectetur!</p>
						</div>
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<img class="rounded-circle img-fluid"
							src="/client/assets/img/about/4.jpg" alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>July 2020</h4>
							<h4 class="subheading">Phase Two Expansion</h4>
						</div>
						<div class="timeline-body">
							<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
								adipisicing elit. Sunt ut voluptatum eius sapiente, totam
								reiciendis temporibus qui quibusdam, recusandae sit vero unde,
								sed, incidunt et ea quo dolore laudantium consectetur!</p>
						</div>
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<h4>
							Be Part <br /> Of Our <br /> Story!
						</h4>
					</div>
				</li>
			</ul>
		</div>
	</section>
	<!-- Clients-->
	<div class="py-5">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-3 col-sm-6 my-3">
					<a href="#!"><img class="img-fluid img-brand d-block mx-auto"
						src="/client/assets/img/logos/microsoft.svg" alt="..." /></a>
				</div>
				<div class="col-md-3 col-sm-6 my-3">
					<a href="#!"><img class="img-fluid img-brand d-block mx-auto"
						src="/client/assets/img/logos/google.svg" alt="..." /></a>
				</div>
				<div class="col-md-3 col-sm-6 my-3">
					<a href="#!"><img class="img-fluid img-brand d-block mx-auto"
						src="/client/assets/img/logos/facebook.svg" alt="..." /></a>
				</div>
				<div class="col-md-3 col-sm-6 my-3">
					<a href="#!"><img class="img-fluid img-brand d-block mx-auto"
						src="/client/assets/img/logos/ibm.svg" alt="..." /></a>
				</div>
			</div>
		</div>
	</div>
	<!-- Contact-->
	<section class="page-section" id="contact">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Contact Us</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
			<!-- * * * * * * * * * * * * * * *-->
			<!-- * * SB Forms Contact Form * *-->
			<!-- * * * * * * * * * * * * * * *-->
			<!-- This form is pre-integrated with SB Forms.-->
			<!-- To make this form functional, sign up at-->
			<!-- https://startbootstrap.com/solution/contact-forms-->
			<!-- to get an API token!-->
			<form id="contactForm" data-sb-form-api-token="API_TOKEN">
				<div class="row align-items-stretch mb-5">
					<div class="col-md-6">
						<div class="form-group">
							<!-- Name input-->
							<input class="form-control" id="name" type="text"
								placeholder="Your Name *" data-sb-validations="required" />
							<div class="invalid-feedback" data-sb-feedback="name:required">A
								name is required.</div>
						</div>
						<div class="form-group">
							<!-- Email address input-->
							<input class="form-control" id="email" type="email"
								placeholder="Your Email *" data-sb-validations="required,email" />
							<div class="invalid-feedback" data-sb-feedback="email:required">An
								email is required.</div>
							<div class="invalid-feedback" data-sb-feedback="email:email">Email
								is not valid.</div>
						</div>
						<div class="form-group mb-md-0">
							<!-- Phone number input-->
							<input class="form-control" id="phone" type="tel"
								placeholder="Your Phone *" data-sb-validations="required" />
							<div class="invalid-feedback" data-sb-feedback="phone:required">A
								phone number is required.</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group form-group-textarea mb-md-0">
							<!-- Message input-->
							<textarea class="form-control" id="message"
								placeholder="Your Message *" data-sb-validations="required"></textarea>
							<div class="invalid-feedback" data-sb-feedback="message:required">A
								message is required.</div>
						</div>
					</div>
				</div>
				<!-- Submit success message-->
				<!---->
				<!-- This is what your users will see when the form-->
				<!-- has successfully submitted-->
				<div class="d-none" id="submitSuccessMessage">
					<div class="text-center text-white mb-3">
						<div class="fw-bolder">Form submission successful!</div>
						To activate this form, sign up at <br /> <a
							href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
					</div>
				</div>
				<!-- Submit error message-->
				<!---->
				<!-- This is what your users will see when there is-->
				<!-- an error submitting the form-->
				<div class="d-none" id="submitErrorMessage">
					<div class="text-center text-danger mb-3">Error sending
						message!</div>
				</div>
				<!-- Submit Button-->
				<div class="text-center">
					<button class="btn btn-primary btn-xl text-uppercase disabled"
						id="submitButton" type="submit">Send Message</button>
				</div>
			</form>
		</div>
	</section>
	<!-- Footer-->
	<footer class="footer py-4">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-4 text-lg-start">Copyright &copy; Your
					Website 2021</div>
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
					<img src="/client/assets/img/close-icon.svg" alt="Close modal" />
				</div>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<div class="modal-body">
								<!-- Project details-->
								<h2 id="marqueModal" class="text-uppercase"></h2>
								<p id="categorieModal" class="item-intro text-muted"></p>
								<img id="imageModal" class="img-fluid d-block mx-auto"
									src="/client/assets/img/portfolio/1.jpg" alt="..." />
								
								<ul class="list-inline">
									<li><strong>Ann�e:</strong> <span id="annee"></span></li>
								</ul>
								<input type="number" class="form-control" id="delai"
									name="delai" placeholder="Delai" min="1" max="30" value="" />
								<p id="errorRes" style="color:red"></p>
								<p id="confirmation" style="color:green"></p>
								<button id="reserver"
									class="btn btn-primary btn-xl text-uppercase">
									Reserver</button>
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
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/client/js/scripts.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="/assets/js/jquery.js"></script>
	<script src="/client/js/reactiveTemp.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->


</body>
</html>
