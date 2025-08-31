<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>Car Rental</title>
    <link rel="shortcut icon" type="image/icon" href="assets/logo/favicon.png"/>
    <meta name="viewport"
    	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description"
    	content="This is an example dashboard created using built-in elements and components.">
    <meta name="msapplication-tap-highlight" content="no">
    <link href="/delibdesign/css/main.css" rel="stylesheet">
    <link rel="stylesheet"
    	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <layout:block name="cssfiles"></layout:block>
    <script type="text/javascript"
    	src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    	google.charts.load('current', {
    		'packages' : [ 'bar' ]
    	});
    	google.charts.setOnLoadCallback(drawChart);

    	function drawChart() {
    		var data = google.visualization.arrayToDataTable([
    				[ 'Country', 'Reservations made'],
    				[ 'Reserved with confirmation', ${reservationsdonenumber}],
    				[ 'Reserved without confirmation', ${reservationsnotdonenumber}]]);

    		var options = {
    			chart : {
    				title : 'Student Statistics',
    				subtitle : 'Validated, retake and not validated for this period',
    			}
    		};

    		var chart = new google.charts.Bar(document
    				.getElementById('columnchart_material'));

    		chart.draw(data, google.charts.Bar.convertOptions(options));
    	}
    </script>
</head>
<body>
	<div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
		<div class="app-header header-shadow">
        	<div class="app-header__logo">
        		<div class="logo-src"></div>
        		<div class="header__pane ml-auto">
        			<div>
        				<button type="button"
        					class="hamburger close-sidebar-btn hamburger--elastic"
        					data-class="closed-sidebar">
        					<span class="hamburger-box"> <span class="hamburger-inner"></span>
        					</span>
        				</button>
        			</div>
        		</div>
        	</div>
        	<div class="app-header__mobile-menu">
        		<div>
        			<button type="button"
        				class="hamburger hamburger--elastic mobile-toggle-nav">
        				<span class="hamburger-box"> <span class="hamburger-inner"></span>
        				</span>
        			</button>
        		</div>
        	</div>
        	<div class="app-header__menu">
        		<span>
        			<button type="button"
        				class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
        				<span class="btn-icon-wrapper"> <i
        					class="fa fa-ellipsis-v fa-w-6"></i>
        				</span>
        			</button>
        		</span>
        	</div>
        	<div class="app-header__content">
        		<div class="app-header-left">
        			<div class="search-wrapper">
        				<div class="input-holder">
        					<input type="text" class="search-input"
        						placeholder="Type to search">
        					<button class="search-icon">
        						<span></span>
        					</button>
        				</div>
        				<button class="close"></button>
        			</div>

        		</div>
        		<div class="app-header-right">
        			<div class="header-btn-lg pr-0">
        				<div class="widget-content p-0">
        					<div class="widget-content-wrapper">
        						<div class="widget-content-left">
        							<div class="btn-group">
        								<a data-toggle="dropdown" aria-haspopup="true"
        									aria-expanded="false" class="p-0 btn"> <img width="42"
        									class="rounded-circle"
        									src="assets/images/avatars/default-profile.jpg" alt=""> <i
        									class="fa fa-angle-down ml-2 opacity-8"></i>
        								</a>
        								<div tabindex="-1" role="menu" aria-hidden="true"
        									class="dropdown-menu dropdown-menu-right">
        									<a href="/logout" tabindex="0" class="dropdown-item">Logout</a>
        								</div>
        							</div>
        						</div>
        						<div class="widget-content-left  ml-3 header-user-info">
        							<div class="widget-heading">Admin</div>
        						</div>
        					</div>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>
		<div class="app-main">
				<div class="app-sidebar sidebar-shadow">
                	<div class="app-header__logo">
                		<div class="logo-src"></div>
                		<div class="header__pane ml-auto">
                			<div>
                				<button type="button"
                					class="hamburger close-sidebar-btn hamburger--elastic"
                					data-class="closed-sidebar">
                					<span class="hamburger-box"> <span class="hamburger-inner"></span>
                					</span>
                				</button>
                			</div>
                		</div>
                	</div>
                	<div class="app-header__mobile-menu">
                		<div>
                			<button type="button">
                				<span class="hamburger-box"> <span class="hamburger-inner"></span>
                				</span>
                			</button>
                		</div>
                	</div>
                	<div class="app-header__menu">
                		<span>
                			<button type="button"
                				class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                				<span class="btn-icon-wrapper"> <i
                					class="fa fa-ellipsis-v fa-w-6"></i>
                				</span>
                			</button>
                		</span>
                	</div>
                	<div class="scrollbar-sidebar">
                		<div class="app-sidebar__inner">
                			<ul class="vertical-nav-menu">
                				<li class="app-sidebar__heading">Home</li>
                				<li><a href="/shared" class="${dashboard}"> <i
                						class="metismenu-icon pe-7s-rocket"></i> Dashboard
                				</a></li>
                				<li><a href="/shared/car" class="${dashboard}"> <i
                                        class="metismenu-icon pe-7s-id"></i> Car
                                </a></li>
                                <c:if test="${user.isAdmin()}">
                                    <li><a href="/shared/reservation" aria-expanded="false"> <i
                                            class="metismenu-icon pe-7s-id"></i> Reservations
                                    </a></li>
                                    <li><a href="/admin/manager" aria-expanded="false"> <i
                                            class="metismenu-icon pe-7s-id"></i> Managers
                                    </a></li>
                                    <li><a href="/admin/client" aria-expanded="false"> <i
                                            class="metismenu-icon pe-7s-id"></i> Clients
                                    </a></li>
                                    <li><a href="/admin/history" aria-expanded="false"> <i
                                            class="metismenu-icon pe-7s-id"></i> History
                                    </a></li>
                                </c:if>
                			</ul>
                		</div>
                	</div>
                </div>
			<div class="app-main__outer">
				<div class="app-main__inner">