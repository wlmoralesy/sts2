package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Local;
import pe.edutec.app.mapper.LocalMapper;

public class LocalDao implements IDaoCrud<Local> {

	@Override
	public Local insert(Local o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el Local
			String sql = "insert into tb_local(nombre_local,direccion_local,telefono_local"
							+ ") values(?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_local());
			pstm.setString(2, o.getDireccion_local());
			pstm.setString(3, o.getTelefono_local());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_local(generatedKeys.getInt(1));
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
	public Local query(String id) {
		Connection cn = null;
		Local obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_local,nombre_local,direccion_local,telefono_local "
							+ " from tb_local "
							+ " where id_local = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			LocalMapper mapper = new LocalMapper();
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
	public List<Local> query() {
		Connection cn = null;
		List<Local> lista = new ArrayList<Local>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_local,nombre_local,direccion_local,telefono_local "
							+ " from tb_local ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			LocalMapper mapper = new LocalMapper();
			while (rs.next()) {
				Local obj = mapper.mapRow(rs);
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
	public List<Local> query(Local o) {
		Connection cn = null;
		List<Local> lista = new ArrayList<Local>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_local,nombre_local,"
							+ " direccion_local,telefono_local"
							+ " from tb_local"
							+ " where nombre_local like ? "
							+ " or direccion_local like ? "
							+ " or telefono_local like ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_local() + "%");
			pstm.setString(2, "%" + o.getDireccion_local() + "%");
			pstm.setString(3, "%" + o.getTelefono_local() + "%");
			ResultSet rs = pstm.executeQuery();
			LocalMapper mapper = new LocalMapper();
			while (rs.next()) {
				Local obj = mapper.mapRow(rs);
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
	public void update(Local o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el Local
			String sql = "update tb_local set "
							+ "nombre_local = ?,"
							+ "direcion_local = ?,"
							+ "telefono_local = ?"
							+ "where id_local = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_local());
			pstm.setString(2, o.getDireccion_local());
			pstm.setString(3, o.getTelefono_local());
			pstm.setInt(4, o.getId_local());
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
			String sql = "delete from tb_local where id_local= ?";
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
