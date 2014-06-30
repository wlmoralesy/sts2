package pe.edutec.app.entity;

public class Usuario {
	private int id_usuario;
	private String nombre_usuario;
	private String contrasena_usuario;
	private Personal id_personal;
	private UsuarioTipo id_usuario_tipo;
	
	public Usuario(){}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasena_usuario() {
		return contrasena_usuario;
	}

	public void setContrasena_usuario(String contrasena_usuario) {
		this.contrasena_usuario = contrasena_usuario;
	}

	public Personal getId_personal() {
		return id_personal;
	}

	public void setId_personal(Personal id_personal) {
		this.id_personal = id_personal;
	}

	public UsuarioTipo getId_usuario_tipo() {
		return id_usuario_tipo;
	}

	public void setId_usuario_tipo(UsuarioTipo id_usuario_tipo) {
		this.id_usuario_tipo = id_usuario_tipo;
	}

	
}
