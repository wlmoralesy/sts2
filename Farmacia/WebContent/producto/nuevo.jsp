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
<script type="text/javascript" src="../jquery/jquery.validate.js"></script>
<script type="text/javascript" src="../jquery/additional-methods.js"></script>
<script>$(function(){$("#pro_ven").datepicker({ dateFormat: 'yy-mm-dd' });});</script>
<script>
</script>
<title>Farmacia - Producto</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form id="formulario" action="registrar.html" method="post">
			<span class="tBlack22">Producto: <c:choose><c:when test="${requestScope.producto == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../producto.html" class="button">Regresar</a>
			<br><br>
			<c:choose>
			<c:when test="${requestScope.producto == null}"><input id="pro_id" name="pro_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="pro_id" name="pro_id" type="hidden" value="${requestScope.producto.id_producto}"></c:otherwise>
			</c:choose>	
			<table class="registro">
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td>Nombre</td>
					<td><input id="pro_nom" name="pro_nom" type="text" value="${requestScope.producto.nombre_producto}" required></td>
				</tr>
				<tr>
					<td>Tipo</td>
					<td>
						<select id="pro_tip" name="pro_tip">
							<c:forEach var="pt" items="${requestScope.producto_tipo}">
							<c:choose>
							<c:when test="${pt.id_producto_tipo == requestScope.producto.id_producto_tipo.id_producto_tipo}"><option selected="selected" value="${pt.id_producto_tipo}">${pt.nombre_producto_tipo}</option></c:when>
							<c:otherwise><option value="${pt.id_producto_tipo}">${pt.nombre_producto_tipo}</option></c:otherwise>
							</c:choose>							
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Descripci&#243;n</td>
					<td><textarea id="pro_des" name="pro_des" class="grande" required>${requestScope.producto.descripcion_producto}</textarea></td>
				</tr>
				<tr>
					<td>Precio</td>
					<td><input id="pro_pri" name="pro_pri" type="text" class="chico" value="${requestScope.producto.precio_producto}" required></td>
				</tr>
				<tr>
					<td>Sustancia</td>
					<td><input id="pro_sus" name="pro_sus" type="text" class="grande" value="${requestScope.producto.sustancia_producto}" required></td>
				</tr>
				<tr>
					<td>Presentaci&#243;n</td>
					<td><input id="pro_pre" name="pro_pre" type="text" class="grande" value="${requestScope.producto.presentacion_producto}" required></td>
				</tr>
				<tr>
					<td>Vencimiento</td>
					<td><input id="pro_ven" name="pro_ven" type="text" class="mediano" value="${requestScope.producto.fecha_vencimiento_producto}" required></td>
				</tr>
				<tr>
					<td>Stock</td>
					<td><input id="pro_sto" name="pro_sto" type="text" class="chico" value="${requestScope.producto.stock_producto}" required></td>
				</tr>
				<tr>
					<td>Local</td>
					<td>
						<select id="pro_loc" name="pro_loc" class="grande">
							<c:forEach var="l" items="${requestScope.local}">							
							<c:choose>
							<c:when test="${l.id_local == requestScope.producto.id_local.id_local}"><option selected="selected" value="${l.id_local}">${l.nombre_local}</option></c:when>
							<c:otherwise><option value="${l.id_local}">${l.nombre_local}</option></c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Comisi&#243;n</td>
					<td>
						<select id="pro_com" name="pro_com" class="grande">
							<c:forEach var="c" items="${requestScope.comision}">
							<c:choose>
							<c:when test="${c.id_comision == requestScope.producto.id_comision.id_comision}"><option selected="selected" value="${c.id_comision}">${c.id_comision_tipo.nombre_comision_tipo} (${c.cantidad_comision})</option></c:when>
							<c:otherwise><option value="${c.id_comision}">${c.id_comision_tipo.nombre_comision_tipo} (${c.cantidad_comision})</option></c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Proveedor</td>
					<td>
						<select id="pro_pro" name="pro_pro" class="grande">
							<c:forEach var="pr" items="${requestScope.proveedor}">
							<c:choose>
							<c:when test="${pr.id_proveedor == requestScope.producto.id_proveedor.id_proveedor}"><option selected="selected" value="${pr.id_proveedor}">${pr.nombre_proveedor}</option></c:when>
							<c:otherwise><option value="${pr.id_proveedor}">${pr.nombre_proveedor}</option></c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="Registar" class="button">
						<input type="reset" value="Limpiar" class="button">			
					</td>
				</tr>
			</table>
		</form>	
	</div>
	<div class="footer">
		<jsp:include page="../menu/footer.html"></jsp:include>
	</div>
</div>
</body>
</html>