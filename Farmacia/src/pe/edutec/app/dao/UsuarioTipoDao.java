package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.UsuarioTipo;
import pe.edutec.app.mapper.UsuarioTipoMapper;

public class UsuarioTipoDao implements IDaoCrud<UsuarioTipo> {

	@Override
	public UsuarioTipo insert(UsuarioTipo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el UsuarioTipo
			String sql = "insert into tb_usuario_tipo(nombre_usuario_tipo"
							+ ") values(?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, o.getNombre_usuario_tipo());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_usuario_tipo(generatedKeys.getInt(1));
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
			throw new RuntimeException("Problemas de insercion del UsuarioTipo.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public UsuarioTipo query(String id) {
		Connection cn = null;
		UsuarioTipo obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_usuario_tipo,nombre_usuario_tipo "
							+ "from tb_usuario_tipo "
							+ "where id_usuario_tipo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.valueOf(id));
			ResultSet rs = pstm.executeQuery();
			UsuarioTipoMapper mapper = new UsuarioTipoMapper();
			if (rs.next()) {
				obj = mapper.mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar el UsuarioTipo.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return obj;
	}

	@Override
	public List<UsuarioTipo> query() {
		Connection cn = null;
		List<UsuarioTipo> lista = new ArrayList<UsuarioTipo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_usuario_tipo,nombre_usuario_tipo"
							+ " from tb_usuario_tipo ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			UsuarioTipoMapper mapper = new UsuarioTipoMapper();
			while (rs.next()) {
				UsuarioTipo obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los UsuarioTipoes.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public List<UsuarioTipo> query(UsuarioTipo o) {
		Connection cn = null;
		List<UsuarioTipo> lista = new ArrayList<UsuarioTipo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_usuario_tipo,nombre_usuario_tipo"
							+ "from tb_usuario_tipo"
							+ "where nombre_usuario_tipo like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_usuario_tipo() + "%");
			ResultSet rs = pstm.executeQuery();
			UsuarioTipoMapper mapper = new UsuarioTipoMapper();
			while (rs.next()) {
				UsuarioTipo obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los UsuarioTipoes.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public void update(UsuarioTipo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el UsuarioTipo
			String sql = "update tb_usuario_tipo set "
							+ "nombre_usuario_tipo = ? "
							+ "where id_usuario_tipo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_usuario_tipo());
			pstm.setInt(2, o.getId_usuario_tipo());
			pstm.executeUpdate();
			pstm.close();
			// Confirmar Tx
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("Problemas con la actualizacion del UsuarioTipo.");
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
			String sql0 = "delete from tb_permiso_usuario where id_usuario_tipo= ?";
			PreparedStatement pstm0 = cn.prepareStatement(sql0);			
			pstm0 = cn.prepareStatement(sql0);
			pstm0.setString(1, id);
			pstm0.executeUpdate();
			cn.commit();
			pstm0.close();
			
			String sql = "delete from tb_usuario_tipo where id_usuario_tipo= ?";
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
