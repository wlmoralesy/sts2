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
import pe.edutec.app.entity.Proveedor;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.entity.UsuarioTipo;
import pe.edutec.app.model.PersonalModel;
import pe.edutec.app.model.ProveedorModel;
import pe.edutec.app.model.UsuarioModel;
import pe.edutec.app.model.UsuarioTipoModel;

@WebServlet({ "/proveedor.html", "/proveedor/nuevo.html", "/proveedor/editar.html", "/proveedor/registrar.html"})
public class ProveedorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/proveedor.html")) {
			listProveedor(request, response);
		} else if (alias.equals("/proveedor/nuevo.html")) {
			newProveedor(request, response);
		} else if (alias.equals("/proveedor/editar.html")) {
			getProveedor(request, response);
		} else if (alias.equals("/proveedor/registrar.html")) {
			registerProveedor(request, response);
		}

	}

	private void registerProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ProveedorModel model = new ProveedorModel();
				Proveedor proveedor = model.buscar(request.getParameter("pro_id"));
				if(proveedor==null) proveedor = new Proveedor();
				proveedor.setRuc_proveedor(request.getParameter("pro_ruc"));
				proveedor.setNombre_proveedor(request.getParameter("pro_nom"));
				proveedor.setTelefono_proveedor(request.getParameter("pro_tel"));
				proveedor.setDireccion_proveedor(request.getParameter("pro_dir"));
				proveedor.setLaboratorio(Boolean.valueOf(request.getParameter("pro_lab")));
				model.grabar(proveedor);
				
				request.setAttribute("proveedor", proveedor);
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

	private void listProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ProveedorModel model = new ProveedorModel();
				
				if(request.getParameter("del_pro_id")!=null)
					try{
						model.eliminar(request.getParameter("del_pro_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<Proveedor> list = model.consultarTodos();
				request.setAttribute("lProveedor", list);
				destino = "proveedor.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private void getProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ProveedorModel model = new ProveedorModel();
				Proveedor proveedor = model.buscar(request.getParameter("pro_id"));
				request.setAttribute("proveedor", proveedor);
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
