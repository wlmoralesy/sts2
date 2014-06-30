package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.LocalDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Local;

public class LocalModel {

	private IDaoCrud<Local> dao = new LocalDao();
	
	public List<Local> buscar(Local per) {
		return dao.query(per);
	}

	public Local buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Local c) {
		if(c.getId_local()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Local> consultarTodos(){
		return dao.query();
	}
	
}
