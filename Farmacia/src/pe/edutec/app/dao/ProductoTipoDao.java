package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.ProductoTipo;
import pe.edutec.app.mapper.ProductoTipoMapper;

public class ProductoTipoDao implements IDaoCrud<ProductoTipo> {

	@Override
	public ProductoTipo insert(ProductoTipo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el ProductoTipo
			String sql = "insert into tb_producto_tipo(nombre_producto_tipo"
							+ ") values(?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, o.getNombre_producto_tipo());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_producto_tipo(generatedKeys.getInt(1));
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
	public ProductoTipo query(String id) {
		Connection cn = null;
		ProductoTipo obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_producto_tipo,nombre_producto_tipo "
							+ " from tb_producto_tipo "
							+ " where id_producto_tipo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			ProductoTipoMapper mapper = new ProductoTipoMapper();
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
	public List<ProductoTipo> query() {
		Connection cn = null;
		List<ProductoTipo> lista = new ArrayList<ProductoTipo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_producto_tipo,nombre_producto_tipo"
							+ " from tb_producto_tipo ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ProductoTipoMapper mapper = new ProductoTipoMapper();
			while (rs.next()) {
				ProductoTipo obj = mapper.mapRow(rs);
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
	public List<ProductoTipo> query(ProductoTipo o) {
		Connection cn = null;
		List<ProductoTipo> lista = new ArrayList<ProductoTipo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_producto_tipo,nombre_producto_tipo "
							+ " from tb_producto_tipo "
							+ " where nombre_producto_tipo like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_producto_tipo() + "%");
			ResultSet rs = pstm.executeQuery();
			ProductoTipoMapper mapper = new ProductoTipoMapper();
			while (rs.next()) {
				ProductoTipo obj = mapper.mapRow(rs);
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
	public void update(ProductoTipo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el ProductoTipo
			String sql = " update tb_producto_tipo set "
							+ " nombre_producto_tipo = ? "
							+ " where id_producto_tipo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_producto_tipo());
			pstm.setInt(2, o.getId_producto_tipo());
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
			String sql = "delete from tb_producto_tipo where id_producto_tipo= ?";
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
