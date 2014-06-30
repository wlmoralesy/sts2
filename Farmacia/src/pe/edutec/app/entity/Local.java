package pe.edutec.app.entity;

public class Local {
	private int id_local;
	private String nombre_local;
	private String direccion_local;
	private String telefono_local;
	
	public Local(){}
	
	public Local(int id_local){
		this.id_local = id_local;
	}
	
	public int getId_local() {
		return id_local;
	}
	public void setId_local(int id_local) {
		this.id_local = id_local;
	}
	public String getNombre_local() {
		return nombre_local;
	}
	public void setNombre_local(String nombre_local) {
		this.nombre_local = nombre_local;
	}
	public String getDireccion_local() {
		return direccion_local;
	}
	public void setDireccion_local(String direccion_local) {
		this.direccion_local = direccion_local;
	}
	public String getTelefono_local() {
		return telefono_local;
	}
	public void setTelefono_local(String telefono_local) {
		this.telefono_local = telefono_local;
	}
	
	
}
