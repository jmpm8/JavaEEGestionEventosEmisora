<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--Importar la librería JSTL core--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS -->
<link rel="stylesheet" href="css/newportal.css">

<title>Lista de Eventos</title>
</head>
<body class="fondo">

	<div class="container generic">
		<div class="signup-form-container">

			<!-- form start -->
			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i>Sistema de inscripción a Eventos
				</h3>
			</div>
			<div class="form-row">
				<div class="col">
					<a href="ServletEventos?accion=volver">Volver</a>
				</div>
				<div class="col-11"></div>
			</div>

			<p></p>

			<hr class="separador">
			<div class="release">
				<span class="welcome">LISTADO DE EVENTOS</span>
				<div class="form-row tableHeaderAll">
					<div class="col">Id</div>
					<div class="col-2">Nombre</div>
					<div class="col-2">Descripcion</div>
					<div class="col">Lugar</div>
					<div class="col-2">Duración</div>
					<div class="col-2">Tipo</div>
					<div class="col-2">Asientos disponibles</div>
				</div>
				<c:forEach var="evento" items="${listaEventos}">
					<div class="form-row tableHeaderUser" style="font-size: small;">
						<div class="col">${evento.eventoId}</div>
						<div class="col-2">${evento.nombre}</div>
						<div class="col-2">${evento.descripcion}</div>
						<div class="col">${evento.lugar}</div>
						<div class="col-2">${evento.duracion}</div>
						<div class="col-2">${evento.tipoEvento}</div>
						<div class="col-2">${evento.asientosDisp}</div>
					</div>
				</c:forEach>
			</div>
			<div class="form-row">
				<div class="col error">
					<c:out value="${mensajeError}"></c:out>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
