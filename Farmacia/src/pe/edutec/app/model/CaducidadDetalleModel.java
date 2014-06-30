package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.CaducidadDetalleDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.CaducidadDetalle;

public class CaducidadDetalleModel {

	private IDaoCrud<CaducidadDetalle> dao = new CaducidadDetalleDao();
	
	public List<CaducidadDetalle> buscar(CaducidadDetalle per) {
		return dao.query(per);
	}

	public CaducidadDetalle buscar(String id) {
		return dao.query(id);
	}

	public void grabar(CaducidadDetalle c) {
		if(c.getId_caducidad_detalle() == 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<CaducidadDetalle> consultarTodos(){
		return dao.query();
	}
	
}
