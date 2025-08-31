<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="layout-resp.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<c:if test="${modification}">
					<form class="form-group" action="/manager/voiture/${voiture.id}"
						enctype="multipart/form-data" method="POST">
						<input type="hidden" name="id" value="${voiture.id}" />
						<h5 class="card-title">Modification d'une voiture</h5>
						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="marque" class="">Marque</label><select
										name="marque" id="marque" class="form-control">
										<c:forEach items="${marques}" var="marque">
											<c:if test="${marque eq voiture.marque}">
												<option value="${marque}" selected>${marque}</option>
											</c:if>
											<c:if test="${marque ne voiture.marque}">
												<option value="${marque}">${marque}</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<p style="color: red;">
									<c:out value="${errors.marque}"></c:out>
								</p>
							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="categorie" class="">Categorie</label> <select
										name="categorie" id="categorie" class="form-control">
										<c:forEach items="${categories}" var="categorie">
											<c:if test="${categorie eq voiture.categorie}">
												<option value="${categorie}" selected>${categorie}</option>
											</c:if>
											<c:if test="${categorie ne voiture.categorie}">
												<option value="${categorie}">${categorie}</option>
											</c:if>
										</c:forEach>
									</select>

								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="annee" class="">Années</label> <select name="annee"
										id="annee" class="form-control">
										<c:forEach items="${annees}" var="annee">
											<c:if test="${annee eq voiture.annee}">
												<option value="${annee}" selected>${annee}</option>
											</c:if>
											<c:if test="${annee ne voiture.annee}">
												<option value="${annee}">${annee}</option>
											</c:if>
										</c:forEach>
									</select>

								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="prix" class="">Prix</label> <input type="number"
										name="prix" id="prix" class="form-control"
										value="${voiture.prix}" />
								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6">
								<img style='display: block; width: 100px; height: 100px;'
									id='base64image' name="img"
									src='data:image/jpeg;base64,${voiture.base64}' />
							</div>
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="picturePart" class="">Image</label> <input
										type="file" name="picturePart" id="picturePart"
										class="form-control" value="${voiture.prix}" />
								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<button class="mt-2 btn btn-primary col-md-6" type="submit">Enregistrer</button>
					</form>
				</c:if>
				<c:if test="${not modification}">
					<form class="form-group" action="/manager/voiture"
						enctype="multipart/form-data" method="POST">
						<h5 class="card-title">Ajout d'une voiture</h5>
						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="marque" class="">Marque</label><select
										name="marque" id="marque" class="form-control">
										<c:forEach items="${marques}" var="marque">
											<c:if test="${marque eq voiture.marque}">
												<option value="${marque}">${marque}</option>
											</c:if>
											<c:if test="${marque ne voiture.marque}">
												<option value="${marque}" selected>${marque}</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<p style="color: red;">
									<c:out value="${errors.marque}"></c:out>
								</p>
							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="categorie" class="">Categorie</label> <select
										name="categorie" id="categorie" class="form-control">
										<c:forEach items="${categories}" var="categorie">
											<c:if test="${categorie eq voiture.categorie}">
												<option value="${categorie}" selected>${categorie}</option>
											</c:if>
											<c:if
												test="${not categorie.toString().equals(voiture.categorie.toString())}">
												<option value="${categorie}">${categorie}</option>
											</c:if>
										</c:forEach>
									</select>

								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="annee" class="">Années</label> <select name="annee"
										id="annee" class="form-control">
										<c:forEach items="${annees}" var="annee">
											<c:if test="${annee eq voiture.annee}">
												<option value="${annee}">${annee}</option>
											</c:if>
											<c:if test="${annee ne voiture.annee}">
												<option value="${annee}" selected>${annee}</option>
											</c:if>
										</c:forEach>
									</select>

								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="prix" class="">Prix</label> <input type="number"
										name="prix" id="prix" class="form-control"
										value="${voiture.prix}" />
								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-6">
								<img style='display: block; width: 100px; height: 100px;'
									id='base64image' name="img"
									src='data:image/jpeg;base64,${voiture.base64}' />
							</div>
							<div class="col-md-6">
								<div class="position-relative form-group">
									<label for="picturePart" class="">Image</label> <input
										type="file" name="picturePart" id="picturePart"
										class="form-control" value="${voiture.prix}" />
								</div>
								<p style="color: red;">
									<c:out value="${errors.categorie}"></c:out>
								</p>
							</div>
						</div>
						<button class="mt-2 btn btn-primary col-md-6" type="submit">Enregistrer</button>
					</form>
				</c:if>
			</div>
			<div class="main-card mb-3">
				<div class="card-body">
					<h5 class="card-title">Liste des voitures</h5>
					<table class="mb-0 table table-striped">
						<thead>
							<tr>
								<th>Picture</th>
								<th>Categorie</th>
								<th>Marque</th>
								<th>Année</th>
								<th>Prix</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="voiture" items="${voitures}">
								<tr>
									<td style="color: black"><img
										style='display: block; width: 100px; height: 100px;'
										id='base64image'
										src='data:image/jpeg;base64,${voiture.base64}' /></td>
									<td style="color: black">${voiture.categorie.toString()}</td>
									<td style="color: black">${voiture.marque.toString()}</td>
									<td style="color: black">${voiture.annee}</td>
									<td style="color: black">${voiture.prix}</td>
									<td><a style="color: black"
										href="/manager/voiture/${voiture.id}"> <i
											class="material-icons" data-toggle="tooltip" title="Edit"
											style="color: #1de9b6">&#xE254;</i>
									</a> <a href="/manager/voiture/delete/${voiture.id}" class="delete"><i
											class="material-icons" data-toggle="tooltip" title="Delete"
											style="color: #f44336;">&#xE872;</i></a></td>
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