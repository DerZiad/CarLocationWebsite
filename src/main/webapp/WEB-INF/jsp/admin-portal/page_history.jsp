<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="admin_portal_header.jsp" />

<div class="main-card mb-3 card">
    <div class="card-body">
        <h5 class="card-title">Liste des historiques</h5>
        <a class="btn btn-success" href="/admin/historiques/vider">Vider</a>
        <table class="mb-0 table table-striped">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Historique</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="historique" items="${historiques}">
                    <tr>
                        <td>${historique.date}</td>
                        <td>${historique.action}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="admin_portal_footer.jsp" />
