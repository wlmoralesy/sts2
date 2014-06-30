package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ComprobanteDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Comprobante;

public class ComprobanteModel {

	private IDaoCrud<Comprobante> dao = new ComprobanteDao();
	
	public List<Comprobante> buscar(Comprobante per) {
		return dao.query(per);
	}

	public Comprobante buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Comprobante c) {
		if(c.getId_comprobante()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Comprobante> consultarTodos(){
		return dao.query();
	}
	
}
