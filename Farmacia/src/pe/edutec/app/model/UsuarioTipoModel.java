package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.UsuarioTipoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.UsuarioTipo;

public class UsuarioTipoModel {

	private IDaoCrud<UsuarioTipo> dao = new UsuarioTipoDao();
	
	public List<UsuarioTipo> buscar(UsuarioTipo per) {
		return dao.query(per);
	}

	public UsuarioTipo buscar(String id) {
		return dao.query(id);
	}

	public void grabar(UsuarioTipo c) {
		if(c.getId_usuario_tipo()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<UsuarioTipo> consultarTodos(){
		return dao.query();
	}
	
}
