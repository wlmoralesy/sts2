package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.entity.UsuarioTipo;

public class UsuarioMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs) throws SQLException {
		
		Personal p = new Personal();
		p.setId_personal(rs.getInt("id_personal"));
		p.setDni_personal(rs.getString("dni_personal"));
		p.setNombre_personal(rs.getString("nombre_personal"));
		p.setApellido_personal(rs.getString("apellido_personal"));
		
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		p.setId_local(l);
		
		UsuarioTipo t = new UsuarioTipo();
		t.setId_usuario_tipo(rs.getInt("id_usuario_tipo"));
		t.setNombre_usuario_tipo(rs.getString("nombre_usuario_tipo"));
		
		Usuario u = new Usuario();
		u.setId_usuario(rs.getInt("id_usuario"));
		u.setNombre_usuario(rs.getString("nombre_usuario"));
		u.setContrasena_usuario(rs.getString("contrasena_usuario"));
		u.setId_personal(p);
		u.setId_usuario_tipo(t);
		
		return u;
	}
}
