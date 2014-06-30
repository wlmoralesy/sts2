package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.ProveedorContacto;
import pe.edutec.app.mapper.ProveedorContactoMapper;

public class ProveedorContactoDao implements IDaoCrud<ProveedorContacto> {

	@Override
	public ProveedorContacto insert(ProveedorContacto o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el proveedor
			String sql = "insert into tb_proveedor_contacto(id_proveedor,dni_proveedor_contacto,"
							+"nombre_proveedor_contacto,apellido_proveedor_contacto,"
							+"telefono_proveedor_contacto,celular_proveedor_contacto"
							+ ") values(?,?,?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_proveedor().getId_proveedor());
			pstm.setString(2, o.getDni_proveedor_contacto());
			pstm.setString(3, o.getNombre_proveedor_contacto());
			pstm.setString(4, o.getApellido_proveedor_contacto());
			pstm.setString(5, o.getTelefono_proveedor_contacto());
			pstm.setString(6, o.getCelular_proveedor_contacto());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_proveedor_contacto(generatedKeys.getInt(1));
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
			throw new RuntimeException("Problemas de insercion del proveedor.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public ProveedorContacto query(String id) {
		Connection cn = null;
		ProveedorContacto obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_proveedor_contacto,dni_proveedor_contacto," +
							"nombre_proveedor_contacto,apellido_proveedor_contacto," +
							"telefono_proveedor_contacto,celular_proveedor_contacto ," +
							"o.id_proveedor as id_proveedor, o.nombre_proveedor as nombre_proveedor " +
							"from tb_proveedor_contacto pc inner join tb_proveedor o on o.id_proveedor=pc.id_proveedor"
							+ " where id_proveedor = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			ProveedorContactoMapper mapper = new ProveedorContactoMapper();
			if (rs.next()) {
				obj = mapper.mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar el proveedor.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return obj;
	}

	@Override
	public List<ProveedorContacto> query() {
		Connection cn = null;
		List<ProveedorContacto> lista = new ArrayList<ProveedorContacto>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_proveedor_contacto,dni_proveedor_contacto," +
					"nombre_proveedor_contacto,apellido_proveedor_contacto," +
					"telefono_proveedor_contacto,celular_proveedor_contacto ," +
					"o.id_proveedor as id_proveedor, o.nombre_proveedor as nombre_proveedor " +
					"from tb_proveedor_contacto pc inner join tb_proveedor o on o.id_proveedor=pc.id_proveedor";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ProveedorContactoMapper mapper = new ProveedorContactoMapper();
			while (rs.next()) {
				ProveedorContacto obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los proveedores.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public List<ProveedorContacto> query(ProveedorContacto o) {
		Connection cn = null;
		List<ProveedorContacto> lista = new ArrayList<ProveedorContacto>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_proveedor_contacto,dni_proveedor_contacto," +
							"nombre_proveedor_contacto,apellido_proveedor_contacto," +
							"telefono_proveedor_contacto,celular_proveedor_contacto ," +
							"o.id_proveedor as id_proveedor, o.nombre_proveedor as nombre_proveedor " +
							"from tb_proveedor_contacto pc inner join tb_proveedor o on o.id_proveedor=pc.id_proveedor"
							+ "where nombre_proveedor=? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_proveedor_contacto() + "%");
			ResultSet rs = pstm.executeQuery();
			ProveedorContactoMapper mapper = new ProveedorContactoMapper();
			while (rs.next()) {
				ProveedorContacto obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los proveedores.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public void update(ProveedorContacto o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el proveedor
			String sql = "update tb_proveedor_contacto set "
							+ "id_proveedor = ?,"
							+ "dni_proveedor_contacto = ?,"
							+ "nombre_proveedor_contacto = ?,"
							+ "apellido_proveedor_contacto = ?,"
							+ "telefono_proveedor_contacto = ?"
							+ "celular_proveedor_contacto= ?"
							+ " where id_proveedor_contacto = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_proveedor().getId_proveedor());
			pstm.setString(2, o.getDni_proveedor_contacto());
			pstm.setString(3, o.getNombre_proveedor_contacto());
			pstm.setString(4, o.getApellido_proveedor_contacto());
			pstm.setString(5, o.getTelefono_proveedor_contacto());
			pstm.setString(6, o.getCelular_proveedor_contacto());
			pstm.setInt(7, o.getId_proveedor_contacto());
			pstm.executeUpdate();
			pstm.close();
			// Confirmar Tx
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("Problemas con la actualización del proveedor.");
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
			String sql = "SELECT COUNT(*) cont FROM tb_proveedor_contacto "
							+ "WHERE id_proveedor_contacto = ?";
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
			sql = "delete from tb_proveedor_contacto where id_proveedor_contacto= ?";
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
