package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ProveedorContactoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.ProveedorContacto;

public class ProveedorContactoModel {

	private IDaoCrud<ProveedorContacto> dao = new ProveedorContactoDao();
	
	public List<ProveedorContacto> buscar(ProveedorContacto per) {
		return dao.query(per);
	}

	public ProveedorContacto buscar(String id) {
		return dao.query(id);
	}

	public void grabar(ProveedorContacto c) {
		if(c.getId_proveedor_contacto()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<ProveedorContacto> consultarTodos(){
		return dao.query();
	}
	
}
