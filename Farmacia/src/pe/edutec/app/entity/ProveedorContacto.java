package pe.edutec.app.entity;

public class ProveedorContacto {
	private int id_proveedor_contacto;
	private Proveedor id_proveedor;
	private String dni_proveedor_contacto;
	private String nombre_proveedor_contacto;
	private String apellido_proveedor_contacto;
	private String telefono_proveedor_contacto;
	private String celular_proveedor_contacto;
	
	public ProveedorContacto(){}

	public int getId_proveedor_contacto() {
		return id_proveedor_contacto;
	}

	public void setId_proveedor_contacto(int id_proveedor_contacto) {
		this.id_proveedor_contacto = id_proveedor_contacto;
	}

	public String getDni_proveedor_contacto() {
		return dni_proveedor_contacto;
	}

	public void setDni_proveedor_contacto(String dni_proveedor_contacto) {
		this.dni_proveedor_contacto = dni_proveedor_contacto;
	}

	public String getNombre_proveedor_contacto() {
		return nombre_proveedor_contacto;
	}

	public void setNombre_proveedor_contacto(String nombre_proveedor_contacto) {
		this.nombre_proveedor_contacto = nombre_proveedor_contacto;
	}

	public String getApellido_proveedor_contacto() {
		return apellido_proveedor_contacto;
	}

	public void setApellido_proveedor_contacto(String apellido_proveedor_contacto) {
		this.apellido_proveedor_contacto = apellido_proveedor_contacto;
	}

	public String getTelefono_proveedor_contacto() {
		return telefono_proveedor_contacto;
	}

	public void setTelefono_proveedor_contacto(String telefono_proveedor_contacto) {
		this.telefono_proveedor_contacto = telefono_proveedor_contacto;
	}

	public String getCelular_proveedor_contacto() {
		return celular_proveedor_contacto;
	}

	public void setCelular_proveedor_contacto(String celular_proveedor_contacto) {
		this.celular_proveedor_contacto = celular_proveedor_contacto;
	}

	public Proveedor getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(Proveedor id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	
	
}
