package pe.edutec.app.entity;

public class CaducidadDetalle {
	private  int id_caducidad_detalle;
	private  Producto id_producto;
	private  int id_caducidad;
	private  int cantidad_caducidad_detalle;
	
	public CaducidadDetalle(){}

	public int getId_caducidad_detalle() {
		return id_caducidad_detalle;
	}

	public void setId_caducidad_detalle(int id_caducidad_detalle) {
		this.id_caducidad_detalle = id_caducidad_detalle;
	}

	public Producto getId_producto() {
		return id_producto;
	}

	public void setId_producto(Producto id_producto) {
		this.id_producto = id_producto;
	}

	public int getId_caducidad() {
		return id_caducidad;
	}

	public void setId_caducidad(int id_caducidad) {
		this.id_caducidad = id_caducidad;
	}

	public int getCantidad_caducidad_detalle() {
		return cantidad_caducidad_detalle;
	}

	public void setCantidad_caducidad_detalle(int cantidad_caducidad_detalle) {
		this.cantidad_caducidad_detalle = cantidad_caducidad_detalle;
	}
	
}
