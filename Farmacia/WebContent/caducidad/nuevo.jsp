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
<script>
$(document).ready(function(){
	
	$("#dlgProducto").dialog({
		width: 600,
		height: 350,
		modal: true,
		autoOpen: false,
		resizable: false,
		open : function() {$(".ui-dialog-titlebar").css('display', 'none');},
		close : function() {$(".ui-dialog-titlebar").css('display', '');}
	});

	$("#agregarLineaCaducidad").click(function(){dlgProducto.open();});
	
	$(".bProducto").click(function(){
		var id = $(this).parent().find('.id').val();
		var nombre = $(this).parent().parent().find('.producto').text();
		var precio = $(this).parent().parent().find('.stock').text();
		var iTotal = $("#tot_line").val();
		
		var iCantidad = '<input type="text" id="can'+iTotal+'" name="can'+iTotal+'" value="0" class="cantidadProducto numericOnly chico cc2">';
		var iProducto = '<input type="hidden" id="pro'+iTotal+'" name="pro'+iTotal+'" value="'+id+'">';
		var xProducto = '<input type="text" id="xpro'+iTotal+'" readonly="readonly" style="width:435px;" class="noInput" value="'+nombre+'">';
		var iStock = '<input type="text" id="stk'+iTotal+'" name="stk'+iTotal+'" readonly="readonly" value="'+precio+'" class="stockProducto chico cc2 noInput">';
		var iTotal = '<input type="text" id="tot'+iTotal+'" name="tot'+iTotal+'"readonly="readonly" value="0" class="stockFinalProducto chico cc2 noInput">';
		var linea = '<tr><td>'+iCantidad+'</td><td>'+iProducto+xProducto+'</td><td>'+iStock+'</td><td>'+iTotal+'</td></tr>';
		
		$('#lineaCaducidad').append(linea);
		
		$("#tot_line").val($("#tot_line").val()*1+1);
		
		dlgProducto.close();
		
		$(this).parent().parent().hide();
		
	});
	
	$('body').on('keyup', '.cantidadProducto',function(){
		var sum = 0;
		var can = $(this).val();
		var stk = $(this).parent().parent().find('.stockProducto').val();
		$(this).parent().parent().find('.stockFinalProducto').val(stk*1-can*1);
	});
	
	$('body').on('keypress', '.numericOnly', function (e) {
	    if (String.fromCharCode(e.keyCode).match(/[^0-9]/g)) return false;
	});
	
	
});

var dlgProducto = {open : function() {$("#dlgProducto").dialog('open');},close : function() {$("#dlgProducto").dialog('close');}};

</script>
<title>Farmacia - Productos Vencidos</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="registrar.html" method="post">
			<span class="tBlack22">Productos vencidos: <c:choose><c:when test="${requestScope.venta == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../caducidad.html" class="button">Regresar</a>
			<br><br>
			<c:choose>
			<c:when test="${requestScope.caducidad == null}"><input id="cad_id" name="cad_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="cad_id" name="cad_id" type="hidden" value="${requestScope.caducidad.id_caducidad}"></c:otherwise>
			</c:choose>						
			<table class="registro">	
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>		
			</table>
			<table class="registro2">
				<thead>
					<tr>
						<td class="chico">Cant.</td>
						<td>Producto</td>
						<td class="chico">Stock</td>
						<td class="chico">N.Stock</td>
					</tr>
				</thead>
				<tbody id="lineaCaducidad"></tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<input id="agregarLineaCaducidad" type="button" value="Agregar producto" class="button">
							<input id="tot_line" name="tot_line" type="hidden" value="0">
						</td>
					</tr>
				</tfoot>
			</table>
			<table class="registro2">
				<tr>
					<td class="linea2">Descripci&#243;n<br>&nbsp;</td>
					<td>
						<textarea id="cad_des" name="cad_des"></textarea>
					</td>
				</tr>
				<tr>
					<td class="linea1"></td>
					<td align="right">
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
	<div id="dlgProducto" style="display: none;">
		<span class="tBlack22">Listado de Productos: Buscar</span>&nbsp;<input id="txt-search" type="text">
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
					<td>Producto</td>
					<td>Tipo</td>
					<td>Stock</td>
					<td>Vencimiento</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${requestScope.lProducto}">
				<tr>
					<td>
						<input class="button bProducto" type="button" value="O">
						<input class="id" type="hidden" value="${p.id_producto}">
					</td>
					<td class="producto">${p.nombre_producto}</td>
					<td>${p.id_producto_tipo.nombre_producto_tipo}</td>
					<td class="stock">${p.stock_producto}</td>
					<td>${p.fecha_vencimiento_producto}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</div>
</body>
</html>