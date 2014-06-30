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
<script>$(function(){
	$("#fecha1").datepicker({ dateFormat: 'yy-mm-dd' });
	$("#fecha2").datepicker({ dateFormat: 'yy-mm-dd' });
});</script>
<script>
$(document).ready(function(){
	
	$('body').on('click', '.vDetalle', function(){
		$("#detalle"+$(this).parent().find('.detalleNumero').val()).show();
	});
	
});
</script>
<title>Farmacia - Comisiones</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>		
	</div>
	<div class="content">
		<span class="tBlack22">Reporte: Comisiones</span>
		<br><br>
		<form action="comision.html" method="post">
		<span class="tBlack22">Fecha</span>&nbsp;<input id="fecha1" name="fecha1" type="text" value="${requestScope.fecha1}" style="width: 66px;">&nbsp;-&nbsp;<input id="fecha2" name="fecha2" type="text" value="${requestScope.fecha2}" style="width: 66px;">&nbsp;<input id="bCierre" type="submit" value="Buscar" class="button">
		</form>		
		<br>
		<table class="registro2 todo">
			<thead>
				<tr>
					<td>Personal</td>					
					<td>Fecha</td>
					<td align="right">Monto Total</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="r" items="${requestScope.reporte}" varStatus="rs">
				<tr>
					<td>${r.personal.apellido_personal}, ${r.personal.nombre_personal}</td>
					<td>${r.fecha}</td>
					<td align="right">${r.total}</td>
					<td align="center">
						<input type="button" value="Detalle" class="button vDetalle">
						<input type="hidden" value="${rs.count}" class="detalleNumero">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<table id="detalle${rs.count}" class="listado rcomision" style="display:none;">
							<thead>
								<tr>
									<td>Producto</td>
									<td class="grande2">Comision</td>
									<td class="mediano" align="right">(C)Ventas</td>
									<td class="mediano" align="right">(C)Productos</td>
									<td class="mediano" align="right">Precio</td>
									<td class="grande" align="right">Total</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="rd" items="${requestScope.reporte_detalle}">
								<c:if test="${r.personal.id_personal == rd.personal.id_personal && r.fecha == rd.fecha}">
								<tr>
									<td>${rd.producto.nombre_producto}</td>
									<td>${rd.comision.id_comision_tipo.nombre_comision_tipo} (${rd.comision.cantidad_comision})</td>
									<td align="right">${rd.ventas}</td>
									<td align="right">${rd.cantidad}</td>
									<td align="right">${rd.precio}</td>
									<td align="right">${rd.total}</td>
								</tr>
								</c:if>
							</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td align="right" colspan="5">Total</td>
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



