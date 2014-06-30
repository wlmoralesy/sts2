package pe.edutec.app.entity;

public class Proveedor {
	private int id_proveedor;
	private String ruc_proveedor;
	private String nombre_proveedor;
	private String telefono_proveedor;
	private String direccion_proveedor;
	private boolean laboratorio;
	
	public Proveedor(){}
	
	public Proveedor(int id_proveedor){
		this.id_proveedor = id_proveedor;
	}

	public int getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public String getRuc_proveedor() {
		return ruc_proveedor;
	}

	public void setRuc_proveedor(String ruc_proveedor) {
		this.ruc_proveedor = ruc_proveedor;
	}

	public String getNombre_proveedor() {
		return nombre_proveedor;
	}

	public void setNombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}

	public String getTelefono_proveedor() {
		return telefono_proveedor;
	}

	public void setTelefono_proveedor(String telefono_proveedor) {
		this.telefono_proveedor = telefono_proveedor;
	}

	public String getDireccion_proveedor() {
		return direccion_proveedor;
	}

	public void setDireccion_proveedor(String direccion_proveedor) {
		this.direccion_proveedor = direccion_proveedor;
	}

	public boolean isLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(boolean laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	
}
