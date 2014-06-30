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

import pe.edutec.app.entity.PermisoUsuario;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.model.LogonModel;
import pe.edutec.app.model.PermisoUsuarioModel;
import pe.edutec.app.model.UsuarioModel;

@WebServlet("/login.html")
public class LogonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogonController() {
		super();
	}

	@SuppressWarnings("unused")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {

			String user = request.getParameter("usu_usu");
			String pass = request.getParameter("usu_pas");
			
			HttpSession session = request.getSession(true);
			UsuarioModel model = new UsuarioModel();
			Usuario usuario = new Usuario();
			usuario.setNombre_usuario(user);
			usuario.setContrasena_usuario(pass);
			usuario = model.buscar(usuario).get(0);
			
			if(usuario == null){
				request.setAttribute("error", "Usuario no encontrado");
				destino = "index.jsp";				
			}else{
				session.setAttribute("usuario", usuario);
				
				PermisoUsuarioModel pumodel = new PermisoUsuarioModel();
				List<PermisoUsuario> pu = pumodel.buscar(new PermisoUsuario(usuario.getId_usuario_tipo()));
				session.setAttribute("usuario_pu", pu);
				
				destino = "main.jsp";
			}			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "index.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
