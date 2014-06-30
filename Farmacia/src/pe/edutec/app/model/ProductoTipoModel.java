package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ProductoTipoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.ProductoTipo;

public class ProductoTipoModel {

	private IDaoCrud<ProductoTipo> dao = new ProductoTipoDao();
	
	public List<ProductoTipo> buscar(ProductoTipo per) {
		return dao.query(per);
	}

	public ProductoTipo buscar(String id) {
		return dao.query(id);
	}

	public void grabar(ProductoTipo c) {
		if(c.getId_producto_tipo()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<ProductoTipo> consultarTodos(){
		return dao.query();
	}
	
}
