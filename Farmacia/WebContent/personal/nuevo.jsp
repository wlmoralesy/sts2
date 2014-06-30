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
<title>Farmacia</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<span class="tBlack22">Personal: <c:choose><c:when test="${requestScope.personal == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../personal.html" class="button">Regresar</a>
		<br><br>
		<form action="registrar.html" method="post">			
			<c:choose>
			<c:when test="${requestScope.personal == null}"><input id="per_id" name="per_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="per_id" name="per_id" type="hidden" value="${requestScope.personal.id_personal}"></c:otherwise>
			</c:choose>			
			<table class="registro">
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">DNI</td>
					<td><input id="per_dni" name="per_dni" type="text" class="mediano" value="${requestScope.personal.dni_personal}" maxlength="8" required></td>
				</tr>
				<tr>
					<td>Nombre(s)</td>
					<td><input id="per_nom" name="per_nom" type="text" class="grande" value="${requestScope.personal.nombre_personal}" required></td>
				</tr>
				<tr>
					<td>Apellido(s)</td>
					<td><input id="per_ape" name="per_ape" type="text" class="grande" value="${requestScope.personal.apellido_personal}" required></td>
				</tr>
				<tr>
					<td>Telefono</td>
					<td><input id="per_tel" name="per_tel" type="text" class="mediano" value="${requestScope.personal.telefono_personal}" ></td>
				</tr>
				<tr>
					<td>Celular</td>
					<td><input id="per_cel" name="per_cel" type="text" class="mediano" value="${requestScope.personal.celular_personal}" ></td>
				</tr>
				<tr>
					<td>Cargo</td>
					<td>
						<select id="per_car" name="per_car" class="grande">
							<c:forEach var="c" items="${requestScope.cargo}">
							<c:choose>
							<c:when test="${c.id_cargo == requestScope.personal.id_cargo.id_cargo}"><option selected="selected" value="${c.id_cargo}">${c.nombre_cargo}</option></c:when>
							<c:otherwise><option value="${c.id_cargo}">${c.nombre_cargo}</option></c:otherwise>
							</c:choose>							
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Local</td>
					<td>
						<select id="per_loc" name="per_loc" class="grande">
							<c:forEach var="l" items="${requestScope.local}">
							<c:choose>
							<c:when test="${l.id_local == requestScope.personal.id_local.id_local}"><option selected="selected" value="${l.id_local}">${l.nombre_local}</option></c:when>
							<c:otherwise><option value="${l.id_local}">${l.nombre_local}</option></c:otherwise>
							</c:choose>							
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="<c:choose><c:when test="${requestScope.personal == null}">Registrar</c:when><c:otherwise>Actualizar</c:otherwise></c:choose>" class="button">
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