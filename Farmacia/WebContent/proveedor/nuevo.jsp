<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="../css/estilos.css" />
<link type="text/css" rel="stylesheet" href="../css/menu.css" />
<script type="text/javascript" src="../jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../jquery/jquery.validate.js"></script>
<title>Farmacia - Proveedor</title>
<c:if test="${requestScope.proveedor != null}">
<script>window.onload = function(){$('input:radio[name="pro_lab"][value="${requestScope.proveedor.laboratorio}"]').attr('checked',true);}</script>
</c:if>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="registrar.html" method="post">
			<span class="tBlack22">Proveedor: <c:choose><c:when test="${requestScope.proveedor == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../proveedor.html" class="button">Regresar</a>
			<br><br>
			<c:choose>
			<c:when test="${requestScope.proveedor == null}"><input id="pro_id" name="pro_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="pro_id" name="pro_id" type="hidden" value="${requestScope.proveedor.id_proveedor}"></c:otherwise>
			</c:choose>						
			<table class="registro">	
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">Ruc</td>
					<td><input id="pro_ruc" name="pro_ruc" type="text" class="mediano" value="${requestScope.proveedor.ruc_proveedor}" maxlength="11" required></td>
				</tr>
				<tr>
					<td>Nombre</td>
					<td><input id="pro_nom" name="pro_nom" type="text" class="grande" value="${requestScope.proveedor.nombre_proveedor}" required></td>
				</tr>
				<tr>
					<td>Telefono</td>
					<td><input id="pro_tel" name="pro_tel" type="text" class="mediano" value="${requestScope.proveedor.telefono_proveedor}" required></td>
				</tr>
				<tr>
					<td>Direcci&#243;n</td>
					<td><input id="pro_dir" name="pro_dir" type="text" class="grande" value="${requestScope.proveedor.direccion_proveedor}" required></td>
				</tr>
				<tr>
					<td>Laboratorio</td>
					<td>
						<input id="pro_lab" name="pro_lab" type="radio" value="true" checked="checked">SI
						<input id="pro_lab" name="pro_lab" type="radio" value="false">NO
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
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