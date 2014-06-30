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

import pe.edutec.app.entity.ProductoTipo;
import pe.edutec.app.model.ProductoTipoModel;

@WebServlet({ "/producto/tipo.html", "/producto/tipo/nuevo.html", "/producto/tipo/editar.html", "/producto/tipo/registrar.html"})
public class ProductoTipoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/producto/tipo.html")) {
			listProductoTipo(request, response);
		} else if (alias.equals("/producto/tipo/nuevo.html")) {
			newProductoTipo(request, response);
		} else if (alias.equals("/producto/tipo/editar.html")) {
			getProductoTipo(request, response);
		} else if (alias.equals("/producto/tipo/registrar.html")) {
			registerProductoTipo(request, response);
		}

	}

	private void registerProductoTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ProductoTipoModel model = new ProductoTipoModel();
				ProductoTipo producto_tipo = model.buscar(request.getParameter("pro_tip_id"));
				if(producto_tipo==null) producto_tipo = new ProductoTipo();
				producto_tipo.setNombre_producto_tipo(request.getParameter("pro_tip_nom"));
				model.grabar(producto_tipo);
				
				request.setAttribute("producto_tipo", producto_tipo);
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

	private void listProductoTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ProductoTipoModel model = new ProductoTipoModel();
				
				if(request.getParameter("del_pro_tip_id")!=null)
					try{
						model.eliminar(request.getParameter("del_pro_tip_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<ProductoTipo> list = model.consultarTodos();
				request.setAttribute("lProductoTipo", list);
				destino = "tipo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newProductoTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private void getProductoTipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ProductoTipoModel model = new ProductoTipoModel();
				ProductoTipo producto_tipo = model.buscar(request.getParameter("pro_tip_id"));
				request.setAttribute("producto_tipo", producto_tipo);
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
