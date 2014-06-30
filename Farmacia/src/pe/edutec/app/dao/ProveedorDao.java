package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Proveedor;
import pe.edutec.app.mapper.ProveedorMapper;

public class ProveedorDao implements IDaoCrud<Proveedor> {

	@Override
	public Proveedor insert(Proveedor o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el proveedor
			String sql = "insert into tb_proveedor(ruc_proveedor,nombre_proveedor,telefono_proveedor,"
							+ "direccion_proveedor,laboratorio"
							+ ") values(?,?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, o.getRuc_proveedor());
			pstm.setString(2, o.getNombre_proveedor());
			pstm.setString(3, o.getTelefono_proveedor());
			pstm.setString(4, o.getDireccion_proveedor());
			pstm.setBoolean(5, o.isLaboratorio());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_proveedor(generatedKeys.getInt(1));
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
	public Proveedor query(String id) {
		Connection cn = null;
		Proveedor obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_proveedor,ruc_proveedor,nombre_proveedor,telefono_proveedor,"
							+ "direccion_proveedor,laboratorio"
							+ " from tb_proveedor "
							+ " where id_proveedor = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			ProveedorMapper mapper = new ProveedorMapper();
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
	public List<Proveedor> query() {
		Connection cn = null;
		List<Proveedor> lista = new ArrayList<Proveedor>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_proveedor,ruc_proveedor,nombre_proveedor,telefono_proveedor,"
							+ "direccion_proveedor,laboratorio "
							+ " from tb_proveedor ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ProveedorMapper mapper = new ProveedorMapper();
			while (rs.next()) {
				Proveedor obj = mapper.mapRow(rs);
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
	public List<Proveedor> query(Proveedor o) {
		Connection cn = null;
		List<Proveedor> lista = new ArrayList<Proveedor>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_proveedor,ruc_proveedor,nombre_proveedor,telefono_proveedor,"
							+ "direccion_proveedor,laboratorio "
							+ " from tb_proveedor"
							+ " where nombre_proveedor like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_proveedor() + "%");
			ResultSet rs = pstm.executeQuery();
			ProveedorMapper mapper = new ProveedorMapper();
			while (rs.next()) {
				Proveedor obj = mapper.mapRow(rs);
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
	public void update(Proveedor o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el proveedor
			String sql = "update tb_proveedor set "
							+ "ruc_proveedor = ?,"
							+ "nombre_proveedor = ?,"
							+ "telefono_proveedor = ?,"
							+ "direccion_proveedor = ?,"
							+ "laboratorio = ? "
							+ "where id_proveedor = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getRuc_proveedor());
			pstm.setString(2, o.getNombre_proveedor());
			pstm.setString(3, o.getTelefono_proveedor());
			pstm.setString(4, o.getDireccion_proveedor());
			pstm.setBoolean(5, o.isLaboratorio());
			pstm.setInt(6, o.getId_proveedor());
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
			String sql =  "delete from tb_proveedor where id_proveedor= ?";
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
