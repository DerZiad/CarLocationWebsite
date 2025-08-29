<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<layout:extends name="../layout-resp.jsp">
	<layout:put block="content" type="REPLACE">
		<div class="table-wrapper">			
			<div class="form-row">
				<div class="col-md-6">
					<label>Date D'Arrivee</label> <input type="date"
						class="form-control" name="dateArriveDate" required />
					<p id="dateDepartError" class="error"></p>
				</div>
			</div>
			<div class="budget-wrap">
				<div class="budget">
					<div class="header">
						<div class="title clearfix">
							Set your budget! <span class="pull-right"></span>
						</div>
					</div>
					<div class="content">
						<input name="budget" type="range" min="100" max="10000" value="300"
							data-rangeslider>
					</div>
					<div class="footer clearfix">
						<div class="pull-right">
							<a href="javascript:void(0)" class="btn btn-def">Back</a> <a
								href="javascript:void(0)" class="btn btn-pri">Next</a>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div></div>
		<div class="table-title">
			<div class="row">
				<div class="col-sm-6">
					<h2>Reservations</h2>
				</div>

			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th><span class="custom-checkbox"> <input
								type="checkbox" id="selectAll"> <label for="selectAll"></label>
						</span></th>
						<th>Label Voyage</th>
						<th>Informations Client</th>
						<th>Pays de Destination</th>
						<th>Prix</th>
						<th>Confirmed</th>
					</tr>
				</thead>
				<tbody id="reservationslist" style="color: black;">

				</tbody>
			</table>
		</div>
	</layout:put>


	<layout:put block="cssfiles" type="REPLACE">
		<style>
.error {
	color: red;
	font-size: 15px;
}
</style>
		<link rel="stylesheet" href="/delibdesign/css/reservation/style.css" />
	</layout:put>

	<layout:put block="scriptsfile" type="REPLACE">
		<script src="/delibdesign/js/Reservation/script.js"></script>
		<script src="/delibdesign/js/Reservation/template.js"></script>
	</layout:put>
</layout:extends>