<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="../css/estilos.css" />
<link type="text/css" rel="stylesheet" href="../css/menu.css" />
<script type="text/javascript" src="../jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../jquery/additional-methods.js"></script>
<title>Farmacia - Comisi&#243;n</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="registrar.html" method="post">
			<span class="tBlack22">Comisi&#243;n: <c:choose><c:when test="${requestScope.comision == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../comision.html" class="button">Regresar</a>
			<br><br>
			<c:choose>
			<c:when test="${requestScope.comision == null}"><input id="com_id" name="com_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="com_id" name="com_id" type="hidden" value="${requestScope.comision.id_comision}"></c:otherwise>
			</c:choose>						
			<table class="registro">	
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">Tipo</td>
					<td>
						<select id="com_tip" name="com_tip" class="grande">
							<c:forEach var="ct" items="${requestScope.comision_tipo}">
								<c:choose>
								<c:when test="${ct.id_comision_tipo == requestScope.comision.id_comision_tipo.id_comision_tipo}"><option selected="selected" value="${ct.id_comision_tipo}">${ct.nombre_comision_tipo}</option></c:when>
								<c:otherwise><option value="${ct.id_comision_tipo}">${ct.nombre_comision_tipo}</option></c:otherwise>
								</c:choose>								
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Cantidad</td>
					<td><input id="com_can" name="com_can" type="text" class="chico" value="${requestScope.comision.cantidad_comision}" required></td>
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