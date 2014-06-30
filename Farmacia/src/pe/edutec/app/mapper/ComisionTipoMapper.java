package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.ComisionTipo;

public class ComisionTipoMapper implements RowMapper<ComisionTipo> {

	@Override
	public ComisionTipo mapRow(ResultSet rs) throws SQLException {
		ComisionTipo c = new ComisionTipo();
		c.setId_comision_tipo(rs.getInt("id_comision_tipo"));
		c.setNombre_comision_tipo(rs.getString("nombre_comision_tipo"));
		
		return c;
	}
}
