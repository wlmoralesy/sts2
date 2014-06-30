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
<title>Farmacia - Usuario - Tipo (Permisos)</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="permiso.html" method="post">
			<span class="tBlack22">Tipo de Usuario: Permisos</span> <a href="../tipo.html" class="button">Regresar</a>
			<br><br>			
			<input id="r_usu_tip_id" name="r_usu_tip_id" type="hidden" value="${requestScope.r_usu_tip_id}">
			<table class="registro">
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<c:forEach var="p" items="${requestScope.permiso}" varStatus="pc">
				<tr>
					<td colspan="2">						
						<input id="per_${p.id_permiso}" name="per_${p.id_permiso}" type="checkbox" <c:if test="${requestScope.permiso_usuario[pc.count-1].visible_permiso_usuario==true || pc.count==1}">checked=checked</c:if> style="<c:if test='${pc.count==1}'>display:none;</c:if>" >${p.nombre_permiso}
					</td>
				</tr>
				</c:forEach>
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