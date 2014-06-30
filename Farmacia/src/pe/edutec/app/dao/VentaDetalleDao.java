package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.VentaDetalle;
import pe.edutec.app.mapper.VentaDetalleMapper;

public class VentaDetalleDao implements IDaoCrud<VentaDetalle> {

	@Override
	public VentaDetalle insert(VentaDetalle o) {
		Connection cn = null;
		
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el VentaDetalle
			String sql = "insert into tb_venta_detalle(id_producto,id_venta,cantidad_venta_detalle,"
							+ "precio_unitario_venta_detalle,precio_total_venta_detalle)"
							+ " values(?,?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, o.getId_producto().getId_producto());
			pstm.setInt(2, o.getId_venta().getId_venta());
			pstm.setInt(3, o.getCantidad_venta_detalle());
			pstm.setDouble(4, o.getPrecio_unitario_venta_detalle());
			pstm.setDouble(5, o.getPrecio_total_venta_detalle());
			pstm.executeUpdate();
		
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_venta_detalle(generatedKeys.getInt(1));
			}
			
			pstm.close();
			// Confirmar Tx
			cn.commit();
			
			
			String sql2 = "update tb_producto set "	+ 
						" stock_producto = stock_producto - ? "	+ 
						" where id_producto = ? ";
			PreparedStatement pstm2 = cn.prepareStatement(sql2);			
			pstm2.setInt(1, o.getCantidad_venta_detalle());
			pstm2.setInt(2, o.getId_producto().getId_producto());
			pstm2.executeUpdate();
			pstm2.close();
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
			} catch (Exception e) {}
		}
	}

	@Override
	public VentaDetalle query(String id) {
		Connection cn = null;
		VentaDetalle obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_venta_detalle,cantidad_venta_detalle,precio_unitario_venta_detalle,"
							+ " precio_total_venta_detalle," 
							+ " p.id_producto as id_producto, p.nombre_producto as nombre_producto," 
							+ " b.id_venta as id_venta"
							+ " from tb_venta_detalle v "
							+ " inner join tb_produto p on p.id_producto = v.id_producto "
							+ " inner join tb_venta b on b.id_venta = v.id_venta "
							+ " where id_venta = ? ";
			
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.valueOf(id));
			ResultSet rs = pstm.executeQuery();
			VentaDetalleMapper mapper = new VentaDetalleMapper();
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
	public List<VentaDetalle> query() {
		Connection cn = null;
		List<VentaDetalle> lista = new ArrayList<VentaDetalle>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_venta_detalle,cantidad_venta_detalle,precio_unitario_venta_detalle,"
					+ " precio_total_venta_detalle," 
					+ " p.id_producto as id_producto, p.nombre_producto as nombre_producto," 
					+ " b.id_venta as id_venta"
					+ " from tb_venta_detalle v "
					+ " inner join tb_produto p on p.id_producto = v.id_producto "
					+ " inner join tb_venta b on b.id_venta = v.id_venta ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			VentaDetalleMapper mapper = new VentaDetalleMapper();
			while (rs.next()) {
				VentaDetalle obj = mapper.mapRow(rs);
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
	public List<VentaDetalle> query(VentaDetalle o) {
		Connection cn = null;
		List<VentaDetalle> lista = new ArrayList<VentaDetalle>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_venta_detalle,cantidad_venta_detalle,precio_unitario_venta_detalle,precio_total_venta_detalle, " 
					+ " p.id_producto as id_producto, p.nombre_producto as nombre_producto, "  
					+ " b.id_venta as id_venta "
					+ " from tb_venta_detalle v "
					+ " inner join tb_producto p on p.id_producto = v.id_producto "
					+ " inner join tb_venta b on b.id_venta = v.id_venta "
					+ " where v.id_venta = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_venta().getId_venta());
			ResultSet rs = pstm.executeQuery();
			VentaDetalleMapper mapper = new VentaDetalleMapper();
			while (rs.next()) {
				VentaDetalle obj = mapper.mapRow(rs);
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
	public void update(VentaDetalle o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el VentaDetalle
			String sql = "update tb_venta_detalle set "
							+ "id_producto = ?,"
							+ "id_venta = ?,"
							+ "cantidad_venta_detalle = ?,"
							+ "precio_unitario_detalle = ?,"
							+ "precio_total_venta_detalle = ?"
							+ " where id_venta_detalle = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_producto().getId_producto());
			pstm.setInt(2, o.getId_venta().getId_venta());
			pstm.setInt(3, o.getCantidad_venta_detalle());
			pstm.setDouble(4, o.getPrecio_unitario_venta_detalle() );
			pstm.setDouble(5, o.getPrecio_total_venta_detalle());
			pstm.setInt(6, o.getId_venta_detalle());
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
			String sql = "delete from tb_venta_detalle where id_venta_detalle= ?";
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
