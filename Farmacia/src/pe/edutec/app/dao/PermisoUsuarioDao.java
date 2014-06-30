package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.PermisoUsuario;
import pe.edutec.app.mapper.PermisoUsuarioMapper;

public class PermisoUsuarioDao implements IDaoCrud<PermisoUsuario> {

	@Override
	public PermisoUsuario insert(PermisoUsuario o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el PermisoUsuario
			String sql = "insert into tb_permiso_usuario(id_permiso,id_usuario_tipo,visible_permiso_usuario"
							+ ") values(?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_permiso().getId_permiso());
			pstm.setInt(2, o.getId_usuario_tipo().getId_usuario_tipo());
			pstm.setBoolean(3, o.isVisible_permiso_usuario());
			
			pstm.executeUpdate();
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
	public PermisoUsuario query(String id) {
		Connection cn = null;
		PermisoUsuario obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select visible_permiso_usuario"
							+" p.id_permiso as id_permiso, p.nombre_permiso as nombre_permiso," 
							+" u.id_usuario_tipo as id_usuario,u.nombre_usuario_tipo as nombre_usuario_tipo ,"
							+ " from tb_permiso_usuario pu inner join tb_permiso p on p.id_permiso = pu.id_permiso inner join tb_usuario_tipo u.id_usuario=p.id_usuario "
							+ " where id_permiso = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			PermisoUsuarioMapper mapper = new PermisoUsuarioMapper();
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
	public List<PermisoUsuario> query() {
		Connection cn = null;
		List<PermisoUsuario> lista = new ArrayList<PermisoUsuario>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select visible_permiso_usuario"
							+" p.id_permiso as id_permiso, p.nombre_permiso as nombre_permiso," 
							+" u.id_usuario_tipo as id_usuario,u.nombre_usuario_tipo as nombre_usuario_tipo ,"
							+ " from tb_permiso_usuario pu inner join tb_permiso p on p.id_permiso = pu.id_permiso inner join tb_usuario_tipo u.id_usuario=p.id_usuario ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			PermisoUsuarioMapper mapper = new PermisoUsuarioMapper();
			while (rs.next()) {
				PermisoUsuario obj = mapper.mapRow(rs);
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
	public List<PermisoUsuario> query(PermisoUsuario o) {
		Connection cn = null;
		List<PermisoUsuario> lista = new ArrayList<PermisoUsuario>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select visible_permiso_usuario, " +
					" p.id_permiso, p.nombre_permiso, " +
					" u.id_usuario_tipo, u.nombre_usuario_tipo " + 
					" from tb_permiso_usuario pu  " +
					" inner join tb_permiso p on p.id_permiso = pu.id_permiso " + 
					" inner join tb_usuario_tipo u on u.id_usuario_tipo = pu.id_usuario_tipo " +
					" where u.id_usuario_tipo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1,o.getId_usuario_tipo().getId_usuario_tipo());
			ResultSet rs = pstm.executeQuery();
			PermisoUsuarioMapper mapper = new PermisoUsuarioMapper();
			while (rs.next()) {
				PermisoUsuario obj = mapper.mapRow(rs);
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
	public void update(PermisoUsuario o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el PermisoUsuario
			String sql = "update tb_permiso_usuario set "
							+ "visible_permiso_usuario = ?"
							+ "where id_permiso = ? and id_usuario_tipo = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setBoolean(1, o.isVisible_permiso_usuario());
			pstm.setInt(2, o.getId_permiso().getId_permiso());
			pstm.setInt(2, o.getId_usuario_tipo().getId_usuario_tipo());
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
			String sql = "delete from tb_permiso_usuario where id_usuario_tipo = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
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
