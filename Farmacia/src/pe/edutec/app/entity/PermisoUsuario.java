package pe.edutec.app.entity;

public class PermisoUsuario {
	private Permiso id_permiso;
	private UsuarioTipo id_usuario_tipo;
	private boolean visible_permiso_usuario;
	
	public PermisoUsuario(){}
	
	public PermisoUsuario(UsuarioTipo id_usuario_tipo){
		this.id_usuario_tipo = id_usuario_tipo;
	}

	public UsuarioTipo getId_usuario_tipo() {
		return id_usuario_tipo;
	}

	public void setId_usuario_tipo(UsuarioTipo id_usuario_tipo) {
		this.id_usuario_tipo = id_usuario_tipo;
	}

	public boolean isVisible_permiso_usuario() {
		return visible_permiso_usuario;
	}

	public void setVisible_permiso_usuario(boolean visible_permiso_usuario) {
		this.visible_permiso_usuario = visible_permiso_usuario;
	}

	public Permiso getId_permiso() {
		return id_permiso;
	}

	public void setId_permiso(Permiso id_permiso) {
		this.id_permiso = id_permiso;
	}
	
	
}
