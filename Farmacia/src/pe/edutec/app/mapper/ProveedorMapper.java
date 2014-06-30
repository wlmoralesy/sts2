package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Proveedor;

public class ProveedorMapper implements RowMapper<Proveedor> {

	@Override
	public Proveedor mapRow(ResultSet rs) throws SQLException {
		Proveedor c = new Proveedor();
		c.setId_proveedor(rs.getInt("id_proveedor"));
		c.setRuc_proveedor(rs.getString("ruc_proveedor"));
		c.setNombre_proveedor(rs.getString("nombre_proveedor"));
		c.setTelefono_proveedor(rs.getString("telefono_proveedor"));
		c.setDireccion_proveedor(rs.getString("direccion_proveedor"));
		c.setLaboratorio(rs.getBoolean("laboratorio"));
		
		return c;
	}
}
