package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.PersonalDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Personal;

public class PersonalModel {

	private IDaoCrud<Personal> dao = new PersonalDao();
	
	public List<Personal> buscar(Personal per) {
		return dao.query(per);
	}

	public Personal buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Personal c) {
		if(c.getId_personal()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Personal> consultarTodos(){
		return dao.query();
	}
	
}
