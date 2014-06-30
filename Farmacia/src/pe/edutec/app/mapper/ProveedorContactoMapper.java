package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Proveedor;
import pe.edutec.app.entity.ProveedorContacto;

public class ProveedorContactoMapper implements RowMapper<ProveedorContacto> {

	@Override
	public ProveedorContacto mapRow(ResultSet rs) throws SQLException {
		
		Proveedor p = new Proveedor();
		p.setId_proveedor(rs.getInt("id_proveedor"));
		p.setNombre_proveedor(rs.getString("nombre_proveedor"));
		
		ProveedorContacto c = new ProveedorContacto();
		c.setId_proveedor_contacto(rs.getInt("id_proveedor_contacto"));
		c.setId_proveedor(p);
		c.setDni_proveedor_contacto(rs.getString("dni_proveedor_contacto"));
		c.setNombre_proveedor_contacto(rs.getString("nombre_proveedor_contacto"));
		c.setApellido_proveedor_contacto(rs.getString("apellido_proveedor_contacto"));
		c.setTelefono_proveedor_contacto(rs.getString("telefono_proveedor_contacto"));
		c.setCelular_proveedor_contacto(rs.getString("celular_proveedor_contacto"));
		
		return c;
	}
}
