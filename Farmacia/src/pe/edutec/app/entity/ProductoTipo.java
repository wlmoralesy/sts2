package pe.edutec.app.entity;

public class ProductoTipo {
	private int id_producto_tipo;
	private String nombre_producto_tipo;
	
	public ProductoTipo(){}
	
	public ProductoTipo(int id_producto_tipo){
		this.id_producto_tipo = id_producto_tipo;
	}

	public int getId_producto_tipo() {
		return id_producto_tipo;
	}

	public void setId_producto_tipo(int id_producto_tipo) {
		this.id_producto_tipo = id_producto_tipo;
	}

	public String getNombre_producto_tipo() {
		return nombre_producto_tipo;
	}

	public void setNombre_producto_tipo(String nombre_producto_tipo) {
		this.nombre_producto_tipo = nombre_producto_tipo;
	}
	
	
}
