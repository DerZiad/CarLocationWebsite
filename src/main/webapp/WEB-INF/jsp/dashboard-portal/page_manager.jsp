<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="dashboard_header.jsp" />

<div class="main-card mb-3 card">
    <div class="card-body">
        <c:if test="${not modification}">
            <form class="form-group" action="/admin/manager" enctype="multipart/form-data" method="POST">
                <h5 class="card-title">Add Manager</h5>
                <div class="form-row">
                    <div class="col-md-6">
                        <div class="position-relative form-group">
                            <label for="username" class="">Username</label>
                            <input value="${user.username}" name="username" id="username" class="form-control" pattern=".*\..*" title="Username must be in the format username.username, minimum 8 characters" />
                        </div>
                        <p style="color: red;">
                            <c:out value="${errors.username}"></c:out>
                        </p>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6">
                        <div class="position-relative form-group">
                            <label for="email" class="">Email</label>
                            <input name="email" id="email" class="form-control" value="${user.email}" />
                        </div>
                        <p style="color: red;">
                            <c:out value="${errors.email}"></c:out>
                        </p>
                    </div>
                </div>
                <button class="mt-2 btn btn-primary col-md-6" type="submit">Save</button>
            </form>
        </c:if>
    </div>
    <div class="main-card mb-3">
        <div class="card-body">
            <h5 class="card-title">Manager List</h5>
            <table class="mb-0 table table-striped">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="manager" items="${managers}">
                        <tr>
                            <td style="color: black">${manager.username}</td>
                            <td style="color: black">${manager.email}</td>
                            <c:if test="${not manager.enabled}">
                                <td style="color: red">BANNED</td>
                            </c:if>
                            <c:if test="${manager.enabled}">
                                <td>
                                    <a href="/admin/ban?username=${manager.username}&userType=manager" class="delete">
                                        <i class="material-icons" data-toggle="tooltip" title="Delete" style="color: #f44336;">&#xE872;</i>
                                    </a>
                                </td>
                            </c:if>
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

<jsp:include page="dashboard_footer.jsp" />
