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
<script type="text/javascript" src="../jquery/jlinq.js"></script>
<script type="text/javascript" src="../jquery/listado/pagination.js"></script>
<title>Farmacia - Usuario - Tipo</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<span class="tBlack22">Tipo de Usuario:</span> <a href="tipo/nuevo.jsp" class="button"><img src="../img/mas.png" alt="mas">&nbsp;Nuevo</a>
		<br><br>		
		<span class="tBlack22">Buscar</span>&nbsp;<input id="txt-search" type="text">
		<select id="items-per-page" style="display: none"><option value="7" selected="selected">7</option></select>
		
		<table id="lis_mantenimiento" class="listado">
		 	<tfoot>
			    <tr>
			     	<td colspan="4">
			     		<div id="paginator" align="center"><div style="font-size:11px; text-align:right;" class="lfooter">Pág. <b>1</b> de <b>1</b></div></div>
			     	</td>
			    </tr>
			</tfoot>
			<thead>
				<tr>
					<td></td>			
					<td>Nombre</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ut" items="${requestScope.lUsuarioTipo}">
				<tr>
					<td>
						<form action="tipo.html" method="post">
							<input type="hidden" name="del_usu_tip_id" value="${ut.id_usuario_tipo}">
							<input type="submit" class="equis" value="">
						</form>
					</td>
					<td>${ut.nombre_usuario_tipo}</td>					
					<td>
						<form action="tipo/editar.html" method="post">
							<input type="submit" value="Editar"> 
							<input type="hidden" name="usu_tip_id" value="${ut.id_usuario_tipo}">
						</form>
					</td>
					<td>
						<form action="tipo/permisos.html" method="post">
							<input type="submit" title="Asignar permisos" value="Permisos" style='<c:if test="${ut.id_usuario_tipo == 1}">display:none;</c:if>'> 
							<input type="hidden" name="usu_tip_id" value="${ut.id_usuario_tipo}">
						</form>
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