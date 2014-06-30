package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Permiso;
import pe.edutec.app.entity.PermisoUsuario;
import pe.edutec.app.entity.UsuarioTipo;

public class PermisoUsuarioMapper implements RowMapper<PermisoUsuario> {

	@Override
	public PermisoUsuario mapRow(ResultSet rs) throws SQLException {
		
		UsuarioTipo u = new UsuarioTipo();
		u.setId_usuario_tipo(rs.getInt("id_usuario_tipo"));
		u.setNombre_usuario_tipo(rs.getString("nombre_usuario_tipo"));
		
		Permiso p =new Permiso();
		p.setId_permiso(rs.getInt("id_permiso"));
		p.setNombre_permiso(rs.getString("nombre_permiso"));
		
		PermisoUsuario c = new PermisoUsuario();
		c.setId_permiso(p);
		c.setId_usuario_tipo(u);
		c.setVisible_permiso_usuario(rs.getBoolean("visible_permiso_usuario"));

		return c;
	}
}
