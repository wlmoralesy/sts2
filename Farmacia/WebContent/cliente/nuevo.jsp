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
<title>Farmacia - Cliente</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<span class="tBlack22">Personal: <c:choose><c:when test="${requestScope.cliente == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../cliente.html" class="button">Regresar</a>
		<br><br>
		<form action="registrar.html" method="post">			
			<c:choose>
			<c:when test="${requestScope.cliente == null}"><input id="cli_id" name="cli_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="cli_id" name="cli_id" type="hidden" value="${requestScope.cliente.id_cliente}"></c:otherwise>
			</c:choose>			
			<table class="registro">
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">RUC</td>
					<td><input id="cli_ruc" name="cli_ruc" type="text" class="mediano" value="${requestScope.cliente.ruc_cliente}" maxlength="11" required></td>
				</tr>
				<tr>
					<td>Nombre(s)</td>
					<td><input id="cli_nom" name="cli_nom" type="text" class="grande" value="${requestScope.cliente.nombre_cliente}" required></td>
				</tr>
				<tr>
					<td>Apellido(s)</td>
					<td><input id="cli_ape" name="cli_ape" type="text" class="grande" value="${requestScope.cliente.apellido_cliente}" required></td>
				</tr>
				<tr>
					<td>Direcci&#243;n</td>
					<td><input id="cli_dir" name="cli_dir" type="text" class="grande" value="${requestScope.cliente.direccion_cliente}" required></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="<c:choose><c:when test="${requestScope.cliente == null}">Registrar</c:when><c:otherwise>Actualizar</c:otherwise></c:choose>" class="button">
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