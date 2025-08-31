<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>WIEBATOUTA</title>
<link rel="shortcut icon" type="image/icon" href="assets/logo/favicon.png"/>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<meta name="description"
	content="This is an example dashboard created using build-in elements and components.">
<meta name="msapplication-tap-highlight" content="no">
<link href="/delibdesign/css/main.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<layout:block name="cssfiles"></layout:block>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawChart);
	

	
	
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Pays', 'Réservations faites'],
				[ 'Réservé avec confirmation', ${reservationsdonenumber}],
				[ 'Réservé sans confirmation', ${reservationsnotdonenumber}]]);

		var options = {
			chart : {
				title : 'Statistique des etudiants',
				subtitle : 'Validé, ratrappage et non validé pour cette periode',
			}
		};

		var chart = new google.charts.Bar(document
				.getElementById('columnchart_material'));

		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
</script>
