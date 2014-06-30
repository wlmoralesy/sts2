package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ProductoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Producto;

public class ProductoModel {

	private IDaoCrud<Producto> dao = new ProductoDao();
	
	public List<Producto> buscar(Producto per) {
		return dao.query(per);
	}

	public Producto buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Producto c) {
		if(c.getId_producto()==0)
			dao.insert(c);
		else
			dao.update(c);
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Producto> consultarTodos(){
		return dao.query();
	}
	
}
