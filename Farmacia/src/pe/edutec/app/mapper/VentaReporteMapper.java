package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.VentaReporte;

public class VentaReporteMapper implements RowMapper<VentaReporte> {

	@Override
	public VentaReporte mapRow(ResultSet rs) throws SQLException {

		VentaReporte vr = new VentaReporte();
		
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		
		vr.setLocal(l);
		vr.setFecha(rs.getDate("fecha"));
		vr.setTurno(rs.getString("turno"));
		vr.setCantidad(rs.getInt("cantidad"));
		vr.setTotal(rs.getDouble("total"));
		
		return vr;
	}

}
