package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.CargoDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Cargo;

public class CargoModel {

	private IDaoCrud<Cargo> dao = new CargoDao();
	
	public List<Cargo> buscar(Cargo per) {
		return dao.query(per);
	}

	public Cargo buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Cargo c) {
		if(c.getId_cargo()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Cargo> consultarTodos(){
		return dao.query();
	}
	
}
