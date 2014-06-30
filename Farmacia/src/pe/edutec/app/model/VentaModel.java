package pe.edutec.app.model;

import java.util.List;

import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.dao.VentaDao;
import pe.edutec.app.entity.Venta;

public class VentaModel {

	private IDaoCrud<Venta> dao = new VentaDao();
	
	public List<Venta> buscar(Venta per) {
		return dao.query(per);
	}

	public Venta buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Venta c) {
		if(c.getId_venta()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Venta> consultarTodos(){
		return dao.query();
	}
	
}
