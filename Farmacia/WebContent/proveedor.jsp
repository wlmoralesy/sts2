<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/estilos.css" />
<link type="text/css" rel="stylesheet" href="css/menu.css" />
<link type="text/css" rel="stylesheet" href="css/smoothness/jquery-ui-1.10.3.custom.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="jquery/jlinq.js"></script>
<script type="text/javascript" src="jquery/listado/pagination.js"></script>
<title>Farmacia - Proveedor</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<span class="tBlack22">Proveedor:</span> <a href="proveedor/nuevo.html" class="button"><img src="img/mas.png" alt="mas">&nbsp;Nuevo</a>
		<br><br>
		<span class="tBlack22">Buscar</span>&nbsp;<input id="txt-search" type="text">
		<select id="items-per-page" style="display: none"><option value="7" selected="selected">7</option></select>
		
		<table id="lis_mantenimiento" class="listado">
			<tfoot>
			    <tr>
			     	<td colspan="8">
			     		<div id="paginator" align="center"><div style="font-size:11px; text-align:right;" class="lfooter">P�g. <b>1</b> de <b>1</b></div></div>
			     	</td>
			    </tr>
			</tfoot>
			<thead>
				<tr>
					<td></td>			
					<td>RUC</td>
					<td>Nombre(s)</td>
					<td>Telefono</td>
					<td>Direcci&#243;n</td>
					<td>Lab</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${requestScope.lProveedor}">
				<tr>
					<td>
						<form action="proveedor.html" method="post">
							<input type="hidden" name="del_pro_id" value="${p.id_proveedor}">
							<input type="submit" class="equis" value="">
						</form>
					</td>
					<td>${p.ruc_proveedor}</td>
					<td>${p.nombre_proveedor}</td>
					<td>${p.telefono_proveedor}</td>
					<td>${p.direccion_proveedor}</td>
					<td><c:choose><c:when test="${p.laboratorio==true}">SI</c:when><c:otherwise>NO</c:otherwise></c:choose></td>					
					<td>
						<form action="proveedor/editar.html" method="post">
							<input type="submit" value="Editar"> 
							<input type="hidden" name="pro_id" value="${p.id_proveedor}">
						</form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="footer">
		<jsp:include page="menu/footer.html"></jsp:include>
	</div>
</div>
</body>
</html>