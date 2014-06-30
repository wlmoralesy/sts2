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
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Permiso;
import pe.edutec.app.entity.PermisoUsuario;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.UsuarioTipo;
import pe.edutec.app.model.PermisoModel;
import pe.edutec.app.model.PermisoUsuarioModel;
import pe.edutec.app.model.PersonalModel;
import pe.edutec.app.model.UsuarioTipoModel;

@WebServlet({ "/usuario/tipo.html", "/usuario/tipo/editar.html", "/usuario/tipo/registrar.html", "/usuario/tipo/permisos.html", "/usuario/tipo/permiso.html"})
public class UsuarioTipoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/usuario/tipo.html")) {
			listUsuarioTipo(request, response);
		} else if (alias.equals("/usuario/tipo/editar.html")) {
			getUsuarioTipo(request, response);
		} else if (alias.equals("/usuario/tipo/registrar.html")) {
			registerUsuarioTipo(request, response);
		}else if (alias.equals("/usuario/tipo/permisos.html")) {
			getUsuarioTipoPermiso(request, response);
		}else if (alias.equals("/usuario/tipo/permiso.html")) {
			registerUsuarioTipoPermiso(request, response);
		}

	}

	private void registerUsuarioTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				UsuarioTipoModel model = new UsuarioTipoModel();
				UsuarioTipo usuario_tipo = model.buscar(request.getParameter("usu_tip_id"));
				if(usuario_tipo==null) usuario_tipo = new UsuarioTipo();
				usuario_tipo.setNombre_usuario_tipo(request.getParameter("usu_tip_nom"));
				model.grabar(usuario_tipo);
				
				request.setAttribute("usuario_tipo", usuario_tipo);
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

	private void listUsuarioTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				UsuarioTipoModel model = new UsuarioTipoModel();
				
				if(request.getParameter("del_usu_tip_id")!=null)
					try{
						model.eliminar(request.getParameter("del_usu_tip_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<UsuarioTipo> list = model.consultarTodos();
				request.setAttribute("lUsuarioTipo", list);
				destino = "tipo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getUsuarioTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				UsuarioTipoModel model = new UsuarioTipoModel();
				UsuarioTipo usuario_tipo = model.buscar(request.getParameter("usu_tip_id"));
				request.setAttribute("usuario_tipo", usuario_tipo);
				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getUsuarioTipoPermiso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				PermisoModel model = new PermisoModel();
				List<Permiso> list = model.consultarTodos();
				request.setAttribute("permiso", list);
				
				UsuarioTipo ut = new UsuarioTipo();
				ut.setId_usuario_tipo(Integer.valueOf(request.getParameter("usu_tip_id")));
				
				PermisoUsuarioModel pumodel = new PermisoUsuarioModel();
				List<PermisoUsuario> pulist = pumodel.buscar(new PermisoUsuario(ut));
				request.setAttribute("permiso_usuario", pulist);
				
				request.setAttribute("r_usu_tip_id", request.getParameter("usu_tip_id"));
				
				destino = "permiso.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void registerUsuarioTipoPermiso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				UsuarioTipo ut = new UsuarioTipo();
				ut.setId_usuario_tipo(Integer.valueOf(request.getParameter("r_usu_tip_id")));
				
				PermisoUsuarioModel model = new PermisoUsuarioModel();
				model.eliminar(String.valueOf(ut.getId_usuario_tipo()));
				
				PermisoUsuario pu = null;
				
				for(int i = 0; i < 4; i++){
					pu = new PermisoUsuario();
					pu.setId_permiso(new Permiso(i+1));
					pu.setId_usuario_tipo(ut);
					pu.setVisible_permiso_usuario(request.getParameter("per_"+String.valueOf(i+1))!=null?true:false);
					model.grabar(pu);
				}
				
				PermisoModel pmodel = new PermisoModel();
				List<Permiso> plist = pmodel.consultarTodos();
				request.setAttribute("permiso", plist);
				
				PermisoUsuarioModel pumodel = new PermisoUsuarioModel();
				List<PermisoUsuario> pulist = pumodel.buscar(new PermisoUsuario(ut));
				request.setAttribute("permiso_usuario", pulist);
				
				request.setAttribute("r_usu_tip_id", request.getParameter("r_usu_tip_id"));
				
				request.setAttribute("resultMessage", "Registrado correctamente");
				
				destino = "permiso.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
