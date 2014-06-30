package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Permiso;
import pe.edutec.app.mapper.PermisoMapper;

public class PermisoDao implements IDaoCrud<Permiso> {

	@Override
	public Permiso insert(Permiso o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el Permiso
			String sql = "insert into tb_permiso(nombre_permiso"
							+ ") values(?)";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_permiso());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_permiso(generatedKeys.getInt(1));
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
	public Permiso query(String id) {
		Connection cn = null;
		Permiso obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_permiso, nombre_permiso "
							+ " from tb_permiso "
							+ " where id_permiso = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			PermisoMapper mapper = new PermisoMapper();
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
	public List<Permiso> query() {
		Connection cn = null;
		List<Permiso> lista = new ArrayList<Permiso>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "  select id_permiso,nombre_permiso "
							+ " from tb_permiso ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			PermisoMapper mapper = new PermisoMapper();
			while (rs.next()) {
				Permiso obj = mapper.mapRow(rs);
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
	public List<Permiso> query(Permiso o) {
		Connection cn = null;
		List<Permiso> lista = new ArrayList<Permiso>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_permiso,nombre_permiso "
							+ " from tb_permiso "
							+ " where nombre_permiso like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_permiso() + "%");
			ResultSet rs = pstm.executeQuery();
			PermisoMapper mapper = new PermisoMapper();
			while (rs.next()) {
				Permiso obj = mapper.mapRow(rs);
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
	public void update(Permiso o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el Permiso
			String sql = " update tb_permiso set "
							+ " nombre_permiso = ? "
							+ " where id_permiso = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_permiso());
			pstm.setInt(2, o.getId_permiso());
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
			String sql = "delete from tb_permiso where id_permiso= ?";
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
