package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Comprobante;

public class ComprobanteMapper implements RowMapper<Comprobante> {

	@Override
	public Comprobante mapRow(ResultSet rs) throws SQLException {
		Comprobante c = new Comprobante();
		c.setId_comprobante(rs.getInt("id_comprobante"));
		c.setNombre_comprobante(rs.getString("nombre_comprobante"));
		c.setFormato_comprobante(rs.getString("formato_comprobante"));
		c.setCorrelativo_primario_comprobante(rs.getInt("correlativo_primario_comprobante"));
		c.setCorrelativo_secundario_comprobante(rs.getInt("correlativo_secundario_comprobante"));
		
		return c;
	}
}
