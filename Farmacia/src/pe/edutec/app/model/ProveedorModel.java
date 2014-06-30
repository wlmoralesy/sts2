package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ProveedorDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Proveedor;

public class ProveedorModel {

	private IDaoCrud<Proveedor> dao = new ProveedorDao();
	
	public List<Proveedor> buscar(Proveedor per) {
		return dao.query(per);
	}

	public Proveedor buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Proveedor c) {
		if(c.getId_proveedor()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Proveedor> consultarTodos(){
		return dao.query();
	}
	
}
