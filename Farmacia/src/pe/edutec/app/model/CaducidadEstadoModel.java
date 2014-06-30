package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.CaducidadEstadoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.CaducidadEstado;

public class CaducidadEstadoModel {

	private IDaoCrud<CaducidadEstado> dao = new CaducidadEstadoDao();
	
	public List<CaducidadEstado> buscar(CaducidadEstado per) {
		return dao.query(per);
	}

	public CaducidadEstado buscar(String id) {
		return dao.query(id);
	}

	public void grabar(CaducidadEstado c) {
		if(c.getId_caducidad_estado()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<CaducidadEstado> consultarTodos(){
		return dao.query();
	}
	
}
