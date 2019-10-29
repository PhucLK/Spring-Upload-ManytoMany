<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Detail Product</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<h1>Detail Product</h1>

		<div class="row">

			<table class="table table-hover">
				<thead class="thead-light">
					<tr>
						<th>ID</th>
						<th style="text-align: left !important;">Product</th>
						<th style="text-align: left !important;">Decription</th>
						<th>Price</th>
					</tr>
				</thead>

				<tr>
					<th>${product.id}</th>
					<td style="text-align: left !important;">${product.name}</td>
					<td style="text-align: left !important;">${product.description}</td>
					<td>${product.price}</td>

				</tr>
			</table>

		</div>
	</div>
</body>
</html>