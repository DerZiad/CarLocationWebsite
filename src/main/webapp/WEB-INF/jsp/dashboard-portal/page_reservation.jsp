<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="dashboard_header.jsp" />

<div class="main-card mb-3 card">
    <div class="card-body"></div>
    <div class="main-card mb-3">
        <div class="card-body">
            <h5 class="card-title">Reservation List</h5>
            <table class="mb-0 table table-striped">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Category</th>
                        <th>Brand</th>
                        <th>Year</th>
                        <th>Price</th>
                        <th>Days</th>
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
                            <td style="color: black">${reservation.delai} days</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="dashboard_footer.jsp" />
