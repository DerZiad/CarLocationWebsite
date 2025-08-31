<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="admin_portal_header.jsp" />

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
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="/delibdesign/js/Theme/script.js"></script>
<script src="/delibdesign/js/hotel/template.js"></script>

<jsp:include page="admin_portal_footer.jsp" />
