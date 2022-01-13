<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout-resp.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body"></div>
			<div class="main-card mb-3">
				<div class="card-body">
					<h5 class="card-title">Liste des reservation</h5>
					<table class="mb-0 table table-striped">
						<thead>
							<tr>
								<th>User</th>
								<th>Categorie</th>
								<th>Marque</th>
								<th>Année</th>
								<th>Prix</th>
								<th>Jours</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="reservation" items="${reservations}">
								<tr>
									<td style="color: black">${reservation.user.username}</td>
									<td style="color: black">${reservation.voiture.categorie.toString()}</td>
									<td style="color: black">${reservation.voiture.marque.toString()}</td>
									<td style="color: black">${reservation.voiture.annee}</td>
									<td style="color: black">${reservation.voiture.prix}</td>
									<td style="color: black">${reservation.delai} jours</td>
									<td><a style="color: black"
										href="/manager/reservation/accepter/${reservation.voiture.id}/${reservation.user.id}">
											<i class="material-icons" data-toggle="tooltip" title="Edit"
											style="color: #1de9b6">&#xE254;</i>
									</a> <a
										href="/manager/reservation/delete/${reservation.voiture.id}/${reservation.user.id}"
										class="delete"><i class="material-icons"
											data-toggle="tooltip" title="Delete" style="color: #f44336;">&#xE872;</i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</layout:put>
	<layout:put block="scriptsfile" type="REPLACE">
		<script src="/delibdesign/js/Theme/script.js"></script>
		<script src="/delibdesign/js/hotel/template.js"></script>
	</layout:put>
</layout:extends>