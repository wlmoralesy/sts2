package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.PermisoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Permiso;

public class PermisoModel {

	private IDaoCrud<Permiso> dao = new PermisoDao();
	
	public List<Permiso> buscar(Permiso per) {
		return dao.query(per);
	}

	public Permiso buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Permiso c) {
		if(c.getId_permiso()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Permiso> consultarTodos(){
		return dao.query();
	}
	
}
