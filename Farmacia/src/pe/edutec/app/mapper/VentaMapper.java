package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Cliente;
import pe.edutec.app.entity.Comprobante;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.Venta;

public class VentaMapper implements RowMapper<Venta> {

	@Override
	public Venta mapRow(ResultSet rs) throws SQLException {
		Venta v = new Venta();
		v.setId_venta(rs.getInt("id_venta"));
		
		Personal p = new Personal();
		p.setId_personal(rs.getInt("id_personal"));
		p.setNombre_personal(rs.getString("nombre_personal"));
		p.setApellido_personal(rs.getString("apellido_personal"));
		v.setId_personal(p);
		
		Cliente c = new Cliente();
		c.setId_cliente(rs.getInt("id_cliente"));
		c.setRuc_cliente(rs.getString("ruc_cliente"));
		c.setNombre_cliente(rs.getString("nombre_cliente"));
		c.setApellido_cliente(rs.getString("apellido_cliente"));
		v.setId_cliente(c);
		
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		v.setId_local(l);
		
		Comprobante m=new Comprobante();
		m.setId_comprobante(rs.getInt("id_comprobante"));
		m.setNombre_comprobante(rs.getString("nombre_comprobante"));
		v.setId_comprobante(m);
		
		v.setNumero_venta(rs.getString("numero_venta"));
		v.setFecha_venta(rs.getDate("fecha_venta"));
		v.setDescripcion_venta(rs.getString("descripcion_venta"));
		v.setTotal_venta(rs.getDouble("total_venta"));
		return v;
	}
}
