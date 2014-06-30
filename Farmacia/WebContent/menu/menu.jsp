<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id='cssmenu'>
	<ul><!-- active -->
		<li class='has-sub' style='<c:if test="${sessionScope.usuario_pu[0].visible_permiso_usuario==false}">display:none;</c:if>'><a href='/Farmacia/inicio.html'><div style="width: 61px;height:49px;"><img src="/Farmacia/img/inicio.png" alt="inicio" style="position: absolute;margin-top: 16px;">&nbsp;</div></a>
		<ul>
			<li class='last'><a href='/Farmacia/cerrar.html'><span>Cerrar sesi&#243;n</span></a></li>
			</ul></li>
		<li class='has-sub' style='<c:if test="${sessionScope.usuario_pu[1].visible_permiso_usuario==false}">display:none;</c:if>'><a href='#'><div style="width: 124px;height:49px;"><img src="/Farmacia/img/mantenimiento.png" alt="mantenimiento" style="position: absolute;margin-top: 16px;">&nbsp;</div></a>
			<ul>
				<li class='has-sub'><a href='/Farmacia/personal.html'><span>Personal</span></a>
					<ul>
						<li><a href='/Farmacia/personal.html'><span>Registro</span></a></li>
						<li class='last'><a href='/Farmacia/personal/cargo.html'><span>Cargo</span></a></li>
					</ul></li>
				<li class='has-sub'><a href='/Farmacia/usuario.html'><span>Usuario</span></a>
					<ul>
						<li><a href='/Farmacia/usuario.html'><span>Registro</span></a></li>
						<li><a href='/Farmacia/usuario/tipo.html'><span>Tipo</span></a></li>
					</ul></li>				
				<li><a href='/Farmacia/proveedor.html'><span>Proveedor</span></a></li>
				<li><a href='/Farmacia/cliente.html'><span>Cliente</span></a></li>
				<li class='has-sub'><a href='/Farmacia/producto.html'><span>Producto</span></a>
					<ul>
						<li><a href='/Farmacia/producto.html'><span>Registro</span></a></li>
						<li class='last'><a href='/Farmacia/producto/tipo.html'><span>Tipo</span></a></li>
					</ul></li>
				<li><a href='/Farmacia/comision.html'><span>Comisi&#243;n</span></a></li>
			</ul>
		</li>
		<li class='has-sub' style='<c:if test="${sessionScope.usuario_pu[2].visible_permiso_usuario==false}">display:none;</c:if>'><a href='#'><div style="width: 86px;height:49px;"><img src="/Farmacia/img/procesos.png" alt="prcesos" style="position: absolute;margin-top: 16px;">&nbsp;</div></a>
			<ul>
				<li><a href='/Farmacia/venta.html'><span>Ventas</span></a></li>
				<li class='last'><a href='/Farmacia/caducidad.html'><span>Productos vencidos</span></a></li>
			</ul>
		</li>
		<li class='has-sub last' style='<c:if test="${sessionScope.usuario_pu[3].visible_permiso_usuario==false}">display:none;</c:if>'><a href='#'><div style="width: 79px;height:49px;"><img src="/Farmacia/img/consultas.png" alt="consultas" style="position: absolute;margin-top: 15px;">&nbsp;</div></a>
			<ul>
				<li><a href='/Farmacia/reporte/cierre.html'><span>Cierre de Caja</span></a></li>
				<li class='last'><a href='/Farmacia/reporte/comision.html'><span>Reporte de Comisiones</span></a></li>
			</ul>
		</li>
	</ul>
	<div class="menu_usuario" style="float: right;width: 350px;height: 42px; text-align: right; color:#fff; margin-right: 8px;margin-top:8px; font-size: 14px;">
		<b>Local:</b> ${sessionScope.usuario.id_personal.id_local.nombre_local}<br/>
		<b>Personal:</b> ${sessionScope.usuario.id_personal.nombre_personal}<br/>
	</div>
</div>


