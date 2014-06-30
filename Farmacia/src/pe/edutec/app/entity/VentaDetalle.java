package pe.edutec.app.entity;

public class VentaDetalle {
	private int id_venta_detalle;
	private Producto id_producto;
	private Venta id_venta;
	private  int cantidad_venta_detalle;
	private double precio_unitario_venta_detalle;
	private double precio_total_venta_detalle;
	
	public VentaDetalle(){}

	public int getId_venta_detalle() {
		return id_venta_detalle;
	}

	public void setId_venta_detalle(int id_venta_detalle) {
		this.id_venta_detalle = id_venta_detalle;
	}


	public Producto getId_producto() {
		return id_producto;
	}

	public void setId_producto(Producto id_producto) {
		this.id_producto = id_producto;
	}

	public Venta getId_venta() {
		return id_venta;
	}

	public void setId_venta(Venta id_venta) {
		this.id_venta = id_venta;
	}

	public int getCantidad_venta_detalle() {
		return cantidad_venta_detalle;
	}

	public void setCantidad_venta_detalle(int cantidad_venta_detalle) {
		this.cantidad_venta_detalle = cantidad_venta_detalle;
	}

	public double getPrecio_unitario_venta_detalle() {
		return precio_unitario_venta_detalle;
	}

	public void setPrecio_unitario_venta_detalle(
			double precio_unitario_venta_detalle) {
		this.precio_unitario_venta_detalle = precio_unitario_venta_detalle;
	}

	public double getPrecio_total_venta_detalle() {
		return precio_total_venta_detalle;
	}

	public void setPrecio_total_venta_detalle(double precio_total_venta_detalle) {
		this.precio_total_venta_detalle = precio_total_venta_detalle;
	}
	
}
