package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.CaducidadDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Caducidad;

public class CaducidadModel {

	private IDaoCrud<Caducidad> dao = new CaducidadDao();
	
	public List<Caducidad> buscar(Caducidad per) {
		return dao.query(per);
	}

	public Caducidad buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Caducidad c) {
		if(c.getId_caducidad()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Caducidad> consultarTodos(){
		return dao.query();
	}
	
}
