package pe.eureka.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.edutec.app.entity.Cliente;
import pe.edutec.app.entity.Comprobante;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.Producto;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.entity.Venta;
import pe.edutec.app.entity.VentaDetalle;
import pe.edutec.app.model.ClienteModel;
import pe.edutec.app.model.ComprobanteModel;
import pe.edutec.app.model.ProductoModel;
import pe.edutec.app.model.VentaDetalleModel;
import pe.edutec.app.model.VentaModel;

@WebServlet({ "/venta.html",  "/venta/nuevo.html", "/venta/registrar.html", "/venta/ver.html"})
public class VentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/venta.html")) {
			listVenta(request, response);
		} else if (alias.equals("/venta/nuevo.html")) {
			newVenta(request, response);
		} else if (alias.equals("/venta/registrar.html")) {
			registerVenta(request, response);
		} else if (alias.equals("/venta/ver.html")) {
			getVenta(request, response);
		}

	}

	private void registerVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				Usuario usuario = (Usuario) session.getAttribute("usuario");
				
				ComprobanteModel cmodel = new ComprobanteModel();
				List<Comprobante> clist = cmodel.consultarTodos();
				request.setAttribute("lComprobante", clist);
				
				ClienteModel kmodel = new ClienteModel();
				List<Cliente> klist = kmodel.consultarTodos();
				request.setAttribute("lCliente", klist);
				
				ProductoModel pmodel = new ProductoModel();
				List<Producto> plist = pmodel.consultarTodos();
				request.setAttribute("lProducto", plist);
				
				VentaModel model = new VentaModel();
				Venta venta = model.buscar(request.getParameter("ven_id"));
				if(venta==null) venta = new Venta();
				/*Verificar si un cliente nuevo*/
				if(request.getParameter("ven_cli").equals("0")){
					Cliente cliente = new Cliente();
					cliente.setRuc_cliente(request.getParameter("ven_cli_ruc"));
					cliente.setNombre_cliente(request.getParameter("ven_cli_nom"));
					cliente.setApellido_cliente(request.getParameter("ven_cli_ape"));
					kmodel.grabar(cliente);					
					venta.setId_cliente(cliente);
				}else{
					Cliente cliente = new Cliente();
					cliente.setId_cliente(Integer.valueOf(request.getParameter("ven_cli")));
					cliente.setRuc_cliente(request.getParameter("ven_cli_ruc"));
					cliente.setNombre_cliente(request.getParameter("ven_cli_nom"));
					cliente.setApellido_cliente(request.getParameter("ven_cli_ape"));
					venta.setId_cliente(cliente);
				}
				
				venta.setId_local(new Local(usuario.getId_personal().getId_local().getId_local()));
				venta.setId_personal(new Personal(usuario.getId_personal().getId_personal()));
				venta.setFecha_venta(new Date());
				venta.setDescripcion_venta(request.getParameter("ven_des"));
				venta.setTotal_venta(Double.parseDouble(request.getParameter("ven_tot")));
				
				ComprobanteModel cmode = new ComprobanteModel();
				Comprobante comprobante = cmode.buscar(request.getParameter("ven_com"));
				comprobante.setCorrelativo_secundario_comprobante(comprobante.getCorrelativo_secundario_comprobante()+1);
				cmode.grabar(comprobante);
				venta.setId_comprobante(comprobante);
				
				String numero = comprobante.getFormato_comprobante().substring(0, 1) + String.format("%03d", comprobante.getCorrelativo_primario_comprobante()) + "-" + String.format("%08d", comprobante.getCorrelativo_secundario_comprobante()); 
				venta.setNumero_venta(numero);
				
				
				
				model.grabar(venta);
				
				VentaDetalleModel vdmodel = new VentaDetalleModel();
				VentaDetalle venta_detalle = null;
				int venta_detalle_total = Integer.valueOf(request.getParameter("tot_line"));
				
				for(int i = 0; i < venta_detalle_total; i++){
					venta_detalle = new VentaDetalle();
					venta_detalle.setId_venta(venta);
					venta_detalle.setId_producto(new Producto(Integer.valueOf(request.getParameter("pro"+String.valueOf(i)))));
					venta_detalle.setCantidad_venta_detalle(Integer.valueOf(request.getParameter("can"+String.valueOf(i))));
					venta_detalle.setPrecio_unitario_venta_detalle(Double.valueOf(request.getParameter("pre"+String.valueOf(i))));
					venta_detalle.setPrecio_total_venta_detalle(Double.valueOf(request.getParameter("tot"+String.valueOf(i))));
					if(Integer.valueOf(request.getParameter("can"+String.valueOf(i)))>0) vdmodel.grabar(venta_detalle);					
				}
				
				request.setAttribute("venta", venta);
				
				VentaDetalle vdTemp = new VentaDetalle();
				vdTemp.setId_venta(venta);
				List<VentaDetalle> vdlist = vdmodel.buscar(vdTemp);
				request.setAttribute("venta_detalle", vdlist);
				
				request.setAttribute("resultMessage", "Registrado correctamente");
				destino = "ver.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

	private void listVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				VentaModel model = new VentaModel();							
				List<Venta> list = model.consultarTodos();
				request.setAttribute("lVenta", list);
				destino = "venta.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				ComprobanteModel cmodel = new ComprobanteModel();
				List<Comprobante> clist = cmodel.consultarTodos();
				request.setAttribute("lComprobante", clist);
				
				ClienteModel kmodel = new ClienteModel();
				List<Cliente> klist = kmodel.consultarTodos();
				request.setAttribute("lCliente", klist);
				
				ProductoModel pmodel = new ProductoModel();
				List<Producto> plist = pmodel.consultarTodos();
				request.setAttribute("lProducto", plist);
				
				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				VentaModel model = new VentaModel();
				Venta venta = model.buscar(request.getParameter("ven_id"));
				request.setAttribute("venta", venta);
				
				VentaDetalle vdTemp = new VentaDetalle();
				vdTemp.setId_venta(venta);
				
				VentaDetalleModel vdmodel = new VentaDetalleModel();
				List<VentaDetalle> vdlist = vdmodel.buscar(vdTemp);
				request.setAttribute("venta_detalle", vdlist);
				
				destino = "ver.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
