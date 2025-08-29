<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="apple-touch-icon" sizes="76x76"
	href="/assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<title>TeamDev</title>
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
<link href="/assets/css/nucleo-icons.css" rel="stylesheet" />
<link href="/assets/css/nucleo-svg.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/42d5adcbca.js"
	crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Round"
	rel="stylesheet">
<link id="pagestyle" href="/assets/css/material-dashboard.css?v=3.0.0"
	rel="stylesheet" />
</head>
<layout:block name="cssfiles"></layout:block>
<body class="">
	<div class="container position-sticky z-index-sticky top-0">
		<div class="row">
			<div class="col-12">
				<!-- Navbar -->
				<nav
					class="navbar navbar-expand-lg blur border-radius-lg top-0 z-index-3 shadow position-absolute mt-4 py-2 start-0 end-0 mx-4">
					<div class="container-fluid ps-2 pe-0">
						<a class="navbar-brand font-weight-bolder ms-lg-0 ms-3 "
							href="/"> Coding day </a>
						<button class="navbar-toggler shadow-none ms-2" type="button"
							data-bs-toggle="collapse" data-bs-target="#navigation"
							aria-controls="navigation" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon mt-2"> <span
								class="navbar-toggler-bar bar1"></span> <span
								class="navbar-toggler-bar bar2"></span> <span
								class="navbar-toggler-bar bar3"></span>
							</span>
						</button>
						<div class="collapse navbar-collapse" id="navigation">
							<ul class="navbar-nav mx-auto">
								<li class="nav-item"><a
									class="nav-link d-flex align-items-center me-2 active"
									aria-current="page" href="/"> <i
										class="fa fa-chart-pie opacity-6 text-dark me-1"></i>
										Home
								</a></li>
								<li class="nav-item"><a class="nav-link me-2"
									href="/signup"> <i
										class="fas fa-user-circle opacity-6 text-dark me-1"></i> Sign
										Up
								</a></li>
								<li class="nav-item"><a class="nav-link me-2"
									href="/login"> <i
										class="fas fa-key opacity-6 text-dark me-1"></i> Sign In
								</a></li>
							</ul>
						</div>
					</div>
				</nav>
				<!-- End Navbar -->
			</div>
		</div>
	</div>
	<layout:block name="content"></layout:block>
</body>
<script src="/assets/js/core/popper.min.js"></script>
<script src="/assets/js/core/bootstrap.min.js"></script>
<script src="/assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="/assets/js/plugins/smooth-scrollbar.min.js"></script>
<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
      var options = {
        damping: '0.5'
      }
      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
  </script>
<script async defer src="https://buttons.github.io/buttons.js"></script>
<script src="/assets/js/material-dashboard.min.js?v=3.0.0"></script>
<layout:block name="scriptsjs"></layout:block>
</html>