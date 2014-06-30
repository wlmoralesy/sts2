package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Producto;
import pe.edutec.app.entity.Venta;
import pe.edutec.app.entity.VentaDetalle;

public class VentaDetalleMapper implements RowMapper<VentaDetalle> {

	@Override
	public VentaDetalle mapRow(ResultSet rs) throws SQLException {
		VentaDetalle v = new VentaDetalle();
		
		v.setId_venta_detalle(rs.getInt("id_venta_detalle"));
		
		Producto p = new Producto();
		p.setId_producto(rs.getInt("id_producto"));
		p.setNombre_producto(rs.getString("nombre_producto"));
		v.setId_producto(p);
		
		Venta b = new Venta();
		b.setId_venta(rs.getInt("id_venta"));
		v.setId_venta(b);
	
		
		v.setCantidad_venta_detalle(rs.getInt("cantidad_venta_detalle"));
		v.setPrecio_unitario_venta_detalle(rs.getDouble("precio_unitario_venta_detalle"));	
		v.setPrecio_total_venta_detalle(rs.getDouble("precio_total_venta_detalle"));
		return v;
	}
}
