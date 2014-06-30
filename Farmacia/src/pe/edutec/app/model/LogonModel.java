package pe.edutec.app.model;

import pe.edutec.app.dao.IDaoCrud;
import pe.edutec.app.entity.Empleado;

public class LogonModel {

	// Si es correcto retorna un objeto empleado. 
	// De lo contrario genera una excepción
	public Empleado validar(String usuario, String clave) {
//		IDaoCrud<Empleado> dao = new EmpleadoDao();
//		Empleado emp = dao.query(usuario);
//		String msg = "Datos no son correctos.";
//		if (emp == null) {
//			throw new RuntimeException(msg);
//		} else {
//			if (!emp.getClave().equals(clave)) {
//				throw new RuntimeException(msg);
//			}
//		}
//		return emp;
		return null;
	}
}
