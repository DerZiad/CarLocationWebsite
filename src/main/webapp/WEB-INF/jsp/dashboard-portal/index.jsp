<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="dashboard_header.jsp" />

<div class="app-page-title">
    <div class="page-title-wrapper">
        <div class="page-title-heading">
            <div class="page-title-icon">
                <i class="pe-7s-car icon-gradient bg-mean-fruit" style="color: red;"></i>
            </div>
            <div>
                MyCar Dashboard
                <div class="page-title-subheading">In this dashboard, it will be shown statistics and everything concerning the car rental in the website</div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-xl-4">
        <div class="card mb-3 widget-content bg-midnight-bloom">
            <div class="widget-content-wrapper text-white">
                <div class="widget-content-left">
                    <div class="widget-heading">Number of Trips</div>
                    <div class="widget-subheading">Number of trips not completed</div>
                </div>
                <div class="widget-content-right">
                    <div class="widget-numbers text-white">
                        <span>${voyagesnumber}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-xl-4">
        <div class="card mb-3 widget-content bg-arielle-smile">
            <div class="widget-content-wrapper text-white">
                <div class="widget-content-left">
                    <div class="widget-heading">Number of Teams</div>
                    <div class="widget-subheading">Number of available teams</div>
                </div>
                <div class="widget-content-right">
                    <div class="widget-numbers text-white">
                        <span>${equipesnumber}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-xl-4">
        <div class="card mb-3 widget-content bg-grow-early">
            <div class="widget-content-wrapper text-white">
                <div class="widget-content-left">
                    <div class="widget-heading">Number of Reservations</div>
                    <div class="widget-subheading">Number of paid reservations</div>
                </div>
                <div class="widget-content-right">
                    <div class="widget-numbers text-white">
                        <span>${reservationsnumber}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="d-xl-none d-lg-block col-md-6 col-xl-4">
        <div class="card mb-3 widget-content bg-premium-dark">
            <div class="widget-content-wrapper text-white">
                <div class="widget-content-left">
                    <div class="widget-heading">Number of Hotels</div>
                    <div class="widget-subheading">Number of recognized hotels</div>
                </div>
                <div class="widget-content-right">
                    <div class="widget-numbers text-warning">
                        <span>${hotelsnumber}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12 col-lg-12">
        <div class="mb-3 card">
            <div class="card-header-tab card-header-tab-animation card-header">
                <div class="card-header-title">
                    <i class="header-icon lnr-apartment icon-gradient bg-love-kiss">
                    </i> Visualization of Most Visited Countries
                </div>
            </div>
            <div class="card-body">
                <div class="tab-content">

                    <div class="tab-pane show active" id="tabs-eg-77">

                        <div class="widget-chat-wrapper-outer">
                            <div
                                class="widget-chart-wrapper widget-chart-wrapper-lg opacity-10 m-0">
                                <!-- chart here -->
                                <div id="columnchart_material"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-xl-4">
        <div class="card mb-3 widget-content">
            <div class="widget-content-outer">
                <div class="widget-content-wrapper">
                    <div class="widget-content-left">
                        <div class="widget-heading">Total Orders</div>
                        <div class="widget-subheading">Last year expenses</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-success">1896</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-xl-4">
        <div class="card mb-3 widget-content">
            <div class="widget-content-outer">
                <div class="widget-content-wrapper">
                    <div class="widget-content-left">
                        <div class="widget-heading">Products Sold</div>
                        <div class="widget-subheading">Revenue streams</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-warning">$3M</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-xl-4">
        <div class="card mb-3 widget-content">
            <div class="widget-content-outer">
                <div class="widget-content-wrapper">
                    <div class="widget-content-left">
                        <div class="widget-heading">Followers</div>
                        <div class="widget-subheading">People Interested</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-danger">45.9%</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="d-xl-none d-lg-block col-md-6 col-xl-4">
        <div class="card mb-3 widget-content">
            <div class="widget-content-outer">
                <div class="widget-content-wrapper">
                    <div class="widget-content-left">
                        <div class="widget-heading">Income</div>
                        <div class="widget-subheading">Expected totals</div>
                    </div>
                    <div class="widget-content-right">
                        <div class="widget-numbers text-focus">$147</div>
                    </div>
                </div>
                <div class="widget-progress-wrapper">
                    <div class="progress-bar-sm progress-bar-animated-alt progress">
                        <div class="progress-bar bg-info" role="progressbar"
                            aria-valuenow="54" aria-valuemin="0" aria-valuemax="100"
                            style="width: 54%;"></div>
                    </div>
                    <div class="progress-sub-label">
                        <div class="sub-label-left">Expenses</div>
                        <div class="sub-label-right">100%</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="main-card mb-3 card">
            <div class="card-header">
                Top Teams
            </div>
            <div class="table-responsive">
                <table
                    class="align-middle mb-0 table table-borderless table-striped table-hover">
                    <thead>
                        <tr>
                            <th class="text-center">Picture</th>
                            <th class="text-center">Name</th>
                            <th class="text-center">Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${equipes}" var="equipe">
                        <tr>
                            <td>
                                <div class="widget-content p-0">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left mr-3">
                                            <div class="widget-content-left">
                                                <img width="40" class="rounded-circle"
                                                    src="data:image/jpeg;base64,${equipe.personne.getBase64()}" alt="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="text-center">${equipe.personne.nom} ${equipe.personne.prenom}</td>
                            <td class="text-center">
                                <div class="badge badge-warning">${equipe.personne.email}</div>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="dashboard_footer.jsp" />
