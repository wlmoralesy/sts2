package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.CaducidadEstado;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Caducidad;
import pe.edutec.app.entity.Personal;

public class CaducidadMapper implements RowMapper<Caducidad> {

	@Override
	public Caducidad mapRow(ResultSet rs) throws SQLException {
		Caducidad d = new Caducidad();
		
		d.setId_caducidad(rs.getInt("id_caducidad"));
		
		Personal p = new Personal();
		p.setId_personal(rs.getInt("id_personal"));
		p.setDni_personal(rs.getString("dni_personal"));
		p.setNombre_personal(rs.getString("nombre_personal"));
		p.setApellido_personal(rs.getString("apellido_personal"));
		d.setId_personal(p);
		
		CaducidadEstado e = new  CaducidadEstado();
		e.setId_caducidad_estado(rs.getInt("id_caducidad_estado"));
		e.setNombre_caducidad_estado(rs.getString("nombre_caducidad_estado"));
		d.setId_caducidad_estado(e);
		Local l = new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		d.setId_local(l);
		
		d.setDescripcion_caducidad(rs.getString("descripcion_caducidad"));
		d.setFecha_creacion_caducidad(rs.getDate("fecha_creacion_caducidad"));
		d.setFecha_limite_caducidad(rs.getDate("fecha_limite_caducidad"));
			
		return d;
	}
}
