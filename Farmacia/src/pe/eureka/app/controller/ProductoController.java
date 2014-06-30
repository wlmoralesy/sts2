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

import pe.edutec.app.entity.Cargo;
import pe.edutec.app.entity.Comision;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.Producto;
import pe.edutec.app.entity.ProductoTipo;
import pe.edutec.app.entity.Proveedor;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.entity.UsuarioTipo;
import pe.edutec.app.model.ComisionModel;
import pe.edutec.app.model.LocalModel;
import pe.edutec.app.model.PersonalModel;
import pe.edutec.app.model.ProductoModel;
import pe.edutec.app.model.ProductoTipoModel;
import pe.edutec.app.model.ProveedorModel;
import pe.edutec.app.model.UsuarioModel;
import pe.edutec.app.model.UsuarioTipoModel;

@WebServlet({ "/producto.html", "/producto/nuevo.html", "/producto/editar.html", "/producto/registrar.html"})
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String alias = request.getServletPath();
		if (alias.equals("/producto.html")) {
			listProducto(request, response);
		} else if (alias.equals("/producto/nuevo.html")) {
			newProducto(request, response);
		} else if (alias.equals("/producto/editar.html")) {
			getProducto(request, response);
		} else if (alias.equals("/producto/registrar.html")) {
			registerProducto(request, response);
		}

	}

	private void registerProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try{
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				ProductoTipoModel ptmodel = new ProductoTipoModel();
				List<ProductoTipo> ptlist = ptmodel.consultarTodos();
				request.setAttribute("producto_tipo", ptlist);
				
				ProveedorModel prmodel = new ProveedorModel();
				List<Proveedor> prlist = prmodel.consultarTodos();
				request.setAttribute("proveedor", prlist);
				
				LocalModel lmodel = new LocalModel();
				List<Local> llist = lmodel.consultarTodos();
				request.setAttribute("local", llist);
				
				ComisionModel cmodel = new ComisionModel();
				List<Comision> clist = cmodel.consultarTodos();
				request.setAttribute("comision", clist);
				
				ProductoModel model = new ProductoModel();
				Producto producto = model.buscar(request.getParameter("pro_id"));
				if(producto==null) producto = new Producto();
				producto.setId_producto_tipo(new ProductoTipo(Integer.valueOf(request.getParameter("pro_tip"))));
				producto.setId_proveedor(new Proveedor(Integer.valueOf(request.getParameter("pro_pro"))));				
				producto.setId_comision(new Comision(Integer.valueOf(request.getParameter("pro_com"))));
				producto.setId_local(new Local(Integer.valueOf(request.getParameter("pro_loc"))));
				producto.setNombre_producto(request.getParameter("pro_nom"));
				producto.setDescripcion_producto(request.getParameter("pro_des"));
				producto.setPrecio_producto(Double.parseDouble(request.getParameter("pro_pri")));
				producto.setSustancia_producto(request.getParameter("pro_sus"));
				producto.setPresentacion_producto(request.getParameter("pro_pre"));
				producto.setStock_producto(Integer.parseInt(request.getParameter("pro_sto")));
				producto.setFecha_vencimiento_producto(Date.valueOf(request.getParameter("pro_ven")));				
				model.grabar(producto);
				
				request.setAttribute("producto", producto);
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

	private void listProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				ProductoModel model = new ProductoModel();
				
				if(request.getParameter("del_pro_id")!=null)
					try{
						model.eliminar(request.getParameter("del_pro_id"));
						request.setAttribute("resultMessage", "Registro eliminado correctamente");
					}catch(Exception e){
						request.setAttribute("resultMessage", e.getMessage());
					}
							
				List<Producto> list = model.consultarTodos();
				request.setAttribute("lProducto", list);
				destino = "producto.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void newProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{	
				
				ProductoTipoModel ptmodel = new ProductoTipoModel();
				List<ProductoTipo> ptlist = ptmodel.consultarTodos();
				request.setAttribute("producto_tipo", ptlist);
				
				ProveedorModel prmodel = new ProveedorModel();
				List<Proveedor> prlist = prmodel.consultarTodos();
				request.setAttribute("proveedor", prlist);
				
				LocalModel lmodel = new LocalModel();
				List<Local> llist = lmodel.consultarTodos();
				request.setAttribute("local", llist);
				
				ComisionModel cmodel = new ComisionModel();
				List<Comision> clist = cmodel.consultarTodos();
				request.setAttribute("comision", clist);
				
				destino = "nuevo.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			destino = "error.jsp";
		}		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
	
	private void getProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destino;	
		try {
			HttpSession session = request.getSession(true);
			if(session.getAttribute("usuario")==null){
				destino="../index.jsp";
				request.setAttribute("error","La sesión ha expirado");				
			}else{
				
				ProductoTipoModel ptmodel = new ProductoTipoModel();
				List<ProductoTipo> ptlist = ptmodel.consultarTodos();
				request.setAttribute("producto_tipo", ptlist);
				
				ProveedorModel prmodel = new ProveedorModel();
				List<Proveedor> prlist = prmodel.consultarTodos();
				request.setAttribute("proveedor", prlist);
				
				LocalModel lmodel = new LocalModel();
				List<Local> llist = lmodel.consultarTodos();
				request.setAttribute("local", llist);
				
				ComisionModel cmodel = new ComisionModel();
				List<Comision> clist = cmodel.consultarTodos();
				request.setAttribute("comision", clist);
				
				ProductoModel model = new ProductoModel();
				Producto producto = model.buscar(request.getParameter("pro_id"));				
				request.setAttribute("producto", producto);
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
