package pe.edutec.app.entity;

import java.util.Date;

public class Caducidad {
	private int id_caducidad;
	private Personal id_personal;
	private CaducidadEstado id_caducidad_estado;
	private  Local  id_local;
	private String descripcion_caducidad;
	private Date fecha_creacion_caducidad;
	private Date fecha_limite_caducidad;
	
	public Caducidad(){}

	public int getId_caducidad() {
		return id_caducidad;
	}

	public void setId_caducidad(int id_caducidad) {
		this.id_caducidad = id_caducidad;
	}



	public Personal getId_personal() {
		return id_personal;
	}

	public void setId_personal(Personal id_personal) {
		this.id_personal = id_personal;
	}

	public CaducidadEstado getId_caducidad_estado() {
		return id_caducidad_estado;
	}

	public void setId_caducidad_estado(CaducidadEstado id_caducidad_estado) {
		this.id_caducidad_estado = id_caducidad_estado;
	}



	public Local getId_local() {
		return id_local;
	}

	public void setId_local(Local id_local) {
		this.id_local = id_local;
	}

	public String getDescripcion_caducidad() {
		return descripcion_caducidad;
	}

	public void setDescripcion_caducidad(String descripcion_caducidad) {
		this.descripcion_caducidad = descripcion_caducidad;
	}

	public Date getFecha_creacion_caducidad() {
		return fecha_creacion_caducidad;
	}

	public void setFecha_creacion_caducidad(Date fecha_creacion_caducidad) {
		this.fecha_creacion_caducidad = fecha_creacion_caducidad;
	}

	public Date getFecha_limite_caducidad() {
		return fecha_limite_caducidad;
	}

	public void setFecha_limite_caducidad(Date fecha_limite_caducidad) {
		this.fecha_limite_caducidad = fecha_limite_caducidad;
	}
	
	
	
}
