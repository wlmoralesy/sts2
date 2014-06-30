package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.CaducidadDetalle;
import pe.edutec.app.entity.Producto;

public class CaducidadDetalleMapper implements RowMapper<CaducidadDetalle> {

	@Override
	public CaducidadDetalle mapRow(ResultSet rs) throws SQLException {
		CaducidadDetalle c = new CaducidadDetalle();
		c.setId_caducidad_detalle(rs.getInt("id_caducidad_detalle"));
		
		Producto p = new Producto();
		p.setId_producto(rs.getInt("id_producto"));
		p.setNombre_producto(rs.getString("nombre_producto"));		
		c.setId_producto(p);
		
		c.setId_caducidad(rs.getInt("id_caducidad"));
		c.setCantidad_caducidad_detalle(rs.getInt("cantidad_caducidad_detalle"));
		
		return c;
	}
}
