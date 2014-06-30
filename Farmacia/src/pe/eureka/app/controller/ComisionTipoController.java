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
import pe.edutec.app.entity.ComisionTipo;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.UsuarioTipo;
import pe.edutec.app.model.ComisionTipoModel;
import pe.edutec.app.model.PersonalModel;
import pe.edutec.app.model.UsuarioTipoModel;

@WebServlet({ "/comision/tipo.html", "/comision/tipo/nuevo.html", "/comision/tipo/editar.html", "/comision/tipo/registrar.html"})
public class ComisionTipoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/comision/tipo.html")) {
			listComisionTipo(request, response);
		} else if (alias.equals("/comision/tipo/nuevo.html")) {
			newComisionTipo(request, response);
		}else if (alias.equals("/comision/tipo/editar.html")) {
			getComisionTipo(request, response);
		} else if (alias.equals("/comision/tipo/registrar.html")) {
			registerComisionTipo(request, response);
		}

	}

	private void registerComisionTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ComisionTipoModel model = new ComisionTipoModel();
				ComisionTipo comision_tipo = model.buscar(request.getParameter("com_tip_id"));
				if(comision_tipo==null) comision_tipo = new ComisionTipo();
				comision_tipo.setNombre_comision_tipo(request.getParameter("com_tip_nom"));
				model.grabar(comision_tipo);
				
				request.setAttribute("comision_tipo", comision_tipo);
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

	private void listComisionTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ComisionTipoModel model = new ComisionTipoModel();
				
				if(request.getParameter("del_com_tip_id")!=null)
					try{
						model.eliminar(request.getParameter("del_com_tip_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<ComisionTipo> list = model.consultarTodos();
				request.setAttribute("lComisionTipo", list);
				destino = "tipo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newComisionTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{				
				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getComisionTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ComisionTipoModel model = new ComisionTipoModel();
				ComisionTipo comision_tipo = model.buscar(request.getParameter("com_tip_id"));
				request.setAttribute("comision_tipo", comision_tipo);
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
