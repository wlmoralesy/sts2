package pe.edutec.app.entity;

public class Comprobante {
	private int id_comprobante;
	private String nombre_comprobante;
	private String formato_comprobante;
	private int correlativo_primario_comprobante;
	private int correlativo_secundario_comprobante;
	
	public Comprobante(){}
	
	public Comprobante(int id_comprobante){
		this.id_comprobante = id_comprobante;
	}

	public int getId_comprobante() {
		return id_comprobante;
	}

	public void setId_comprobante(int id_comprobante) {
		this.id_comprobante = id_comprobante;
	}

	public String getNombre_comprobante() {
		return nombre_comprobante;
	}

	public void setNombre_comprobante(String nombre_comprobante) {
		this.nombre_comprobante = nombre_comprobante;
	}

	public String getFormato_comprobante() {
		return formato_comprobante;
	}

	public void setFormato_comprobante(String formato_comprobante) {
		this.formato_comprobante = formato_comprobante;
	}

	public int getCorrelativo_primario_comprobante() {
		return correlativo_primario_comprobante;
	}

	public void setCorrelativo_primario_comprobante(
			int correlativo_primario_comprobante) {
		this.correlativo_primario_comprobante = correlativo_primario_comprobante;
	}

	public int getCorrelativo_secundario_comprobante() {
		return correlativo_secundario_comprobante;
	}

	public void setCorrelativo_secundario_comprobante(
			int correlativo_secundario_comprobante) {
		this.correlativo_secundario_comprobante = correlativo_secundario_comprobante;
	}
	
}
