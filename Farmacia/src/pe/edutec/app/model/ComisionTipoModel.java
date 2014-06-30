package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ComisionTipoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.ComisionTipo;

public class ComisionTipoModel {

	private IDaoCrud<ComisionTipo> dao = new ComisionTipoDao();
	
	public List<ComisionTipo> buscar(ComisionTipo per) {
		return dao.query(per);
	}

	public ComisionTipo buscar(String id) {
		return dao.query(id);
	}

	public void grabar(ComisionTipo c) {
		if(c.getId_comision_tipo()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<ComisionTipo> consultarTodos(){
		return dao.query();
	}
	
}
