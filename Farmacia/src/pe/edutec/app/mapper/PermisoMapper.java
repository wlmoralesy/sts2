package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Permiso;

public class PermisoMapper implements RowMapper<Permiso> {

	@Override
	public Permiso mapRow(ResultSet rs) throws SQLException {
		Permiso c = new Permiso();
		c.setId_permiso(rs.getInt("id_permiso"));
		c.setNombre_permiso(rs.getString("nombre_permiso"));
		
		return c;
	}
}
