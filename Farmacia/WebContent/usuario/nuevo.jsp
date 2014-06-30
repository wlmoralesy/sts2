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
<script type="text/javascript" src="../jquery/jquery.validate.js"></script>
<script>
$(document).ready(function(){
	
	$("#dlgPersonal").dialog({
		width: 550,
		height: 360,
		modal: true,
		autoOpen: false,
		resizable: false,
		open : function() {$(".ui-dialog-titlebar").css('display', 'none');},
		close : function() {$(".ui-dialog-titlebar").css('display', '');}
	});
	
	$("#btnBPersonal").click(function(){dlgPersonal.open();});
	
	$('body').on('click', '.bPersonal', function(){
		$('#usu_per').val($(this).parent().find('.id').val());
		$('#usu_per_dat').val($(this).parent().parent().find('.dni').text() + ' - ' + $(this).parent().parent().find('.apellido').text() + ', ' + $(this).parent().parent().find('.nombre').text());
		dlgPersonal.close();
	});
	
});

var dlgPersonal = {open : function() {$("#dlgPersonal").dialog('open');},close : function() {$("#dlgPersonal").dialog('close');}};

</script>
<title>Farmacia</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="registrar.html" method="post">
			<span class="tBlack22">Usuario: <c:choose><c:when test="${requestScope.usuario == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../usuario.html" class="button">Regresar</a>
			<br><br>
			<c:choose>
			<c:when test="${requestScope.usuario == null}"><input id="usu_id" name="usu_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="usu_id" name="usu_id" type="hidden" value="${requestScope.usuario.id_usuario}"></c:otherwise>
			</c:choose>						
			<table class="registro">	
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">Personal</td>
					<td>
						<input id="usu_per" name="usu_per" type="hidden" value="${requestScope.usuario.id_personal.id_personal}">
						<c:choose>
						<c:when test="${requestScope.usuario == null}">
						<input id="usu_per_dat" type="text" readonly="readonly" class="grande" required>&nbsp;<input id="btnBPersonal" class="button" type="button" value="O" title="Buscar personal">
						</c:when>
						<c:otherwise>
						<input type="text" readonly="readonly" class="grande" value="${requestScope.usuario.id_personal.dni_personal} - ${requestScope.usuario.id_personal.apellido_personal} ${requestScope.usuario.id_personal.nombre_personal}">
						</c:otherwise>
						</c:choose>						
					</td>
				</tr>
				<tr>
					<td class="linea1">Usuario</td>
					<td>
						<c:choose>
						<c:when test="${requestScope.usuario == null}">
						<input id="usu_usu" name="usu_usu" type="text" class="mediano" value="${requestScope.usuario.nombre_usuario}" required>
						</c:when>
						<c:otherwise>
						<input type="text" class="mediano" disabled="disabled" value="${requestScope.usuario.nombre_usuario}">
						</c:otherwise>
						</c:choose>						
					</td>
				</tr>
				<tr>
					<td>Contrase&#241;a</td>
					<td><input id="usu_con" name="usu_con" type="password" class="mediano" value="${requestScope.usuario.contrasena_usuario}" required></td>
				</tr>
				<tr>
					<td>Tipo</td>
					<td>
						<select id="usu_tip" name="usu_tip" class="grande">
							<c:forEach var="ut" items="${requestScope.usuario_tipo}">								
								<c:choose>
								<c:when test="${ut.id_usuario_tipo == requestScope.usuario.id_usuario_tipo.id_usuario_tipo}"><option selected="selected" value="${ut.id_usuario_tipo}">${ut.nombre_usuario_tipo}</option></c:when>
								<c:otherwise><option value="${ut.id_usuario_tipo}">${ut.nombre_usuario_tipo}</option></c:otherwise>
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
	<div id="dlgPersonal" style="display: none;">
		<span class="tBlack22">Listado de Personal: Buscar</span>&nbsp;<input id="txt-search" type="text">
		<select id="items-per-page" style="display: none"><option value="6" selected="selected">6</option></select>
		
		<table id="lis_mantenimiento" class="listado" style="width:100%">
		 	<tfoot>
			    <tr>
			     	<td colspan="5">
			     		<div id="paginator" align="center"><div style="font-size:11px; text-align:right;" class="lfooter">Pág. <b>1</b> de <b>1</b></div></div>
			     	</td>
			    </tr>
			</tfoot>
			<thead>
				<tr>
					<td></td>			
					<td>DNI</td>
					<td>Nombre</td>
					<td>Apellido</td>
					<td>Cargo</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${requestScope.lPersonal}">
				<tr>
					<td>
						<input class="button bPersonal" type="button" value="O">
						<input class="id" type="hidden" value="${p.id_personal}">
					</td>
					<td class="dni">${p.dni_personal}</td>
					<td class="apellido">${p.apellido_personal}</td>
					<td class="nombre">${p.nombre_personal}</td>
					<td class="cargo">${p.id_cargo.nombre_cargo}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
</div>
</body>
</html>





