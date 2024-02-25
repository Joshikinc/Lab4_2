<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Чтение</title>
	<!-- Подключение Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
	<table id="main_table" class="table table-info w-50 shadow rounded text-center position-absolute top-50 start-50 translate-middle">
		<thead class="bg-success">
		<tr>
			<th scope="col">Название</th>
			<th scope="col">Жанр</th>
			<th scope="col">Платформа</th>
			<th scope="col">Год выпуска</th>
			<th scope="col">Цена</th>
			<th scope="col">ID</th>
			<th scope="col">Удаление</th>
			<th scope="col">Редактирование</th>
		</tr>
		</thead>
		<tbody id="table_body">
		</tbody>
	</table>
</div>

<!-- Подключение Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/reader.js"></script>
</body>
</html>