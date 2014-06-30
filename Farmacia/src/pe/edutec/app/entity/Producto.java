package pe.edutec.app.entity;

import java.util.Date;

public class Producto {
	private int id_producto;
	private ProductoTipo id_producto_tipo;
	private Proveedor id_proveedor;
	private Comision id_comision;
	private Local  id_local;
	private String nombre_producto;
	private String descripcion_producto;
	private double precio_producto;
	private String sustancia_producto;
	private String presentacion_producto;
	private int stock_producto;
	private Date fecha_vencimiento_producto;
	
	public Producto(){}
	
	public Producto(int id_producto){
		this.id_producto = id_producto;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	

	public ProductoTipo getId_producto_tipo() {
		return id_producto_tipo;
	}

	public void setId_producto_tipo(ProductoTipo id_producto_tipo) {
		this.id_producto_tipo = id_producto_tipo;
	}

	public Proveedor getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(Proveedor id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public Comision getId_comision() {
		return id_comision;
	}

	public void setId_comision(Comision id_comision) {
		this.id_comision = id_comision;
	}

	public Local getId_local() {
		return id_local;
	}

	public void setId_local(Local id_local) {
		this.id_local = id_local;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public String getDescripcion_producto() {
		return descripcion_producto;
	}

	public void setDescripcion_producto(String descripcion_producto) {
		this.descripcion_producto = descripcion_producto;
	}

	public double getPrecio_producto() {
		return precio_producto;
	}

	public void setPrecio_producto(double precio_producto) {
		this.precio_producto = precio_producto;
	}

	public String getSustancia_producto() {
		return sustancia_producto;
	}

	public void setSustancia_producto(String sustancia_producto) {
		this.sustancia_producto = sustancia_producto;
	}

	public String getPresentacion_producto() {
		return presentacion_producto;
	}

	public void setPresentacion_producto(String presentacion_producto) {
		this.presentacion_producto = presentacion_producto;
	}

	public int getStock_producto() {
		return stock_producto;
	}

	public void setStock_producto(int stock_producto) {
		this.stock_producto = stock_producto;
	}

	public Date getFecha_vencimiento_producto() {
		return fecha_vencimiento_producto;
	}

	public void setFecha_vencimiento_producto(Date fecha_vencimiento_producto) {
		this.fecha_vencimiento_producto = fecha_vencimiento_producto;
	}
	
}
