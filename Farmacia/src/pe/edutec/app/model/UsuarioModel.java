package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.UsuarioDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Usuario;

public class UsuarioModel {

	private IDaoCrud<Usuario> dao = new UsuarioDao();
	
	public List<Usuario> buscar(Usuario per) {
		return dao.query(per);
	}

	public Usuario buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Usuario c) {
		if(c.getId_usuario()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Usuario> consultarTodos(){
		return dao.query();
	}
	
}
