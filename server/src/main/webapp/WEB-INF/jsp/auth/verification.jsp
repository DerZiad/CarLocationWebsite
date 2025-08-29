<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<layout:extends name="base.jsp">
	<layout:put block="content" type="REPLACE">
		<main class="main-content  mt-0">
			<section>
				<div class="page-header min-vh-100">
					<div class="container">
						<div class="row">
							<div
								class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
								<div
									class="position-relative bg-gradient-primary h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center"
									style="background-image: url('https://images.unsplash.com/photo-1529419412599-7bb870e11810?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80'); background-size: cover;">
								</div>
							</div>
							<div
								class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
								<div class="card card-plain">
									<div class="card-header">
										<p class="mb-0">Please enter a code to verificate your
											account</p>
									</div>
									<div class="card-body">
										<form role="form" action="verification" method="POST">
											<div class="input-group input-group-outline mb-3">
												<label class="form-label">Code</label> <input type="number"
													class="form-control" name="code" required>
											</div>
											<c:if test="${error ne null }">
												<div
													class="input-group input-group-outline mb-3 alert alert-danger">
													<div class="row">
														<div class="col-12">
															<h3 style="color: white">Error</h3>
														</div>
													</div>
													<div class="row">
														<div class="col-12">
															<br>
															<p style="color: white">${error}</p>
														</div>
													</div>
												</div>
											</c:if>
											<div class="text-center">
												<input type="submit" value="Sign up"
													class="btn btn-lg bg-gradient-primary btn-lg w-100 mt-4 mb-0" />
											</div>
										</form>
									</div>
									<div class="card-footer text-center pt-0 px-lg-2 px-1">
										<p class="mb-2 text-sm mx-auto">
											Already have an account? <a href="/login"
												class="text-primary text-gradient font-weight-bold">Sign
												in</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</main>
	</layout:put>
</layout:extends>
