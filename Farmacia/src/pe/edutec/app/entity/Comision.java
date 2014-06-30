package pe.edutec.app.entity;

public class Comision {

	private int id_comision;
	private ComisionTipo id_comision_tipo;
	private double cantidad_comision;
	
	public Comision(){}
	
	public Comision(int id_comision){
		this.id_comision = id_comision;
	}

	public int getId_comision() {
		return id_comision;
	}

	public void setId_comision(int id_comision) {
		this.id_comision = id_comision;
	}


	public ComisionTipo getId_comision_tipo() {
		return id_comision_tipo;
	}

	public void setId_comision_tipo(ComisionTipo id_comision_tipo) {
		this.id_comision_tipo = id_comision_tipo;
	}

	public double getCantidad_comision() {
		return cantidad_comision;
	}

	public void setCantidad_comision(double cantidad_comision) {
		this.cantidad_comision = cantidad_comision;
	}
	
}
