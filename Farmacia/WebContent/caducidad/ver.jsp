<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="../css/estilos.css" />
<link type="text/css" rel="stylesheet" href="../css/menu.css" />
<link type="text/css" rel="stylesheet" href="../css/smoothness/jquery-ui-1.10.3.custom.css" />
<script type="text/javascript" src="../jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../jquery/jquery-ui-1.10.3.custom.js"></script>
<title>Farmacia - Productos Vencidos</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="" method="post">
			<span class="tBlack22">Productos Vencidos: Ver</span> <a href="../caducidad.html" class="button">Regresar</a>
			<br><br>
			<table class="registro2">	
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">Local</td>
					<td>${requestScope.caducidad.id_local.nombre_local}</td>
				</tr>
				<tr>
					<td>Responsable</td>
					<td>${requestScope.caducidad.id_personal.dni_personal} - ${requestScope.caducidad.id_personal.nombre_personal} ${requestScope.caducidad.id_personal.apellido_personal} </td>
				</tr>
				<tr>
					<td>Estado</td>
					<td>${requestScope.caducidad.id_caducidad_estado.nombre_caducidad_estado}</td>
				</tr>					
			</table>
			<br>
			<table class="registro2">
				<thead>
					<tr>
						<td class="chico">Cant.</td>
						<td>Producto</td>
					</tr>
				</thead>
				<tbody id="lineaVenta">
					<c:forEach var="cd" items="${requestScope.caducidad_detalle}">
					<tr>
						<td>${cd.cantidad_caducidad_detalle}</td>
						<td>${cd.id_producto.nombre_producto}</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<br>
							<b>Informaci&#243;n adicional:</b><br>
							<c:choose>
							<c:when test="${not empty requestScope.caducidad.descripcion_caducidad}">${requestScope.caducidad.descripcion_caducidad}</c:when>
							<c:otherwise>No se agrego informaci&#243;n adicional</c:otherwise>
							</c:choose>
														
						</td>
					</tr>
				</tfoot>
			</table>			
		</form>	
	</div>
	<div class="footer">
		<jsp:include page="../menu/footer.html"></jsp:include>
	</div>
</div>
</body>
</html>