package pe.eureka.app.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.edutec.app.entity.ComisionDetalleReporte;
import pe.edutec.app.entity.ComisionReporte;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.entity.VentaDetalleReporte;
import pe.edutec.app.entity.VentaReporte;
import pe.edutec.app.model.ComisionReporteModel;

import pe.edutec.app.model.VentaReporteModel;

@WebServlet({ "/reporte/cierre.html", "/reporte/comision.html"  })
public class ReporteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/reporte/cierre.html")) {
			listVenta(request, response);
		} else if(alias.equals("/reporte/comision.html")) {
			listComision(request, response);
		}

	}

	private void listVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{

				Usuario usuario = (Usuario) session.getAttribute("usuario");
				Date fecha = (request.getParameter("fecha")==null?(new Date(new java.util.Date().getTime())):Date.valueOf(request.getParameter("fecha")));
				
				request.setAttribute("fecha", fecha);
				
				VentaReporteModel model = new VentaReporteModel();
				List<VentaReporte> list = model.reporte(fecha, usuario.getId_personal().getId_local().getId_local());
				request.setAttribute("reporte", list);
				
				List<VentaDetalleReporte> vrlist = model.reporteDetalle(fecha, usuario.getId_personal().getId_local().getId_local());
				request.setAttribute("reporte_detalle", vrlist);
				
				destino = "cierre.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void listComision(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{

				Usuario usuario = (Usuario) session.getAttribute("usuario");
				Date fecha1 = (request.getParameter("fecha1")==null?(new Date(new java.util.Date().getTime())):Date.valueOf(request.getParameter("fecha1")));
				Date fecha2 = (request.getParameter("fecha2")==null?(new Date(new java.util.Date().getTime())):Date.valueOf(request.getParameter("fecha2")));
				
				request.setAttribute("fecha1", fecha1);
				request.setAttribute("fecha2", fecha2);
				
				ComisionReporteModel model = new ComisionReporteModel();
				List<ComisionReporte> list = model.reporte(fecha1, fecha2, usuario.getId_personal().getId_local().getId_local());
				request.setAttribute("reporte", list);
				
				List<ComisionDetalleReporte> vrlist = model.reporteDetalle(fecha1, fecha2, usuario.getId_personal().getId_local().getId_local());
				request.setAttribute("reporte_detalle", vrlist);
				
				destino = "comision.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
}
