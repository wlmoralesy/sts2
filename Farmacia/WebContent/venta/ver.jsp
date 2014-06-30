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
<title>Farmacia - Venta</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="" method="post">
			<span class="tBlack22">Venta: Ver</span> <a href="../venta.html" class="button">Regresar</a>
			<br><br>
			<table class="registro2">	
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">Comprobante</td>
					<td>${requestScope.venta.id_comprobante.nombre_comprobante}</td>
				</tr>
				<tr>
					<td>Venta</td>
					<td>${requestScope.venta.numero_venta}</td>
				</tr>
				<tr>
					<td>Cliente</td>
					<td>${requestScope.venta.id_cliente.ruc_cliente} - ${requestScope.venta.id_cliente.nombre_cliente} ${requestScope.venta.id_cliente.apellido_cliente}</td>
				</tr>					
			</table>
			<table class="registro2">
				<thead>
					<tr>
						<td class="chico">Cant.</td>
						<td>Producto</td>
						<td class="chico">Precio</td>
						<td class="chico2">Total</td>
					</tr>
				</thead>
				<tbody id="lineaVenta">
					<c:set var="total" value="0" />
					<c:forEach var="vd" items="${requestScope.venta_detalle}">
					<tr>
						<td>${vd.cantidad_venta_detalle}</td>
						<td>${vd.id_producto.nombre_producto}</td>
						<td align="right">${vd.precio_unitario_venta_detalle}</td>
						<td align="right">${vd.precio_total_venta_detalle}</td>
					</tr>
					<c:set var="total" value="${total + vd.precio_total_venta_detalle}" />
					</c:forEach>
				</tbody>
				<tfoot>					
					<tr>
						<td align="right" colspan="3"><b>TOTAL</b></td>
						<td align="right"><b>${total}</b></td>
					</tr>
					<tr>
						<td colspan="4">
							<b>Informaci&#243;n adicional:</b><br>
							<c:choose>
							<c:when test="${not empty requestScope.venta.descripcion_venta}">${requestScope.venta.descripcion_venta}</c:when>
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