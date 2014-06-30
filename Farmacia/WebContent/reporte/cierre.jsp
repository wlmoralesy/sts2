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
<script>$(function(){$("#fecha").datepicker({ dateFormat: 'yy-mm-dd' });});</script>
<script>
$(document).ready(function(){
	
	$('body').on('click', '.vDetalle', function(){
		$("#detalle"+$(this).parent().find('.detalleNumero').val()).show();
	});
	
});
</script>
<title>Farmacia - Cierre de Caja</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>		
	</div>
	<div class="content">
		<span class="tBlack22">Reporte: Cierre de Caja</span>
		<br><br>
		<form action="cierre.html" method="post">
		<span class="tBlack22">Fecha</span>&nbsp;<input id="fecha" name="fecha" type="text" value="${requestScope.fecha}" style="width: 66px;">&nbsp;<input id="bCierre" type="submit" value="Buscar" class="button">
		</form>		
		<br>
		<table class="registro2">
			<thead>
				<tr>
					<td class="linea3">Turno</td>
					<td>Total Ventas</td>
					<td align="right">Monto Total</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="r" items="${requestScope.reporte}" varStatus="rs">
				<tr>
					<td>${r.turno}</td>
					<td align="center">${r.cantidad}</td>
					<td align="right">${r.total}</td>
					<td align="center">
						<input type="button" value="Detalle" class="button vDetalle">
						<input type="hidden" value="${rs.count}" class="detalleNumero">
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<table id="detalle${rs.count}" class="listado" style="display: none;">
							<thead>
								<tr>
									<td>Personal</td>
									<td>Comprobante</td>
									<td>Total Ventas</td>
									<td align="right">Monto Total</td>
								</tr>
							</thead>							
							<tbody>
								<c:forEach var="rd" items="${requestScope.reporte_detalle}">
								<c:if test="${r.turno == rd.turno}">
								<tr>
									<td>${rd.personal.apellido_personal}, ${rd.personal.nombre_personal}</td>
									<td>${rd.comprobante.nombre_comprobante}</td>
									<td align="center">${rd.cantidad}</td>
									<td align="right">${rd.total}</td>
								</tr>
								</c:if>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="3">Total</td>
									<td align="right">${r.total}</td>
								</tr>
							</tfoot>
						</table>
					</td>
				</tr>				
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="footer">
		<jsp:include page="../menu/footer.html"></jsp:include>
	</div>
</div>
</body>
</html>



