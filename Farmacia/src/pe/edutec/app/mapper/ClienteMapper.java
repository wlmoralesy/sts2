package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.Cliente;

public class ClienteMapper implements RowMapper<Cliente> {

	@Override
	public Cliente mapRow(ResultSet rs) throws SQLException {
		Cliente c = new Cliente();
		c.setId_cliente(rs.getInt("id_cliente"));
		c.setRuc_cliente(rs.getString("ruc_cliente"));
		c.setNombre_cliente(rs.getString("nombre_cliente"));
		c.setApellido_cliente(rs.getString("apellido_cliente"));
		c.setDireccion_cliente(rs.getString("direccion_cliente"));
		return c;
	}
}
