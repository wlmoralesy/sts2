package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Usuario;
import pe.edutec.app.mapper.UsuarioMapper;

public class UsuarioDao implements IDaoCrud<Usuario> {

	@Override
	public Usuario insert(Usuario o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el usuario
			String sql = "insert into tb_usuario(nombre_usuario,contrasena_usuario,id_persona,id_usuario_tipo"
							+ ") values(?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, o.getNombre_usuario());
			pstm.setString(2, o.getContrasena_usuario());
			pstm.setInt(3, o.getId_personal().getId_personal());
			pstm.setInt(4, o.getId_usuario_tipo().getId_usuario_tipo());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_usuario(generatedKeys.getInt(1));
			}
			
			pstm.close();
			// Confirmar Tx
			cn.commit();
			
			return o;
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public Usuario query(String id) {
		Connection cn = null;
		Usuario obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_usuario,nombre_usuario,contrasena_usuario,"
							+ " p.id_personal as id_personal,p.dni_personal as dni_personal,p.nombre_personal as nombre_personal,p.apellido_personal as apellido_personal, "
							+ " t.id_usuario_tipo as id_usuario_tipo, t.nombre_usuario_tipo as nombre_usuario_tipo, "
							+ " l.id_local as id_local, l.nombre_local as nombre_local "
							+ " from tb_usuario u "
							+ " inner join tb_personal p on p.id_personal=u.id_persona "
							+ " inner join tb_usuario_tipo t on t.id_usuario_tipo=u.id_usuario_tipo "
							+ " inner join tb_local l on l.id_local = p.id_local "
							+ " where id_usuario = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			UsuarioMapper mapper = new UsuarioMapper();
			if (rs.next()) {
				obj = mapper.mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return obj;
	}

	@Override
	public List<Usuario> query() {
		Connection cn = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_usuario,nombre_usuario,contrasena_usuario,"
						+ " p.id_personal as id_personal,p.dni_personal as dni_personal,p.nombre_personal as nombre_personal,p.apellido_personal as apellido_personal, "
						+ " t.id_usuario_tipo as id_usuario_tipo, t.nombre_usuario_tipo as nombre_usuario_tipo, "
						+ " l.id_local as id_local, l.nombre_local as nombre_local "
						+ " from tb_usuario u "
						+ " inner join tb_personal p on p.id_personal=u.id_persona "
						+ " inner join tb_usuario_tipo t on t.id_usuario_tipo=u.id_usuario_tipo "
						+ " inner join tb_local l on l.id_local = p.id_local ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			UsuarioMapper mapper = new UsuarioMapper();
			while (rs.next()) {
				Usuario obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public List<Usuario> query(Usuario o) {
		Connection cn = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_usuario,nombre_usuario,contrasena_usuario,"
						+ " p.id_personal as id_personal,p.dni_personal as dni_personal,p.nombre_personal as nombre_personal,p.apellido_personal as apellido_personal, "
						+ " t.id_usuario_tipo as id_usuario_tipo, t.nombre_usuario_tipo as nombre_usuario_tipo, "
						+ " l.id_local as id_local, l.nombre_local as nombre_local "
						+ " from tb_usuario u "
						+ " inner join tb_personal p on p.id_personal=u.id_persona "
						+ " inner join tb_usuario_tipo t on t.id_usuario_tipo=u.id_usuario_tipo "
						+ " inner join tb_local l on l.id_local = p.id_local "
						+ " where nombre_usuario = ?  and contrasena_usuario = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1,o.getNombre_usuario());
			pstm.setString(2,o.getContrasena_usuario());
			ResultSet rs = pstm.executeQuery();
			UsuarioMapper mapper = new UsuarioMapper();
			while (rs.next()) {
				Usuario obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public void update(Usuario o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el usuario
			String sql = "update tb_usuario set "
							+ " contrasena_usuario = ?,"
							+ " id_usuario_tipo = ? "
							+ " where id_usuario = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);			
			pstm.setString(1, o.getContrasena_usuario());
			pstm.setInt(2, o.getId_usuario_tipo().getId_usuario_tipo());
			pstm.setInt(3, o.getId_usuario());
			pstm.executeUpdate();
			pstm.close();
			// Confirmar Tx
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(String id) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			String sql = "delete from tb_usuario where id_usuario= ?";
			PreparedStatement pstm = cn.prepareStatement(sql);			
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.valueOf(id));
			pstm.executeUpdate();
			cn.commit();
			pstm.close();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} finally{
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		
		
		
	}
}
