<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="dashboard_header.jsp" />

<div class="main-card mb-3 card">
    <div class="card-body">
        <h5 class="card-title">History List</h5>
        <a class="btn btn-success" href="/admin/history/clear">Clear</a>
        <table class="mb-0 table table-striped">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>History</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="history" items="${historyData}">
                    <tr>
                        <td>${history.date}</td>
                        <td>${history.action}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="dashboard_footer.jsp" />
