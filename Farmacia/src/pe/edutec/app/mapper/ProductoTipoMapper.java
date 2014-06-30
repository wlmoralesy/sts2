package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edutec.app.entity.ProductoTipo;

public class ProductoTipoMapper implements RowMapper<ProductoTipo> {

	@Override
	public ProductoTipo mapRow(ResultSet rs) throws SQLException {
		ProductoTipo c = new ProductoTipo();
		c.setId_producto_tipo(rs.getInt("id_producto_tipo"));
		c.setNombre_producto_tipo(rs.getString("nombre_producto_tipo"));
		
		return c;
	}
}
