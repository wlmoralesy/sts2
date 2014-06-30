package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.ComisionDetalleReporte;
import pe.edutec.app.entity.ComisionReporte;
import pe.edutec.app.entity.Venta;
import pe.edutec.app.entity.VentaDetalleReporte;
import pe.edutec.app.entity.VentaReporte;
import pe.edutec.app.mapper.ComisionDetalleReporteMapper;
import pe.edutec.app.mapper.ComisionReporteMapper;
import pe.edutec.app.mapper.VentaDetalleReporteMapper;
import pe.edutec.app.mapper.VentaMapper;
import pe.edutec.app.mapper.VentaReporteMapper;

public class VentaDao implements IDaoCrud<Venta>,IReporte {

	private static java.sql.Timestamp getCurrentTimeStamp() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());
	}
	
	@Override
	public Venta insert(Venta o) {
		Connection cn = null;
		
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el Venta
			String sql = "insert into tb_venta(id_personal,id_cliente,id_local,numero_venta,"
							+ "fecha_venta,descripcion_venta,total_venta,id_comprobante)"
							+ " values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, o.getId_personal().getId_personal());
			pstm.setInt(2, o.getId_cliente().getId_cliente());
			pstm.setInt(3, o.getId_local().getId_local());
			pstm.setString(4, o.getNumero_venta());
			pstm.setTimestamp(5, getCurrentTimeStamp());
			pstm.setString(6, o.getDescripcion_venta());
			pstm.setDouble(7, o.getTotal_venta());
			pstm.setInt(8, o.getId_comprobante().getId_comprobante());
			pstm.executeUpdate();
		
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_venta(generatedKeys.getInt(1));
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
			} catch (Exception e) {}
		}
	}

	@Override
	public Venta query(String id) {
		Connection cn = null;
		Venta obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_venta,numero_venta,fecha_venta,descripcion_venta,total_venta," +
						" p.id_personal as id_personal, p.nombre_personal as nombre_personal, p.apellido_personal as apellido_personal," +
						" c.id_cliente as id_cliente, c.ruc_cliente as ruc_cliente, c.nombre_cliente as nombre_cliente,c.apellido_cliente as apellido_cliente," +
						" l.id_local as id_local, l.nombre_local as nombre_local," +
						" co.id_comprobante as id_comprobante, co.nombre_comprobante as nombre_comprobante " +
						" from tb_venta v " + 
						" inner join tb_personal p on p.id_personal = v.id_personal " +
						" inner join tb_cliente c on c.id_cliente = v.id_cliente " +
						" inner join tb_local l on l.id_local = v.id_local " +
						" inner join tb_comprobante co on co.id_comprobante=v.id_comprobante" + 
						" where id_venta = ? ";
			
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			VentaMapper mapper = new VentaMapper();
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
	public List<Venta> query() {
		Connection cn = null;
		List<Venta> lista = new ArrayList<Venta>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_venta,numero_venta,fecha_venta,descripcion_venta,total_venta," +
					" p.id_personal as id_personal, p.nombre_personal as nombre_personal, p.apellido_personal as apellido_personal," +
					" c.id_cliente as id_cliente, c.ruc_cliente as ruc_cliente, c.nombre_cliente as nombre_cliente,c.apellido_cliente as apellido_cliente," +
					" l.id_local as id_local, l.nombre_local as nombre_local," +
					" co.id_comprobante as id_comprobante, co.nombre_comprobante as nombre_comprobante " +
					" from tb_venta v " + 
					" inner join tb_personal p on p.id_personal = v.id_personal " +
					" inner join tb_cliente c on c.id_cliente = v.id_cliente " +
					" inner join tb_local l on l.id_local = v.id_local " +
					" inner join tb_comprobante co on co.id_comprobante=v.id_comprobante" + 
					" order by 1 desc ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			VentaMapper mapper = new VentaMapper();
			while (rs.next()) {
				Venta obj = mapper.mapRow(rs);
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
	public List<Venta> query(Venta o) {
		Connection cn = null;
		List<Venta> lista = new ArrayList<Venta>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_venta,numero_venta,fecha_venta,descripcion_venta,total_venta,"
						+"p.id_personal as id_personal, p.nombre_personal as nombre_personal, p.apellido_personal as apellido_personal," +
						"c.id_cliente as id_cliente, c.nombre_cliente as nombre_cliente,c.apellido_cliente as apellido_cliente," +
						"c.ruc_cliente as ruc_cliente,l.id_local as id_local, l.nombre_local as nombre_local ,co.id_comprobante as id_comprobante," +
						"co.nombre_comprobante as nombre_comprobante from tb_venta v inner join tb_personal p on p.id_personal = v.id_personal " +
						"inner join tb_cliente c on c.id_cliente = v.id_cliente " +
						"inner join tb_local l on l.id_local = v.id_local " +
						"inner join tb_comprobante co on co.id_comprobante=v.id_comprobante"
							+ "where numero_venta like ? "
							+ "or fecha_venta like ? "
							+ "or descripcion_venta like ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNumero_venta() + "%");
			pstm.setString(2, "%" + o.getFecha_venta() + "%");
			pstm.setString(3, "%" + o.getDescripcion_venta()+ "%");
			ResultSet rs = pstm.executeQuery();
			VentaMapper mapper = new VentaMapper();
			while (rs.next()) {
				Venta obj = mapper.mapRow(rs);
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
	public void update(Venta o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el Venta
			String sql = "update tb_venta set "
							+ "id_personal = ?,"
							+ "id_cliente = ?,"
							+ "id_local = ?,"
							+ "id_comprobante=?"
							+ "numero_venta = ?,"
							+ "fecha_venta = ?,"
							+ "descripcion_venta = ?,"
							+ "total_venta = ? "
							+ "where id_venta = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_personal().getId_personal());
			pstm.setInt(2, o.getId_cliente().getId_cliente());
			pstm.setInt(3, o.getId_local().getId_local());
			pstm.setInt(4, o.getId_comprobante().getId_comprobante());
			pstm.setString(5, o.getNumero_venta());
			pstm.setDate(6, (Date) o.getFecha_venta());
			pstm.setString(7, o.getDescripcion_venta());
			pstm.setDouble(8, o.getTotal_venta());
			pstm.setInt(9, o.getId_venta());
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
			String sql = "delete from tb_venta where id_venta= ?";
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

	@Override
	public List<VentaReporte> reporte(Date fecha, int local) {
		Connection cn = null;
		List<VentaReporte> lista = new ArrayList<VentaReporte>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select l.id_local,l.nombre_local, " +
					" date(v.fecha_venta) fecha, " +
					" case when v.fecha_venta > '" + fecha.toString() + " 00:00:00' and v.fecha_venta < '" + fecha.toString() + " 12:00:00' then '1. Mañana' " +
					" when v.fecha_venta > '" + fecha.toString() + " 12:00:01' and v.fecha_venta < '" + fecha.toString() + " 18:00:00' then '2. Tarde' " +
					" else '3. Noche' end turno, " +
					" count(v.id_venta) cantidad, " +
					" sum(v.total_venta) total " +
					" from tb_venta v " +
					" inner join tb_local l on l.id_local = v.id_local " +
					" where date(v.fecha_venta) = ? " + 
					" and l.id_local = ? " +
					" group by l.nombre_local,fecha,turno " +
					" order by turno";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setDate(1, new Date(fecha.getTime()));
			pstm.setInt(2, local);
			ResultSet rs = pstm.executeQuery();
			VentaReporteMapper mapper = new VentaReporteMapper();
			while (rs.next()) {
				VentaReporte obj = mapper.mapRow(rs);
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
	public List<VentaDetalleReporte> reporteDetalle(Date fecha, int local) {
		Connection cn = null;
		List<VentaDetalleReporte> lista = new ArrayList<VentaDetalleReporte>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select l.id_local,l.nombre_local, " +
					" c.id_comprobante,c.nombre_comprobante, " +
					" p.id_personal,p.nombre_personal,p.apellido_personal, " +
					" date(v.fecha_venta) fecha, " +
					" case when v.fecha_venta > '" + fecha.toString() + " 00:00:00' and v.fecha_venta < '" + fecha.toString() + " 12:00:00' then '1. Mañana' " +
					" when v.fecha_venta > '" + fecha.toString() + " 12:00:01' and v.fecha_venta < '" + fecha.toString() + " 18:00:00' then '2. Tarde' " +
					" else '3. Noche' end turno, " +
					" count(v.id_venta) cantidad, " +
					" sum(v.total_venta) total " +
					" from tb_venta v " +
					" inner join tb_local l on l.id_local = v.id_local " +
					" inner join tb_comprobante c on c.id_comprobante = v.id_comprobante " +					
					" inner join tb_personal p on p.id_personal = v.id_personal " +
					" where date(v.fecha_venta) = ? " + 
					" and l.id_local = ? " +
					" group by l.id_local,l.nombre_local," +
					" c.id_comprobante,c.nombre_comprobante, " +
					" p.id_personal,p.nombre_personal,p.apellido_personal, " +
					" fecha, turno " +
					" order by turno";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setDate(1, new Date(fecha.getTime()));
			pstm.setInt(2, local);
			ResultSet rs = pstm.executeQuery();
			VentaDetalleReporteMapper mapper = new VentaDetalleReporteMapper();
			while (rs.next()) {
				VentaDetalleReporte obj = mapper.mapRow(rs);
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
	public List<ComisionReporte> reporte(Date fecha1, Date fecha2, int local) {
		Connection cn = null;
		List<ComisionReporte> lista = new ArrayList<ComisionReporte>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select l.id_local,l.nombre_local, " +
					" p.id_personal, p.nombre_personal, p.apellido_personal, " +
					" date(v.fecha_venta) fecha, " +
					" round(sum(case when c.id_comision_tipo = 1 then c.cantidad_comision " +
					" when c.id_comision_tipo = 2 then vd.cantidad_venta_detalle*c.cantidad_comision " +
					" else vd.precio_total_venta_detalle*c.cantidad_comision end),1) total " +
					" from tb_venta v " +
					" inner join tb_local l on l.id_local = v.id_local " +
					" inner join tb_personal p on p.id_personal = v.id_personal " +
					" inner join tb_venta_detalle vd on vd.id_venta = v.id_venta " +
					" inner join tb_producto vp on vp.id_producto = vd.id_producto " +
					" inner join tb_comision c on c.id_comision = vp.id_comision " +
					" inner join tb_comision_tipo ct on ct.id_comision_tipo = c.id_comision_tipo " +
					" where date(v.fecha_venta) between ? and ? and v.id_local = ? " +
					" group by p.id_personal, p.nombre_personal, p.apellido_personal, " +
					" fecha ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setDate(1, new Date(fecha1.getTime()));
			pstm.setDate(2, new Date(fecha2.getTime()));
			pstm.setInt(3, local);
			ResultSet rs = pstm.executeQuery();
			ComisionReporteMapper mapper = new ComisionReporteMapper();
			while (rs.next()) {
				ComisionReporte obj = mapper.mapRow(rs);
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
	public List<ComisionDetalleReporte> reporteDetalle(Date fecha1, Date fecha2,int local) {
		Connection cn = null;
		List<ComisionDetalleReporte> lista = new ArrayList<ComisionDetalleReporte>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select l.id_local,l.nombre_local, " +
					" p.id_personal, p.nombre_personal, p.apellido_personal, " +
					" c.id_comision,c.cantidad_comision, " +
					" ct.id_comision_tipo,ct.nombre_comision_tipo, " +
					" vp.id_producto,vp.nombre_producto, " +
					" sum(vd.cantidad_venta_detalle) cantidad, " +
					" vd.precio_unitario_venta_detalle precio, " +
					" count(v.id_venta) ventas, " +
					" date(v.fecha_venta) fecha, " +
					" case when c.id_comision_tipo = 1 then round(count(vd.id_venta_detalle)*c.cantidad_comision,1) " +
					" when c.id_comision_tipo = 2 then round(sum(vd.cantidad_venta_detalle*c.cantidad_comision),1) " +
					" else round(sum(vd.precio_total_venta_detalle*c.cantidad_comision),1) end total " +
					" from tb_venta v " +
					" inner join tb_local l on l.id_local = v.id_local " +
					" inner join tb_personal p on p.id_personal = v.id_personal " +
					" inner join tb_venta_detalle vd on vd.id_venta = v.id_venta " +
					" inner join tb_producto vp on vp.id_producto = vd.id_producto " +
					" inner join tb_comision c on c.id_comision = vp.id_comision " +
					" inner join tb_comision_tipo ct on ct.id_comision_tipo = c.id_comision_tipo " +
					" where date(v.fecha_venta) between ? and ? and v.id_local = ? " +
					" group by p.id_personal, p.nombre_personal, p.apellido_personal, " +
					" c.id_comision,c.cantidad_comision, " +
					" vp.id_producto,vp.nombre_producto, " +
					" ct.id_comision_tipo,ct.nombre_comision_tipo,fecha,precio; ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setDate(1, new Date(fecha1.getTime()));
			pstm.setDate(2, new Date(fecha2.getTime()));
			pstm.setInt(3, local);
			ResultSet rs = pstm.executeQuery();
			ComisionDetalleReporteMapper mapper = new ComisionDetalleReporteMapper();
			while (rs.next()) {
				ComisionDetalleReporte obj = mapper.mapRow(rs);
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
}
