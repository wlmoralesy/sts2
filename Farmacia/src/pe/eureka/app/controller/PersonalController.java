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
import pe.edutec.app.model.CargoModel;
import pe.edutec.app.model.LocalModel;
import pe.edutec.app.model.PersonalModel;

@WebServlet({ "/personal.html", "/personal/nuevo.html", "/personal/editar.html", "/personal/registrar.html"})
public class PersonalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/personal.html")) {
			listPersonal(request, response);
		} else if (alias.equals("/personal/nuevo.html")) {
			newPersonal(request, response);
		} else if (alias.equals("/personal/editar.html")) {
			getPersonal(request, response);
		} else if (alias.equals("/personal/registrar.html")) {
			registerPersonal(request, response);
		}

	}

	private void registerPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				CargoModel cmodel = new CargoModel();
				List<Cargo> clist = cmodel.consultarTodos();
				request.setAttribute("cargo", clist);
				
				LocalModel lmodel = new LocalModel();
				List<Local> llist = lmodel.consultarTodos();
				request.setAttribute("local", llist);
				
				PersonalModel model = new PersonalModel();			
				Personal personal = model.buscar(request.getParameter("per_id"));
				if(personal == null) personal = new Personal();
				personal.setId_personal(Integer.valueOf(request.getParameter("per_id")));
				personal.setDni_personal(request.getParameter("per_dni"));
				personal.setNombre_personal(request.getParameter("per_nom"));
				personal.setApellido_personal(request.getParameter("per_ape"));
				personal.setTelefono_personal(request.getParameter("per_tel"));
				personal.setCelular_personal(request.getParameter("per_cel"));								
				personal.setId_cargo(new Cargo(Integer.valueOf(request.getParameter("per_car"))));
				personal.setId_local(new Local(Integer.valueOf(request.getParameter("per_loc"))));
				model.grabar(personal);
				
				request.setAttribute("personal", personal);
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

	private void listPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				PersonalModel model = new PersonalModel();
				
				if(request.getParameter("del_per_id")!=null)
					try{
						model.eliminar(request.getParameter("del_per_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<Personal> list = model.consultarTodos();
				request.setAttribute("lPersonal", list);
				destino = "personal.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				CargoModel cmodel = new CargoModel();
				List<Cargo> clist = cmodel.consultarTodos();
				request.setAttribute("cargo", clist);
				
				LocalModel lmodel = new LocalModel();
				List<Local> llist = lmodel.consultarTodos();
				request.setAttribute("local", llist);

				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				CargoModel cmodel = new CargoModel();
				List<Cargo> clist = cmodel.consultarTodos();
				request.setAttribute("cargo", clist);
				
				LocalModel lmodel = new LocalModel();
				List<Local> llist = lmodel.consultarTodos();
				request.setAttribute("local", llist);
				
				PersonalModel model = new PersonalModel();
				Personal personal = model.buscar(request.getParameter("per_id"));
				request.setAttribute("personal", personal);
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
