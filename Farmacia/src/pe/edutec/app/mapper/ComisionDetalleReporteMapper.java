package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Comision;
import pe.edutec.app.entity.ComisionDetalleReporte;
import pe.edutec.app.entity.ComisionTipo;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.Producto;

public class ComisionDetalleReporteMapper implements RowMapper<ComisionDetalleReporte> {

	@Override
	public ComisionDetalleReporte mapRow(ResultSet rs) throws SQLException {

		ComisionDetalleReporte cr = new ComisionDetalleReporte();
		
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		cr.setLocal(l);
		
		Personal p = new Personal();
		p.setId_personal(rs.getInt("id_personal"));
		p.setNombre_personal(rs.getString("nombre_personal"));
		p.setApellido_personal(rs.getString("apellido_personal"));
		cr.setPersonal(p);
		
		Comision c = new Comision();
		c.setId_comision(rs.getInt("id_comision"));
		c.setCantidad_comision(rs.getDouble("cantidad_comision"));		
		ComisionTipo ct = new ComisionTipo();
		ct.setId_comision_tipo(rs.getInt("id_comision_tipo"));
		ct.setNombre_comision_tipo(rs.getString("nombre_comision_tipo"));
		c.setId_comision_tipo(ct);		
		cr.setComision(c);
		
		Producto vp = new Producto();
		vp.setId_producto(rs.getInt("id_producto"));
		vp.setNombre_producto(rs.getString("nombre_producto"));
		cr.setProducto(vp);
		
		cr.setVentas(rs.getInt("ventas"));
		cr.setFecha(rs.getDate("fecha"));
		cr.setCantidad(rs.getInt("cantidad"));
		cr.setPrecio(rs.getDouble("precio"));
		cr.setTotal(rs.getDouble("total"));
		
		return cr;
	}

}
