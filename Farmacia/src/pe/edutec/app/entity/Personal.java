package pe.edutec.app.entity;

public class Personal {
	
	private int id_personal;
	private Cargo id_cargo;
	private Local id_local;
	private String dni_personal;
	private String nombre_personal;
	private String apellido_personal;
	private String telefono_personal;
	private String celular_personal;
	
	public Personal(){}
	
	public Personal(int id_personal){
		this.id_personal = id_personal;
	}

	public int getId_personal() {
		return id_personal;
	}

	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}

	public String getNombre_personal() {
		return nombre_personal;
	}

	public void setNombre_personal(String nombre_personal) {
		this.nombre_personal = nombre_personal;
	}

	public Cargo getId_cargo() {
		return id_cargo;
	}

	public void setId_cargo(Cargo id_cargo) {
		this.id_cargo = id_cargo;
	}

	public Local getId_local() {
		return id_local;
	}

	public void setId_local(Local id_local) {
		this.id_local = id_local;
	}

	public String getApellido_personal() {
		return apellido_personal;
	}

	public void setApellido_personal(String apellido_personal) {
		this.apellido_personal = apellido_personal;
	}

	public String getTelefono_personal() {
		return telefono_personal;
	}

	public void setTelefono_personal(String telefono_personal) {
		this.telefono_personal = telefono_personal;
	}

	public String getCelular_personal() {
		return celular_personal;
	}

	public void setCelular_personal(String celular_personal) {
		this.celular_personal = celular_personal;
	}

	public String getDni_personal() {
		return dni_personal;
	}

	public void setDni_personal(String dni_personal) {
		this.dni_personal = dni_personal;
	}
	
}
