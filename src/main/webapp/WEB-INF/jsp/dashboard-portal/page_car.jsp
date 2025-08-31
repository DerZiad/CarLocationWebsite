<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="dashboard_header.jsp" />

<div class="main-card mb-3 card">
    <div class="card-body">
        <c:if test="${modification}">
			<form class="form-group" action="/shared/car/${car.id}"
				enctype="multipart/form-data" method="POST">
				<input type="hidden" name="id" value="${car.id}" />
				<h5 class="card-title">Edit Car</h5>
				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="brand" class="">Brand</label>
							<select name="brand" id="brand" class="form-control">
								<c:forEach items="${brands}" var="brand">
									<c:if test="${brand eq car.brand}">
										<option value="${brand}" selected>${brand}</option>
									</c:if>
									<c:if test="${brand ne car.brand}">
										<option value="${brand}">${brand}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<p style="color: red;">
							<c:out value="${errors.brand}"></c:out>
						</p>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="category" class="">Category</label>
							<select name="category" id="category" class="form-control">
								<c:forEach items="${categories}" var="category">
									<c:if test="${category eq car.category}">
										<option value="${category}" selected>${category}</option>
									</c:if>
									<c:if test="${category ne car.category}">
										<option value="${category}">${category}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<p style="color: red;">
							<c:out value="${errors.category}"></c:out>
						</p>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="year" class="">Year</label>
							<select name="year" id="year" class="form-control">
								<c:forEach items="${years}" var="year">
									<c:if test="${year eq car.year}">
										<option value="${year}" selected>${year}</option>
									</c:if>
									<c:if test="${year ne car.year}">
										<option value="${year}">${year}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<p style="color: red;">
							<c:out value="${errors.year}"></c:out>
						</p>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="price" class="">Price</label>
							<input type="number" name="price" id="price" class="form-control"
								value="${car.price}" />
						</div>
						<p style="color: red;">
							<c:out value="${errors.price}"></c:out>
						</p>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6">
						<img style='display: block; width: 100px; height: 100px;'
							id='base64image' name="img"
							src='data:image/jpeg;base64,${car.base64Image}' />
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="picturePart" class="">Image</label>
							<input type="file" name="picturePart" id="picturePart"
								class="form-control" />
						</div>
						<p style="color: red;">
							<c:out value="${errors.image}"></c:out>
						</p>
					</div>
				</div>
				<button class="mt-2 btn btn-primary col-md-6" type="submit">Save</button>
			</form>
		</c:if>
		<c:if test="${not modification}">
			<form class="form-group" action="/shared/car"
				enctype="multipart/form-data" method="POST">
				<h5 class="card-title">Add Car</h5>
				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="brand" class="">Brand</label>
							<select name="brand" id="brand" class="form-control">
								<c:forEach items="${brands}" var="brand">
									<option value="${brand}">${brand}</option>
								</c:forEach>
							</select>
						</div>
						<p style="color: red;">
							<c:out value="${errors.brand}"></c:out>
						</p>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="category" class="">Category</label>
							<select name="category" id="category" class="form-control">
								<c:forEach items="${categories}" var="category">
									<option value="${category}">${category}</option>
								</c:forEach>
							</select>
						</div>
						<p style="color: red;">
							<c:out value="${errors.category}"></c:out>
						</p>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="year" class="">Year</label>
							<select name="year" id="year" class="form-control">
								<c:forEach items="${years}" var="year">
									<option value="${year}">${year}</option>
								</c:forEach>
							</select>
						</div>
						<p style="color: red;">
							<c:out value="${errors.year}"></c:out>
						</p>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="price" class="">Price</label>
							<input type="number" name="price" id="price" class="form-control"
								value="${car.price}" />
						</div>
						<p style="color: red;">
							<c:out value="${errors.price}"></c:out>
						</p>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6">
						<img style='display: block; width: 100px; height: 100px;'
							id='base64image' name="img"
							src='data:image/jpeg;base64,${car.base64Image}' />
					</div>
					<div class="col-md-6">
						<div class="position-relative form-group">
							<label for="picturePart" class="">Image</label>
							<input type="file" name="picturePart" id="picturePart"
								class="form-control" />
						</div>
						<p style="color: red;">
							<c:out value="${errors.image}"></c:out>
						</p>
					</div>
				</div>
				<button class="mt-2 btn btn-primary col-md-6" type="submit">Save</button>
			</form>
		</c:if>
    </div>
</div>

<!-- Scripts -->
<script src="/delibdesign/js/Theme/script.js"></script>
<script src="/delibdesign/js/hotel/template.js"></script>

<jsp:include page="dashboard_footer.jsp" />
