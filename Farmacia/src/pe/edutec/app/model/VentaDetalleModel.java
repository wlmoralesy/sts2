package pe.edutec.app.model;

import java.util.List;

import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.dao.VentaDetalleDao;
import pe.edutec.app.entity.VentaDetalle;

public class VentaDetalleModel {

	private IDaoCrud<VentaDetalle> dao = new VentaDetalleDao();
	
	public List<VentaDetalle> buscar(VentaDetalle per) {
		return dao.query(per);
	}

	public VentaDetalle buscar(String id) {
		return dao.query(id);
	}

	public void grabar(VentaDetalle c) {
		dao.insert(c);
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<VentaDetalle> consultarTodos(){
		return dao.query();
	}
	
}
