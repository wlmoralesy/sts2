package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Cliente;
import pe.edutec.app.mapper.ClienteMapper;

public class ClienteDao implements IDaoCrud<Cliente> {

	@Override
	public Cliente insert(Cliente o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el cliente
			String sql = "insert into tb_cliente(ruc_cliente,nombre_cliente,"
							+ "apellido_cliente,direccion_cliente"
							+ ") values(?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, o.getRuc_cliente());
			pstm.setString(2, o.getNombre_cliente());
			pstm.setString(3, o.getApellido_cliente());
			pstm.setString(4, o.getDireccion_cliente());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_cliente(generatedKeys.getInt(1));
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
	public Cliente query(String id) {
		Connection cn = null;
		Cliente obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_cliente,ruc_cliente,nombre_cliente, apellido_cliente,direccion_cliente "
							+ " from tb_cliente "
							+ " where id_cliente = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			ClienteMapper mapper = new ClienteMapper();
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
	public List<Cliente> query() {
		Connection cn = null;
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_cliente,ruc_cliente,nombre_cliente,apellido_cliente,direccion_cliente "
							+ " from tb_cliente ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ClienteMapper mapper = new ClienteMapper();
			while (rs.next()) {
				Cliente obj = mapper.mapRow(rs);
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
	public List<Cliente> query(Cliente o) {
		Connection cn = null;
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_cliente,ruc_cliente,nombre_cliente,"
							+ "apellido_cliente,direccion_cliente"
							+ "from tb_cliente"
							+ "where ruc_cliente like ? "
							+ "or nombre_cliente like ? "
							+ "or apellido_cliente like ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getRuc_cliente() + "%");
			pstm.setString(2, "%" + o.getNombre_cliente() + "%");
			pstm.setString(3, "%" + o.getApellido_cliente() + "%");
			ResultSet rs = pstm.executeQuery();
			ClienteMapper mapper = new ClienteMapper();
			while (rs.next()) {
				Cliente obj = mapper.mapRow(rs);
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
	public void update(Cliente o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el cliente
			String sql = "update tb_cliente set "
							+ " ruc_cliente = ?,"
							+ " nombre_cliente = ?,"
							+ " apellido_cliente = ?,"
							+ " direccion_cliente = ? "
							+ " where id_cliente = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getRuc_cliente());
			pstm.setString(2, o.getNombre_cliente());
			pstm.setString(3, o.getApellido_cliente());
			pstm.setString(4, o.getDireccion_cliente());
			pstm.setInt(5, o.getId_cliente());
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
			String sql = "delete from tb_cliente where id_cliente= ?";
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
