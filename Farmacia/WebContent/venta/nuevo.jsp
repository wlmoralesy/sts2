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
<script type="text/javascript" src="../jquery/producto/pagination.js"></script>
<script type="text/javascript" src="../jquery/listado/pagination.js"></script>
<script type="text/javascript" src="../jquery/jquery.validate.js"></script>
<script>
$(document).ready(function(){
	
	$("#dlgCliente").dialog({
		width: 500,
		height: 340,
		modal: true,
		autoOpen: false,
		resizable: false,
		open : function() {$(".ui-dialog-titlebar").css('display', 'none');},
		close : function() {$(".ui-dialog-titlebar").css('display', '');}
	});
	
	$("#dlgProducto").dialog({
		width: 650,
		height: 420,
		modal: true,
		autoOpen: false,
		resizable: false,
		open : function() {$(".ui-dialog-titlebar").css('display', 'none');},
		close : function() {$(".ui-dialog-titlebar").css('display', '');}
	});
	
	$("#btnBCliente").click(function(){dlgCliente.open();});
	
	$("#btnECliente").click(function(){		
		$('.eCliente').removeAttr('readonly');
		$('.eCliente').val('');
		$('#ven_cli').val('0');
		$(this).hide();
	});
	
	$('body').on('click', '.bCliente', function(){
		$('#ven_cli').val($(this).parent().find('.id').val());
		$('#ven_cli_ruc').val($(this).parent().parent().find('.ruc').text());
		$('#ven_cli_nom').val($(this).parent().parent().find('.nombre').text());
		$('#ven_cli_ape').val($(this).parent().parent().find('.apellido').text());
		$('.eCliente').attr('readonly','readonly');		
		$("#btnECliente").show();
		dlgCliente.close();
	});
	
	$("#agregarLineaVenta").click(function(){dlgProducto.open();});
	
	$('body').on('click', '.bProducto', function(){
		var id = $(this).parent().find('.id').val();
		var nombre = $(this).parent().parent().find('.producto').text();
		var precio = $(this).parent().parent().find('.precio').text();
		var iTotal = $("#tot_line").val();
		
		var iCantidad = '<input type="text" id="can'+iTotal+'" name="can'+iTotal+'" value="0" class="cantidadProducto numericOnly chico cc2">';
		var iProducto = '<input type="hidden" id="pro'+iTotal+'" name="pro'+iTotal+'" value="'+id+'">';
		var xProducto = '<input type="text" id="xpro'+iTotal+'" readonly="readonly" style="width:435px;" class="noInput" value="'+nombre+'">';
		var iPrecio = '<input type="text" id="pre'+iTotal+'" name="pre'+iTotal+'" readonly="readonly" value="'+precio+'" class="precioProducto chico cc2 noInput">';
		var iTotal = '<input type="text" id="tot'+iTotal+'" name="tot'+iTotal+'"readonly="readonly" value="0.00" class="totalProducto chico cc2 noInput">';
		var linea = '<tr><td>'+iCantidad+'</td><td>'+iProducto+xProducto+'</td><td>'+iPrecio+'</td><td>'+iTotal+'</td></tr>';
		
		$('#lineaVenta').append(linea);
		
		$("#tot_line").val($("#tot_line").val()*1+1);
		
		dlgProducto.close();
		
		$(this).parent().parent().hide();
		
	});
	
	$('body').on('keyup', '.cantidadProducto', function(){
		var sum = 0;
		var can = $(this).val();
		var pri = $(this).parent().parent().find('.precioProducto').val();
		$(this).parent().parent().find('.totalProducto').val(can*pri*1);		
		$(".totalProducto").each(function() {
		    var value = $(this).val();		
		    if(!isNaN(value) && value.length != 0) { sum += parseFloat(value); }
		});		
		$("#ven_tot").val(sum);		
	});
	
	$('body').on('keypress', '.numericOnly', function (e) {
	    if (String.fromCharCode(e.keyCode).match(/[^0-9]/g)) return false;
	});
	
});

var dlgCliente = {open : function() {$("#dlgCliente").dialog('open');},close : function() {$("#dlgCliente").dialog('close');}};
var dlgProducto = {open : function() {$("#dlgProducto").dialog('open');},close : function() {$("#dlgProducto").dialog('close');}};

</script>
<title>Farmacia - Venta</title>
</head>
<body>
<div class="body">
	<div class="head"></div>
	<div class="menu">
		<jsp:include page="../menu/menu.jsp"></jsp:include>
	</div>
	<div class="content">
		<form action="registrar.html" method="post">
			<span class="tBlack22">Venta: <c:choose><c:when test="${requestScope.venta == null}">Nuevo</c:when><c:otherwise>Actualizar</c:otherwise></c:choose></span> <a href="../venta.html" class="button">Regresar</a>
			<br><br>
			<c:choose>
			<c:when test="${requestScope.venta == null}"><input id="ven_id" name="ven_id" type="hidden" value="0"></c:when>
			<c:otherwise><input id="ven_id" name="ven_id" type="hidden" value="${requestScope.venta.id_venta}"></c:otherwise>
			</c:choose>						
			<table class="registro">	
				<tr>
					<td colspan="2">${requestScope.resultMessage}</td>
				</tr>
				<tr>
					<td class="linea1">Comprobante</td>
					<td>
						<c:forEach var="c" items="${requestScope.lComprobante}">
						<input id="ven_con${c.id_comprobante}" type="radio" name="ven_com" checked="checked" value="${c.id_comprobante}">${c.nombre_comprobante}					
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>Cliente<br>&nbsp;</td>
					<td>
						<input id="ven_cli" type="hidden" name="ven_cli" value="0">
						<input id="ven_cli_ruc" name="ven_cli_ruc" type="text" class="mediano eCliente" maxlength="11" required>&nbsp;<input id="btnBCliente" class="button" type="button" value="O" title="Buscar cliente">&nbsp;<input id="btnECliente" style="display: none;" class="button" type="button" value="X" title="Eliminar cliente buscado"><br>
						<input id="ven_cli_nom" name="ven_cli_nom" type="text" class="mediano eCliente" required>&nbsp;<input id="ven_cli_ape" name="ven_cli_ape" type="text" class="mediano eCliente" required>
					</td>
				</tr>					
			</table>
			<table class="registro2">
				<thead>
					<tr>
						<td class="chico">Cant.</td>
						<td>Producto</td>
						<td class="chico">Precio</td>
						<td class="chico">Total</td>
					</tr>
				</thead>
				<tbody id="lineaVenta"></tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input id="agregarLineaVenta" type="button" value="Agregar producto" class="button">
							<input id="tot_line" name="tot_line" type="hidden" value="0">
						</td>
						<td align="right"><b>TOTAL</b></td>
						<td align="right"><input id="ven_tot" name="ven_tot" value="0.00" class="chico cc2 noInput" readonly="readonly"></td>
					</tr>
				</tfoot>
			</table>
			<table class="registro2">
				<tr>
					<td class="linea2">Informaci&#243;n Adicional<br>&nbsp;</td>
					<td>
						<textarea id="ven_des" name="ven_des"></textarea>
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
	<div id="dlgCliente" style="display: none;">
		<span class="tBlack22">Listado de Clientes: Buscar</span>&nbsp;<input id="txt-search" type="text" style="width:150px">
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
					<td>Documento</td>
					<td>Nombre(s)</td>
					<td>Apellido(s)</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${requestScope.lCliente}">
				<tr>
					<td>
						<input class="button bCliente" type="button" value="O">
						<input class="id" type="hidden" value="${c.id_cliente}">
					</td>
					<td class="ruc">${c.ruc_cliente}</td>
					<td class="nombre">${c.nombre_cliente}</td>
					<td class="apellido">${c.apellido_cliente}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
	<div id="dlgProducto" style="display: none;">
		<span class="tBlack22">Listado de Productos: Buscar</span>&nbsp;<input id="txt-searchp" type="text">
		<select id="items-per-pagep" style="display: none"><option value="8" selected="selected">8</option></select>
		<table id="lis_producto" class="listado" style="width:100%">
			 <tfoot>
			    <tr>
			     	<td colspan="7">
			     		<div id="paginatorp" align="center"><div style="font-size:11px; text-align:right;" class="lfooter">Pág. <b>1</b> de <b>1</b></div></div>
			     	</td>
			    </tr>
			</tfoot>
			<thead>
				<tr>
					<td class="mini"></td>			
					<td>Producto</td>
					<td class="mediano">Tipo</td>
					<td>Sustancia</td>
					<td>Comisi&#243;n</td>
					<td>Stock</td>
					<td>Precio</td>
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
					<td>${p.sustancia_producto}</td>
					<td>${p.id_comision.id_comision_tipo.nombre_comision_tipo} (${p.id_comision.cantidad_comision})</td>
					<td align="right" class="stock">${p.stock_producto}</td>
					<td align="right" class="precio">${p.precio_producto}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>