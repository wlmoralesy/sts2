package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Comprobante;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.entity.VentaDetalleReporte;

public class VentaDetalleReporteMapper implements RowMapper<VentaDetalleReporte> {

	@Override
	public VentaDetalleReporte mapRow(ResultSet rs) throws SQLException {

		VentaDetalleReporte vr = new VentaDetalleReporte();
		
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		vr.setLocal(l);
		
		Personal p = new Personal();
		p.setId_personal(rs.getInt("id_personal"));
		p.setNombre_personal(rs.getString("nombre_personal"));
		p.setApellido_personal(rs.getString("apellido_personal"));
		vr.setPersonal(p);
		
		Comprobante c = new Comprobante();
		c.setId_comprobante(rs.getInt("id_comprobante"));
		c.setNombre_comprobante(rs.getString("nombre_comprobante"));
		vr.setComprobante(c);
		
		vr.setFecha(rs.getDate("fecha"));
		vr.setTurno(rs.getString("turno"));
		vr.setCantidad(rs.getInt("cantidad"));
		vr.setTotal(rs.getDouble("total"));
		
		return vr;
	}

}
