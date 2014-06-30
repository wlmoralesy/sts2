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

import pe.edutec.app.entity.Cliente;
import pe.edutec.app.model.ClienteModel;

@WebServlet({ "/cliente.html", "/cliente/nuevo.html", "/cliente/editar.html", "/cliente/registrar.html"})
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/cliente.html")) {
			listCliente(request, response);
		} else if (alias.equals("/cliente/nuevo.html")) {
			newCliente(request, response);
		} else if (alias.equals("/cliente/editar.html")) {
			getCliente(request, response);
		} else if (alias.equals("/cliente/registrar.html")) {
			registerCliente(request, response);
		}

	}

	private void registerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{

				ClienteModel model = new ClienteModel();
				Cliente cliente = model.buscar(request.getParameter("cli_id"));
				if(cliente==null) cliente = new Cliente();
				cliente.setRuc_cliente(request.getParameter("cli_ruc"));
				cliente.setNombre_cliente(request.getParameter("cli_nom"));
				cliente.setApellido_cliente(request.getParameter("cli_ape"));
				cliente.setDireccion_cliente(request.getParameter("cli_dir"));				
				model.grabar(cliente);
				
				request.setAttribute("cliente", cliente);
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

	private void listCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ClienteModel model = new ClienteModel();
				
				if(request.getParameter("del_cli_id")!=null)
					try{
						model.eliminar(request.getParameter("del_cli_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<Cliente> list = model.consultarTodos();
				request.setAttribute("lCliente", list);
				destino = "cliente.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private void getCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{				
				ClienteModel model = new ClienteModel();
				Cliente cliente = model.buscar(request.getParameter("cli_id"));				
				request.setAttribute("cliente", cliente);
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
