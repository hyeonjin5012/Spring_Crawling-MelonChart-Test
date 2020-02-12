<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bootstrap 4 Horizontal Card</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
.bs-example {
	margin: 20px;
}
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="container">
			<div class="col text-center ">
				<h1 class="display-4 text-success mb-0">
					<strong>Melon Chart</strong>
				</h1>
			</div>
			<br />
			<div class="row " id="chart--title">
				<c:forEach var="chart" items="${charts}">
					<div class="col-md-6 col-lg-4 my-3 card--items">
						<div class="card ">
							<img src="${chart.photo}" class="card-img-top mx-auto" alt="" style="width: 200px;" />
							<div class="card-header d-flex">${chart.ranking}위
								<button class="ml-auto"onclick="music_play('${chart.musicLink}')">▶</button>
								<button onclick="music_stop('${chart.musicLink}')">ΙΙ</button>
								<button onclick="music_replay('${chart.musicLink}')">■</button>
							</div>
							<div class="card-body">
								<h5 class="card-title text-capitalize">${chart.title}</h5>
								<p class="card-text">${chart.singer}</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<br />
			<ul class="pagination  justify-content-center">
				<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>

				<c:forEach var="i" begin="1" end="17" step="1">

					<li class="page-item"><a class="page-link" onclick="page(${i})">${i}</a></li>

				</c:forEach>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</div>
	</div>

<div id="test"></div>
<script src="/js/melonChart.js"></script>
</body>
</html>
