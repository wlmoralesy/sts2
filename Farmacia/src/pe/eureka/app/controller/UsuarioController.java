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
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.entity.UsuarioTipo;
import pe.edutec.app.model.PersonalModel;
import pe.edutec.app.model.UsuarioModel;
import pe.edutec.app.model.UsuarioTipoModel;

@WebServlet({ "/usuario.html", "/usuario/nuevo.html", "/usuario/editar.html", "/usuario/registrar.html"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/usuario.html")) {
			listUsuario(request, response);
		} else if (alias.equals("/usuario/nuevo.html")) {
			newUsuario(request, response);
		} else if (alias.equals("/usuario/editar.html")) {
			getUsuario(request, response);
		} else if (alias.equals("/usuario/registrar.html")) {
			registerUsuario(request, response);
		}

	}

	private void registerUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				PersonalModel pmodel = new PersonalModel();
				Personal personal = pmodel.buscar(request.getParameter("usu_per"));
				
				UsuarioTipoModel utmodel = new UsuarioTipoModel();
				List<UsuarioTipo> utlist = utmodel.consultarTodos();
				request.setAttribute("usuario_tipo", utlist);
				
				UsuarioModel model = new UsuarioModel();
				Usuario usuario = model.buscar(request.getParameter("usu_id"));
				if(usuario==null) usuario = new Usuario();
				usuario.setId_personal(personal);
				usuario.setId_usuario_tipo(new UsuarioTipo(Integer.valueOf(request.getParameter("usu_tip"))));
				usuario.setNombre_usuario(request.getParameter("usu_usu"));
				usuario.setContrasena_usuario(request.getParameter("usu_con"));
				model.grabar(usuario);
				
				request.setAttribute("usuario", usuario);
				
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

	private void listUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				UsuarioModel model = new UsuarioModel();
				
				if(request.getParameter("del_usu_id")!=null)
					try{
						model.eliminar(request.getParameter("del_usu_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<Usuario> list = model.consultarTodos();
				request.setAttribute("lUsuario", list);
				destino = "usuario.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				PersonalModel pmodel = new PersonalModel();
				List<Personal> plist = pmodel.consultarTodos();
				request.setAttribute("lPersonal", plist);
				
				UsuarioTipoModel utmodel = new UsuarioTipoModel();
				List<UsuarioTipo> utlist = utmodel.consultarTodos();
				request.setAttribute("usuario_tipo", utlist);
				
				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				/*PersonalModel pmodel = new PersonalModel();
				List<Personal> plist = pmodel.consultarTodos();
				request.setAttribute("personal", plist);*/
				
				UsuarioTipoModel utmodel = new UsuarioTipoModel();
				List<UsuarioTipo> utlist = utmodel.consultarTodos();
				request.setAttribute("usuario_tipo", utlist);
				
				UsuarioModel model = new UsuarioModel();
				Usuario usuario = model.buscar(request.getParameter("usu_id"));
				request.setAttribute("usuario", usuario);
				
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
