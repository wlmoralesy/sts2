package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Empleado;

public class EmpleadoMapper implements RowMapper<Empleado> {

	@Override
	public Empleado mapRow(ResultSet rs) throws SQLException {
		Empleado e = new Empleado();
		e.setCodigo(rs.getString("chr_emplcodigo"));
		e.setPaterno(rs.getString("vch_emplpaterno"));
		e.setMaterno(rs.getString("vch_emplmaterno"));
		e.setNombre(rs.getString("vch_emplnombre"));
		e.setCiudad(rs.getString("vch_emplciudad"));
		e.setDireccion(rs.getString("vch_empldireccion"));
		e.setUsuario(rs.getString("vch_emplusuario"));
		e.setClave(rs.getString("vch_emplclave"));
		return e;
	}
}
