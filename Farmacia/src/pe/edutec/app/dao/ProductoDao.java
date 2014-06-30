package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Producto;
import pe.edutec.app.mapper.ProductoMapper;

public class ProductoDao implements IDaoCrud<Producto> {

	@Override
	public Producto insert(Producto o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el producto
			String sql = "insert into tb_producto(id_producto_tipo,id_proveedor,id_comision,id_local,"
							+ "nombre_producto,descripcion_producto,precio_producto,sustancia_producto,"
							+"presentacion_producto,stock_producto,fecha_vencimiento_producto"
							+ ") values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, o.getId_producto_tipo().getId_producto_tipo());
			pstm.setInt(2, o.getId_proveedor().getId_proveedor());
			pstm.setInt(3, o.getId_comision().getId_comision());
			pstm.setInt(4, o.getId_local().getId_local());
			pstm.setString(5, o.getNombre_producto());
			pstm.setString(6, o.getDescripcion_producto());
			pstm.setDouble(7, o.getPrecio_producto());
			pstm.setString(8, o.getSustancia_producto());
			pstm.setString(9, o.getPresentacion_producto());
			pstm.setInt(10, o.getStock_producto());
			pstm.setDate(11, (Date) o.getFecha_vencimiento_producto());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_producto(generatedKeys.getInt(1));
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
	public Producto query(String id) {
		Connection cn = null;
		Producto obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql =  "select id_producto,nombre_producto,descripcion_producto,"
					+"precio_producto,sustancia_producto,presentacion_producto,stock_producto,fecha_vencimiento_producto," +
					"tp.id_producto_tipo as id_producto_tipo,tp.nombre_producto_tipo,p.id_proveedor as id_proveedor,p.nombre_proveedor,"+
					"c.id_comision_tipo as id_comision_tipo,c.nombre_comision_tipo,co.id_comision as id_comision, co.cantidad_comision as cantidad_comision,l.id_local as id_local,l.nombre_local as nombre_local"+
					" from tb_producto o inner join tb_producto_tipo tp on tp.id_producto_tipo=o.id_producto_tipo " +
					" inner join tb_proveedor p on p.id_proveedor=o.id_proveedor " +
					" inner join tb_comision co on co.id_comision=o.id_comision " +
					" inner join tb_comision_tipo c on c.id_comision_tipo=co.id_comision_tipo " +
					" inner join tb_local l on l.id_local=o.id_local"
							+ " where id_producto = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			ProductoMapper mapper = new ProductoMapper();
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
	public List<Producto> query() {
		Connection cn = null;
		List<Producto> lista = new ArrayList<Producto>();
		try {
			cn = AccesoDB.getConnection();
			String sql =  "select id_producto,nombre_producto,descripcion_producto,"
					+"precio_producto,sustancia_producto,presentacion_producto,stock_producto,fecha_vencimiento_producto," +
					"tp.id_producto_tipo as id_producto_tipo,tp.nombre_producto_tipo,p.id_proveedor as id_proveedor,p.nombre_proveedor,"+
					"c.id_comision_tipo as id_comision_tipo,c.nombre_comision_tipo,co.id_comision as id_comision, co.cantidad_comision as cantidad_comision,l.id_local as id_local,l.nombre_local as nombre_local"+
					" from tb_producto o inner join tb_producto_tipo tp on tp.id_producto_tipo=o.id_producto_tipo " +
					" inner join tb_proveedor p on p.id_proveedor=o.id_proveedor " +
					" inner join tb_comision co on co.id_comision=o.id_comision " +
					" inner join tb_comision_tipo c on c.id_comision_tipo=co.id_comision_tipo " +
					" inner join tb_local l on l.id_local=o.id_local";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ProductoMapper mapper = new ProductoMapper();
			while (rs.next()) {
				Producto obj = mapper.mapRow(rs);
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
	public List<Producto> query(Producto o) {
		Connection cn = null;
		List<Producto> lista = new ArrayList<Producto>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_producto,nombre_producto,descripcion_producto,"
						+"precio_producto,sustancia_producto,presentacion_producto,stock_producto,fecha_vencimiento_producto," +
						"tp.id_producto_tipo as id_producto_tipo,tp.nombre_producto_tipo,p.id_proveedor as id_proveedor,p.nombre_proveedor,"+
						"c.id_comision_tipo as id_comision_tipo,c.nombre_comision_tipo,co.id_comision as id_comision, co.cantidad_comision as cantidad_comision,l.id_local as id_local,l.nombre_local as nombre_local"+
						" from tb_producto o inner join tb_producto_tipo tp on tp.id_producto_tipo=o.id_producto_tipo " +
						" inner join tb_proveedor p on p.id_proveedor=o.id_proveedor " +
						" inner join tb_comision co on co.id_comision=o.id_comision " +
						" inner join tb_comision_tipo c on c.id_comision_tipo=co.id_comision_tipo " +
						" inner join tb_local l on l.id_local=o.id_local"
							+ " where nombre_producto= ? "
							+ "or descripcion_producto = ? "
							+ "or sustancia_producto = ?"
							+ "or presentacion_producto = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_producto() + "%");
			pstm.setString(2, "%" + o.getDescripcion_producto() + "%");
			pstm.setString(3, "%" + o.getSustancia_producto() + "%");
			pstm.setString(3, "%" + o.getPresentacion_producto() + "%");
			ResultSet rs = pstm.executeQuery();
			ProductoMapper mapper = new ProductoMapper();
			while (rs.next()) {
				Producto obj = mapper.mapRow(rs);
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
	public void update(Producto o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el producto
			String sql = "update tb_producto set "
							+ " id_producto_tipo = ?,"
							+ " id_proveedor = ?,"
							+ " id_comision = ?,"
							+ " id_local = ?,"
							+ " nombre_producto = ?,"
							+ " descripcion_producto = ?,"
							+ " precio_producto = ?,"
							+ " sustancia_producto = ?,"
							+ " presentacion_producto = ?,"
							+ " stock_producto = ?,"
							+ " fecha_vencimiento_producto = ? "
							+ " where id_producto = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_producto_tipo().getId_producto_tipo());
			pstm.setInt(2, o.getId_proveedor().getId_proveedor());
			pstm.setInt(3, o.getId_comision().getId_comision());
			pstm.setInt(4, o.getId_local().getId_local());
			pstm.setString(5, o.getNombre_producto());
			pstm.setString(6, o.getDescripcion_producto());
			pstm.setDouble(7, o.getPrecio_producto());
			pstm.setString(8, o.getSustancia_producto());
			pstm.setString(9, o.getPresentacion_producto());
			pstm.setInt(10, o.getStock_producto());
			pstm.setDate(11, (Date) o.getFecha_vencimiento_producto());
			pstm.setInt(12, o.getId_producto());
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
			String sql =  "delete from tb_producto where id_producto= ?";
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
