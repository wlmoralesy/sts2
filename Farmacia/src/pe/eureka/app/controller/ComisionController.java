package pe.eureka.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.edutec.app.entity.Cargo;
import pe.edutec.app.entity.Comision;
import pe.edutec.app.entity.ComisionTipo;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.UsuarioTipo;
import pe.edutec.app.model.ComisionModel;
import pe.edutec.app.model.ComisionTipoModel;
import pe.edutec.app.model.PersonalModel;
import pe.edutec.app.model.UsuarioTipoModel;

@WebServlet({ "/comision.html", "/comision/nuevo.html", "/comision/editar.html", "/comision/registrar.html"})
public class ComisionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/comision.html")) {
			listComision(request, response);
		} else if (alias.equals("/comision/nuevo.html")) {
			newComision(request, response);
		}else if (alias.equals("/comision/editar.html")) {
			getComision(request, response);
		} else if (alias.equals("/comision/registrar.html")) {
			registerComision(request, response);
		}

	}

	private void registerComision(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				ComisionTipoModel lmodel = new ComisionTipoModel();
				List<ComisionTipo> list = lmodel.consultarTodos();
				request.setAttribute("comision_tipo", list);
				
				
				ComisionModel model = new ComisionModel();
				Comision comision = model.buscar(request.getParameter("com_id"));
				if(comision==null) comision = new Comision();
				comision.setId_comision_tipo(new ComisionTipo(Integer.valueOf(request.getParameter("com_tip"))));
				comision.setCantidad_comision(Double.parseDouble(request.getParameter("com_can")));
				model.grabar(comision);
				
				request.setAttribute("comision", comision);
				request.setAttribute("resultMessage", "Registrado correctamente");
				destino = "nuevo.jsp";
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
				destino="index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ComisionModel model = new ComisionModel();
				
				if(request.getParameter("del_com_id")!=null)
					try{
						model.eliminar(request.getParameter("del_com_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<Comision> list = model.consultarTodos();
				request.setAttribute("lComision", list);
				destino = "comision.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newComision(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{			
				ComisionTipoModel model = new ComisionTipoModel();
				List<ComisionTipo> list = model.consultarTodos();
				request.setAttribute("comision_tipo", list);
				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getComision(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				ComisionTipoModel lmodel = new ComisionTipoModel();
				List<ComisionTipo> list = lmodel.consultarTodos();
				request.setAttribute("comision_tipo", list);
				
				ComisionModel model = new ComisionModel();
				Comision comision = model.buscar(request.getParameter("com_id"));
				request.setAttribute("comision", comision);
				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
