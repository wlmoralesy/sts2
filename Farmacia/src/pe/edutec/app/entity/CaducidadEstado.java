package pe.edutec.app.entity;

public class CaducidadEstado {
	private int id_caducidad_estado;
	private String nombre_caducidad_estado;
	
	public CaducidadEstado(){}
	
	public CaducidadEstado(int id_caducidad_estado, String nombre_caducidad_estado){
		this.id_caducidad_estado = id_caducidad_estado;
		this.nombre_caducidad_estado = nombre_caducidad_estado;
	}

	public int getId_caducidad_estado() {
		return id_caducidad_estado;
	}

	public void setId_caducidad_estado(int id_caducidad_estado) {
		this.id_caducidad_estado = id_caducidad_estado;
	}

	public String getNombre_caducidad_estado() {
		return nombre_caducidad_estado;
	}

	public void setNombre_caducidad_estado(String nombre_caducidad_estado) {
		this.nombre_caducidad_estado = nombre_caducidad_estado;
	}
	
	
}
