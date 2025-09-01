<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="dashboard_header.jsp"/>

<div class="main-card mb-3 card">
    <div class="card-body">
        <!-- Manager hinzufügen Formular -->
        <form class="form-group" action="<c:url value="/admin/manager"/>" enctype="multipart/form-data" method="POST">
            <h5 class="card-title">Add Manager</h5>
            <div class="form-row">
                <div class="col-md-6">
                    <div class="position-relative form-group">
                        <label for="username" class="">Username</label>
                        <input value="<c:out value="${manager.username}"/>" name="username" id="username" class="form-control"
                               pattern=".{8,}" title="Mindestens 8 Zeichen" required/>
                    </div>
                    <p style="color: red;">
                        <c:out value="${errors.username}"/>
                    </p>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-6">
                    <div class="position-relative form-group">
                        <label for="email" class="">Email</label>
                        <input name="email" id="email" class="form-control" value="<c:out value="${manager.email}"/>" type="email" required/>
                    </div>
                    <p style="color: red;">
                        <c:out value="${errors.email}"/>
                    </p>
                </div>
            </div>
            <button class="mt-2 btn btn-primary col-md-6" type="submit">Save</button>
        </form>
    </div>
    <div class="main-card mb-3">
        <div class="card-body">
            <h5 class="card-title">Manager List</h5>
            <form id="managerFilterForm" onsubmit="return false;">
                <div class="form-row">
                    <div class="col-md-6">
                        <input type="text" id="filterInput" class="form-control" placeholder="Suche Username oder Email">
                    </div>
                </div>
            </form>
            <table id="managerTable" class="mb-0 table table-striped">
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
                                <a href="<c:url value="/admin/ban?username=${manager.username}&userType=manager"/>" class="delete">
                                    <i class="material-icons" data-toggle="tooltip" title="Delete" style="color: #f44336;">&#xE872;</i>
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <script>
                function escapeHtml(text) {
                    var map = {
                        '&': '&amp;',
                        '<': '&lt;',
                        '>': '&gt;',
                        '"': '&quot;',
                        "'": '&#039;'
                    };
                    return text.replace(/[&<>"']/g, function(m) { return map[m]; });
                }
                function highlight(text, search) {
                    if (!search) return escapeHtml(text);
                    const regex = new RegExp('(' + search.replace(/[.*+?^${}()|[\]\\]/g, '\\$&') + ')', 'gi');
                    return escapeHtml(text).replace(regex, '<span style="background:#757500;color:#fff;">$1</span>');
                }
                function filterManagers() {
                    const search = document.getElementById('filterInput').value.trim().toLowerCase();
                    const rows = document.querySelectorAll('#managerTable tbody tr');
                    rows.forEach(row => {
                        const userCell = row.cells[0];
                        const emailCell = row.cells[1];
                        const username = userCell.textContent;
                        const email = emailCell.textContent;
                        if (!search) {
                            row.style.display = '';
                            userCell.innerHTML = escapeHtml(username);
                            emailCell.innerHTML = escapeHtml(email);
                            return;
                        }
                        const matchUser = username.toLowerCase().includes(search);
                        const matchEmail = email.toLowerCase().includes(search);
                        if (matchUser || matchEmail) {
                            row.style.display = '';
                            userCell.innerHTML = highlight(username, search);
                            emailCell.innerHTML = highlight(email, search);
                        } else {
                            row.style.display = 'none';
                            userCell.innerHTML = escapeHtml(username);
                            emailCell.innerHTML = escapeHtml(email);
                        }
                    });
                }
                document.getElementById('filterInput').addEventListener('input', filterManagers);
            </script>
        </div>
    </div>
</div>
<jsp:include page="dashboard_footer.jsp"/>
