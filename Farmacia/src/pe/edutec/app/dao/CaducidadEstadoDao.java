package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.CaducidadEstado;
import pe.edutec.app.mapper.CaducidadEstadoMapper;

public class CaducidadEstadoDao implements IDaoCrud<CaducidadEstado> {

	@Override
	public CaducidadEstado insert(CaducidadEstado o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el CaducidadEstado
			String sql = "insert into tb_caducidad_estado(nombre_caducidad_estado"
							+ ") values(?)";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_caducidad_estado());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_caducidad_estado(generatedKeys.getInt(1));
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
			throw new RuntimeException("Problemas de insercion del CaducidadEstado.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public CaducidadEstado query(String id) {
		Connection cn = null;
		CaducidadEstado obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select nombre_caducidad_estado"
							+ "from tb_caducidad_estado "
							+ "where id_caducidad_estado = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			CaducidadEstadoMapper mapper = new CaducidadEstadoMapper();
			if (rs.next()) {
				obj = mapper.mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar el CaducidadEstado.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return obj;
	}

	@Override
	public List<CaducidadEstado> query() {
		Connection cn = null;
		List<CaducidadEstado> lista = new ArrayList<CaducidadEstado>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_caducidad_estado,nombre_caducidad_estado,"
							+ "from tb_caducidad_estado ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			CaducidadEstadoMapper mapper = new CaducidadEstadoMapper();
			while (rs.next()) {
				CaducidadEstado obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los CaducidadEstadoes.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public List<CaducidadEstado> query(CaducidadEstado o) {
		Connection cn = null;
		List<CaducidadEstado> lista = new ArrayList<CaducidadEstado>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_caducidad_estado,nombre_caducidad_estado"
							+ "from tb_caducidad_estado"
							+ "where nombre_caducidad_estado like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_caducidad_estado() + "%");
			ResultSet rs = pstm.executeQuery();
			CaducidadEstadoMapper mapper = new CaducidadEstadoMapper();
			while (rs.next()) {
				CaducidadEstado obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los CaducidadEstadoes.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public void update(CaducidadEstado o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el CaducidadEstado
			String sql = "update tb_usuaio_tipo set "
							+ "nombre_caducidad_estado = ?,"
							+ "where id_caducidad_estado = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_caducidad_estado());
			pstm.setInt(2, o.getId_caducidad_estado());
			pstm.executeUpdate();
			pstm.close();
			// Confirmar Tx
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("Problemas con la actualizacion del CaducidadEstado.");
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
			String sql = "SELECT COUNT(*) cont FROM tb_caducidad_estado "
							+ "WHERE id_caducidad_estado = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs  = pstm.executeQuery();
			rs.next();
			long cont = rs.getLong("cont");
			rs.close();
			pstm.close();
			if(cont > 0){
				throw new RuntimeException("No se puede eliminar por que tiene cuentas.");
			}
			sql = "delete from tb_caducidad_estado where id_caducidad_estado= ?";
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
