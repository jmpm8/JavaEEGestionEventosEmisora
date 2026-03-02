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
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS -->
<link rel="stylesheet" href="./css/newportal.css">

<title>Busqueda de Eventos</title>
</head>

<body class="fondo">

	<div class="container">
		<div class="signup-form-container">

			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i>Sistema de inscripción a Eventos
				</h3>
				<h2>&nbsp;</h2>
			</div>
			<div class="form-row">
				<div class="col">
					<a href="ServletEventos?accion=logout">Cerrar sesión</a>
				</div>
			</div>
			<p></p>
			<div class="form-row">
				<div class="col-1"></div>
				<div class="col-10">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="datos-usuario" autocomplete="off" class="credentials">
									<div class="input-group">
 										 Nombre: <c:out value="${sessionScope.usuarioLogueado.nombre}"></c:out> <c:out value="${sessionScope.datosUsuario.apellido}"></c:out>
 									</div>
									<div class="input-group">
 										 Email: <c:out value="${sessionScope.usuarioLogueado.email}"></c:out>
 									</div>
									<div class="input-group">
  										 Dirección: <c:out value="${sessionScope.usuarioLogueado.direccion}"></c:out>
 								</div>
									<div class="input-group">
 										 Teléfono: <c:out value="${sessionScope.usuarioLogueado.telefono}"></c:out>
 									</div>
								</form>
							</div>

						</div>
					</div>
				</div>

				<div class="col-1"></div>
			</div>


			<div class="form-row">
				<div class="col-1"></div>
				<div class="col-10">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="busqueda-usuarios" autocomplete="off"
									class="credentials" method="post" action="ServletEventos">
									<input type="hidden" name="accion" value="buscar">
									Lista de Eventos
									<div class="input-group">
 										 <input name="criterio" type="text" class="form-control fontAwesome"
										placeholder="&#xf007; Nombre/Descripción del evento">
										<span class="input-group-btn">
   											 <button type="submit" class="btn btn-info">Buscar...</button>
										</span>
									</div>
								</form>
							</div>

						</div>
					</div>
				</div>

				<div class="col-1"></div>
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
