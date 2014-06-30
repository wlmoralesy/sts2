package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.UsuarioTipo;

public class UsuarioTipoMapper implements RowMapper<UsuarioTipo> {

	@Override
	public UsuarioTipo mapRow(ResultSet rs) throws SQLException {
		UsuarioTipo c = new UsuarioTipo();
		c.setId_usuario_tipo(rs.getInt("id_usuario_tipo"));
		c.setNombre_usuario_tipo(rs.getString("nombre_usuario_tipo"));
		
		return c;
	}
}
