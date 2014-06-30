package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Cargo;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;

public class PersonalMapper implements RowMapper<Personal> {

	@Override
	public Personal mapRow(ResultSet rs) throws SQLException {
		Personal p = new Personal();
		p.setId_personal(rs.getInt("id_personal"));
		
		Cargo c = new Cargo();
		c.setId_cargo(rs.getInt("id_cargo"));
		c.setNombre_cargo(rs.getString("nombre_cargo"));
		p.setId_cargo(c);
		
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		p.setId_local(l);
		
		p.setDni_personal(rs.getString("dni_personal"));
		p.setNombre_personal(rs.getString("nombre_personal"));
		p.setApellido_personal(rs.getString("apellido_personal"));
		p.setTelefono_personal(rs.getString("telefono_personal"));
		p.setCelular_personal(rs.getString("celular_personal"));		
		return p;
	}
}
