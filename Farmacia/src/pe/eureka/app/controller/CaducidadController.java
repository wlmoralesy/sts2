package pe.eureka.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.edutec.app.entity.CaducidadEstado;
import pe.edutec.app.entity.Cliente;
import pe.edutec.app.entity.Comprobante;
import pe.edutec.app.entity.Producto;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.entity.Caducidad;
import pe.edutec.app.entity.CaducidadDetalle;
import pe.edutec.app.model.ClienteModel;
import pe.edutec.app.model.ComprobanteModel;
import pe.edutec.app.model.ProductoModel;
import pe.edutec.app.model.CaducidadDetalleModel;
import pe.edutec.app.model.CaducidadModel;
import pe.farmacia.variables.VConstants;

@WebServlet({ "/caducidad.html",  "/caducidad/nuevo.html", "/caducidad/registrar.html", "/caducidad/ver.html"})
public class CaducidadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/caducidad.html")) {
			listCaducidad(request, response);
		} else if (alias.equals("/caducidad/nuevo.html")) {
			newCaducidad(request, response);
		} else if (alias.equals("/caducidad/registrar.html")) {
			registerCaducidad(request, response);
		} else if (alias.equals("/caducidad/ver.html")) {
			getCaducidad(request, response);
		}

	}

	private void registerCaducidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				Usuario usuario = (Usuario) session.getAttribute("usuario");

				CaducidadModel model = new CaducidadModel();
				Caducidad caducidad = model.buscar(request.getParameter("cad_id"));
				if(caducidad==null) caducidad = new Caducidad();
				caducidad.setId_caducidad_estado(new CaducidadEstado(VConstants.CE_CREADO,"CREADO"));
				
				caducidad.setId_local(usuario.getId_personal().getId_local());
				caducidad.setId_personal(usuario.getId_personal());
				caducidad.setFecha_creacion_caducidad((new Date()));
				caducidad.setDescripcion_caducidad(request.getParameter("cad_des"));
				
				model.grabar(caducidad);
				
				CaducidadDetalleModel vdmodel = new CaducidadDetalleModel();
				CaducidadDetalle caducidad_detalle = null;
				int caducidad_detalle_total = Integer.valueOf(request.getParameter("tot_line"));
						
				for(int i = 0; i < caducidad_detalle_total; i++){					
					caducidad_detalle = new CaducidadDetalle();					
					caducidad_detalle.setId_caducidad(caducidad.getId_caducidad());
					caducidad_detalle.setId_producto(new Producto(Integer.valueOf(request.getParameter("pro"+String.valueOf(i)))));
					caducidad_detalle.setCantidad_caducidad_detalle(Integer.valueOf(request.getParameter("can"+String.valueOf(i))));
					vdmodel.grabar(caducidad_detalle);					
				}
				
				request.setAttribute("caducidad", caducidad);
				
				CaducidadDetalle vdTemp = new CaducidadDetalle();
				vdTemp.setId_caducidad(caducidad.getId_caducidad());
				List<CaducidadDetalle> vdlist = vdmodel.buscar(vdTemp);
				request.setAttribute("caducidad_detalle", vdlist);
				
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

	private void listCaducidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				CaducidadModel model = new CaducidadModel();							
				List<Caducidad> list = model.consultarTodos();
				request.setAttribute("lCaducidad", list);
				destino = "caducidad.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newCaducidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private void getCaducidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				CaducidadModel model = new CaducidadModel();
				Caducidad caducidad = model.buscar(request.getParameter("cad_id"));
				request.setAttribute("caducidad", caducidad);
				
				CaducidadDetalle vdTemp = new CaducidadDetalle();
				vdTemp.setId_caducidad(caducidad.getId_caducidad());
				
				CaducidadDetalleModel vdmodel = new CaducidadDetalleModel();
				List<CaducidadDetalle> vdlist = vdmodel.buscar(vdTemp);
				request.setAttribute("caducidad_detalle", vdlist);
				
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
