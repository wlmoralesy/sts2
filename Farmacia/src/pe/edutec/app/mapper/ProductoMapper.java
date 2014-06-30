package pe.edutec.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edutec.app.entity.Comision;
import pe.edutec.app.entity.ComisionTipo;
import pe.edutec.app.entity.Local;
import pe.edutec.app.entity.Producto;
import pe.edutec.app.entity.ProductoTipo;
import pe.edutec.app.entity.Proveedor;

public class ProductoMapper implements RowMapper<Producto> {

	@Override
	public Producto mapRow(ResultSet rs) throws SQLException {
		
		ProductoTipo t= new ProductoTipo();
		t.setId_producto_tipo(rs.getInt("id_producto_tipo"));
		t.setNombre_producto_tipo(rs.getString("nombre_producto_tipo"));
		Proveedor p=new Proveedor();
		p.setId_proveedor(rs.getInt("id_proveedor"));
		p.setNombre_proveedor(rs.getString("nombre_proveedor"));
		
		ComisionTipo ct=new ComisionTipo();
		ct.setId_comision_tipo(rs.getInt("id_comision_tipo"));
		ct.setNombre_comision_tipo(rs.getString("nombre_comision_tipo"));
		
		Comision o=new Comision();
		o.setId_comision(rs.getInt("id_comision"));
		o.setId_comision_tipo(ct);
		o.setCantidad_comision(rs.getDouble("cantidad_comision"));
		
		Local l=new Local();
		l.setId_local(rs.getInt("id_local"));
		l.setNombre_local(rs.getString("nombre_local"));
		
		Producto c = new Producto();
		c.setId_producto(rs.getInt("id_producto"));
		c.setId_producto_tipo(t);
		c.setId_proveedor(p);
		c.setId_comision(o);
		c.setId_local(l);
		c.setNombre_producto(rs.getString("nombre_producto"));
		c.setDescripcion_producto(rs.getString("descripcion_producto"));
		c.setPrecio_producto(rs.getDouble("precio_producto"));
		c.setSustancia_producto(rs.getString("sustancia_producto"));
		c.setPresentacion_producto(rs.getString("presentacion_producto"));
		c.setStock_producto(rs.getInt("stock_producto"));
		c.setFecha_vencimiento_producto(rs.getDate("fecha_vencimiento_producto"));

		return c;
	}
}
