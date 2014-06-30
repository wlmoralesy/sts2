package pe.edutec.app.entity;

public class ComisionTipo {

		private int id_comision_tipo;
		private String nombre_comision_tipo;
		
		public ComisionTipo(){}
		
		public ComisionTipo(int id_comision_tipo){
			this.id_comision_tipo = id_comision_tipo;
		}

		public int getId_comision_tipo() {
			return id_comision_tipo;
		}

		public void setId_comision_tipo(int id_comision_tipo) {
			this.id_comision_tipo = id_comision_tipo;
		}

		public String getNombre_comision_tipo() {
			return nombre_comision_tipo;
		}

		public void setNombre_comision_tipo(String nombre_comision_tipo) {
			this.nombre_comision_tipo = nombre_comision_tipo;
		}

		
}
