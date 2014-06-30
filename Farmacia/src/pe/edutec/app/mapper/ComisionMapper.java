package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Comision;
import pe.edutec.app.entity.ComisionTipo;

public class ComisionMapper implements RowMapper<Comision> {

	@Override
	public Comision mapRow(ResultSet rs) throws SQLException {
		
		ComisionTipo t =new ComisionTipo();
		t.setId_comision_tipo(rs.getInt("id_comision_tipo"));
		t.setNombre_comision_tipo(rs.getString("nombre_comision_tipo"));
		
		Comision c = new Comision();
		c.setId_comision(rs.getInt("id_comision"));
		c.setId_comision_tipo(t);
		c.setCantidad_comision(rs.getDouble("cantidad_comision"));
		
		return c;
	}
}
