<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="../../css/estilos.css" />
<link type="text/css" rel="stylesheet" href="../../css/menu.css" />
<link type="text/css" rel="stylesheet" href="../../css/smoothness/jquery-ui-1.10.3.custom.css" />
<script type="text/javascript" src="../../jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../../jquery/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="../../jquery/jquery.validate.js"></script>
<title>Farmacia - Usuario - Tipo</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="registrar.html" method="post">
			<span class="tBlack22">Tipo de Usuario: <c:choose><c:when test="${requestScope.usuario_tipo == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../tipo.html" class="button">Regresar</a>
			<br><br>					
			<c:choose>
			<c:when test="${requestScope.usuario_tipo == null}"><input id="usu_tip_id" name="usu_tip_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="usu_tip_id" name="usu_tip_id" type="hidden" value="${requestScope.usuario_tipo.id_usuario_tipo}"></c:otherwise>
			</c:choose>
			<table class="registro">
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">Nombre</td>
					<td><input id="usu_tip_nom" name="usu_tip_nom" type="text" class="grande" value="${requestScope.usuario_tipo.nombre_usuario_tipo}" required></td>
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
		<jsp:include page="../../menu/footer.html"></jsp:include>
	</div>
</div>
</body>
</html>