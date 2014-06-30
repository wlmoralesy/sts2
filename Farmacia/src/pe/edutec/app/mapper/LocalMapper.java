package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Local;

public class LocalMapper implements RowMapper<Local> {

	@Override
	public Local mapRow(ResultSet rs) throws SQLException {
		Local c = new Local();
		c.setId_local(rs.getInt("id_local"));
		c.setNombre_local(rs.getString("nombre_local"));
		c.setDireccion_local(rs.getString("direccion_local"));
		c.setTelefono_local(rs.getString("telefono_local"));
		
		return c;
	}
}
