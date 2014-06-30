package pe.edutec.app.model;

import java.util.List;
import pe.edutec.app.dao.ClienteDao;
import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Cliente;

public class ClienteModel {

	private IDaoCrud<Cliente> dao = new ClienteDao();
	
	public List<Cliente> buscar(Cliente per) {
		return dao.query(per);
	}

	public Cliente buscar(String id) {
		return dao.query(id);
	}

	public void grabar(Cliente c) {
		if(c.getId_cliente()== 0){
			dao.insert(c);
		} else {
			dao.update(c);
		}
	}

	public void eliminar(String codigo) {
		dao.delete(codigo);
	}
	
	public List<Cliente> consultarTodos(){
		return dao.query();
	}
	
}
