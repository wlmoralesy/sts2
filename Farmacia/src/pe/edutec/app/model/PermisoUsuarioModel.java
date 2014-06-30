package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.PermisoUsuarioDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.PermisoUsuario;

public class PermisoUsuarioModel {

	private IDaoCrud<PermisoUsuario> dao = new PermisoUsuarioDao();
	
	public List<PermisoUsuario> buscar(PermisoUsuario per) {
		return dao.query(per);
	}

	public PermisoUsuario buscar(String id) {
		return dao.query(id);
	}

	public void grabar(PermisoUsuario c) {
		dao.insert(c);
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<PermisoUsuario> consultarTodos(){
		return dao.query();
	}
	
}
