package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ComisionDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Comision;

public class ComisionModel {

	private IDaoCrud<Comision> dao = new ComisionDao();
	
	public List<Comision> buscar(Comision per) {
		return dao.query(per);
	}

	public Comision buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Comision c) {
		if(c.getId_comision()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Comision> consultarTodos(){
		return dao.query();
	}
	
}
