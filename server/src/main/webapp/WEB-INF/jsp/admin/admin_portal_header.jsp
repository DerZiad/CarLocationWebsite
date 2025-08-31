<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Material Dashboard 2 by Creative Tim</title>
<layout:block name="cssfiles"></layout:block>
</head>

<body class="g-sidenav-show  bg-gray-200">
	<aside
		class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark"
		id="sidenav-main">
		<div class="sidenav-header">
			<i
				class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
				aria-hidden="true" id="iconSidenav"></i> <a class="navbar-brand m-0"
				href=" https://demos.creative-tim.com/material-dashboard/pages/dashboard "
				target="_blank"> <img src="../assets/img/logo-ct.png"
				class="navbar-brand-img h-100" alt="main_logo"> <span
				class="ms-1 font-weight-bold text-white">MyCar</span>
			</a>
		</div>
		<hr class="horizontal light mt-0 mb-2">
		<div class="collapse navbar-collapse  w-auto  max-height-vh-100"
			id="sidenav-collapse-main">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link text-white "
					href="/admin">
						<div
							class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">dashboard</i>
						</div> <span class="nav-link-text ms-1">Dashboard</span>
				</a></li>
				<li class="nav-item"><a
					class="nav-link text-white active bg-gradient-primary"
					href="/admin/voiture">
						<div
							class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">table_view</i>
						</div> <span class="nav-link-text ms-1">Voitures</span>
				</a></li>
				<li class="nav-item"><a class="nav-link text-white "
					href="../pages/billing.html">
						<div
							class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">receipt_long</i>
						</div> <span class="nav-link-text ms-1">Billing</span>
				</a></li>
				<li class="nav-item"><a class="nav-link text-white "
					href="../pages/virtual-reality.html">
						<div
							class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">view_in_ar</i>
						</div> <span class="nav-link-text ms-1">Virtual Reality</span>
				</a></li>
				<li class="nav-item"><a class="nav-link text-white "
					href="../pages/rtl.html">
						<div
							class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">format_textdirection_r_to_l</i>
						</div> <span class="nav-link-text ms-1">RTL</span>
				</a></li>
				<li class="nav-item"><a class="nav-link text-white "
					href="../pages/notifications.html">
						<div
							class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">notifications</i>
						</div> <span class="nav-link-text ms-1">Notifications</span>
				</a></li>
				<li class="nav-item mt-3">
					<h6
						class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Account
						pages</h6>
				</li>
				<li class="nav-item"><a class="nav-link text-white "
					href="../pages/profile.html">
						<div
							class="text-white text-center me-2 d-flex align-items-center justify-content-center">
							<i class="material-icons opacity-10">person</i>
						</div> <span class="nav-link-text ms-1">Profile</span>
				</a></li>
			</ul>
		</div>
		<div class="sidenav-footer position-absolute w-100 bottom-0 ">
			<div class="mx-3">
				<a class="btn bg-gradient-primary mt-4 w-100"
					href="https://www.creative-tim.com/product/material-dashboard-pro?ref=sidebarfree"
					type="button">Upgrade to pro</a>
			</div>
		</div>
	</aside>