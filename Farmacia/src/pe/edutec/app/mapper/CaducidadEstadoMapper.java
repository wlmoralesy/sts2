package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.CaducidadEstado;

public class CaducidadEstadoMapper implements RowMapper<CaducidadEstado> {

	@Override
	public CaducidadEstado mapRow(ResultSet rs) throws SQLException {
		CaducidadEstado c = new CaducidadEstado();
		c.setId_caducidad_estado(rs.getInt("id_caducidad_estado"));
		c.setNombre_caducidad_estado(rs.getString("nombre_caducidad_estado"));
		
		return c;
	}
}
