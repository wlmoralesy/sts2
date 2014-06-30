package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Comision;
import pe.edutec.app.entity.ComisionReporte;
import pe.edutec.app.entity.ComisionTipo;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Personal;

public class ComisionReporteMapper implements RowMapper<ComisionReporte> {

	@Override
	public ComisionReporte mapRow(ResultSet rs) throws SQLException {

		ComisionReporte cr = new ComisionReporte();
		
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		cr.setLocal(l);
		
		Personal p = new Personal();
		p.setId_personal(rs.getInt("id_personal"));
		p.setNombre_personal(rs.getString("nombre_personal"));
		p.setApellido_personal(rs.getString("apellido_personal"));
		cr.setPersonal(p);
		
		cr.setFecha(rs.getDate("fecha"));
		cr.setTotal(rs.getDouble("total"));
		
		return cr;
	}

}
