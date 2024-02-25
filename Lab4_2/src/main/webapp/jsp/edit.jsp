<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ page import="com.example.Game" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Редактирование</title>
	<!-- Подключение Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
	<div class="row justify-content-center align-items-center vh-100">
		<form class="col-lg-4 col-md-6 col-sm-8 col-10 p-5 shadow rounded text-center">
			<h4 class="mb-4 font-weight-bold">Редактировать запись</h4>
			<div class="form-group">
				<input class="form-control mt-2 rounded" id="name" type="text" placeholder="Название"
					   value="<%=((Game) request.getAttribute("game")).getName()%>">
			</div>
			<div class="form-group">
				<input class="form-control mt-2 rounded" id="genre" type="text" placeholder="Жанр"
					   value="<%=((Game) request.getAttribute("game")).getGenre()%>">
			</div>
			<div class="form-group">
				<input class="form-control mt-2 rounded" id="platform" type="text" placeholder="Платформа"
					   value="<%=((Game) request.getAttribute("game")).getPlatform()%>">
			</div>
			<div class="form-group">
				<input class="form-control mt-2 rounded" id="year" type="number" placeholder="Год выпуска"
					   value="<%=((Game) request.getAttribute("game")).getYear()%>">
			</div>
			<div class="form-group">
				<input class="form-control mt-2 rounded" id="price" type="number" placeholder="Цена"
					   value="<%=((Game) request.getAttribute("game")).getPrice()%>">
			</div>
			<button type="button" class="btn btn-info mt-2 rounded" onclick="sendEditedGame()">Сохранить</button>
		</form>
	</div>
</div>

<!-- Подключение Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/editor.js"></script>
</body>
</html>
