package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Cargo;
import pe.edutec.app.mapper.CargoMapper;

public class CargoDao implements IDaoCrud<Cargo> {

	@Override
	public Cargo insert(Cargo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el Cargo
			String sql = "insert into tb_cargo(nombre_cargo"
							+ ") values(?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, o.getNombre_cargo());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_cargo(generatedKeys.getInt(1));
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
	public Cargo query(String id) {
		Connection cn = null;
		Cargo obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_cargo,nombre_cargo "
							+ " from tb_cargo "
							+ " where id_cargo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			CargoMapper mapper = new CargoMapper();
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
	public List<Cargo> query() {
		Connection cn = null;
		List<Cargo> lista = new ArrayList<Cargo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_cargo,nombre_cargo "
							+ " from tb_cargo ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			CargoMapper mapper = new CargoMapper();
			while (rs.next()) {
				Cargo obj = mapper.mapRow(rs);
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
	public List<Cargo> query(Cargo o) {
		Connection cn = null;
		List<Cargo> lista = new ArrayList<Cargo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_cargo,nombre_cargo "
							+ " from tb_cargo "
							+ " where nombre_cargo like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_cargo() + "%");
			ResultSet rs = pstm.executeQuery();
			CargoMapper mapper = new CargoMapper();
			while (rs.next()) {
				Cargo obj = mapper.mapRow(rs);
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
	public void update(Cargo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el Cargo
			String sql = " update tb_cargo set "
							+ " nombre_cargo = ? "
							+ " where id_cargo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_cargo());
			pstm.setInt(2, o.getId_cargo());
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
			String sql = "delete from tb_cargo where id_cargo= ?";
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
