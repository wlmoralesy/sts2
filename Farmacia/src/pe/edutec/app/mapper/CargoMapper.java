package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Cargo;

public class CargoMapper implements RowMapper<Cargo> {

	@Override
	public Cargo mapRow(ResultSet rs) throws SQLException {
		Cargo c = new Cargo();
		c.setId_cargo(rs.getInt("id_cargo"));
		c.setNombre_cargo(rs.getString("nombre_cargo"));
		
		return c;
	}
}
