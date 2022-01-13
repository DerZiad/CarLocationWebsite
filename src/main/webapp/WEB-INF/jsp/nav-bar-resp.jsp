 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<li class="app-sidebar__heading">Accueil</li>
				<li><a href="/admin" class="${dashboard}"> <i
						class="metismenu-icon pe-7s-rocket"></i> Tableau de bords
				</a></li>
				<li><a href="#" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Voitures <i
						class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
				</a>
					<ul class="mm-collapse" style="height: 7.04px;">
						<li><a href="/admin/voiture"
					class="${lieuAjout}"> <i
						class="metismenu-icon pe-7s-graph3"></i> Ajouter voiture
				</a></li>
						
					</ul></li>
				<li><a href="/admin/manager" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Managers
				</a></li>
				<li><a href="/admin/client" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Clients
				</a></li>
				<li><a href="/admin/reservation" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Reservations
				</a></li>
				<li><a href="/admin/historiques" aria-expanded="false"> <i
						class="metismenu-icon pe-7s-id"></i> Historiques
				</a></li>
				
				
			</ul>
		</div>
	</div>
</div>