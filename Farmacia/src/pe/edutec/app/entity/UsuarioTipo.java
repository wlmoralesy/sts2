package pe.edutec.app.entity;

public class UsuarioTipo {

	private int id_usuario_tipo;
	private String nombre_usuario_tipo;
	
	public UsuarioTipo(){}
	
	public UsuarioTipo(int id_usuario_tipo){
		this.id_usuario_tipo = id_usuario_tipo;
	}

	public int getId_usuario_tipo() {
		return id_usuario_tipo;
	}

	public void setId_usuario_tipo(int id_usuario_tipo) {
		this.id_usuario_tipo = id_usuario_tipo;
	}

	public String getNombre_usuario_tipo() {
		return nombre_usuario_tipo;
	}

	public void setNombre_usuario_tipo(String nombre_usuario_tipo) {
		this.nombre_usuario_tipo = nombre_usuario_tipo;
	}

}
