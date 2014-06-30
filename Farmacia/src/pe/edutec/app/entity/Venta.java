package pe.edutec.app.entity;

import java.util.Date;

public class Venta {
	private int id_venta;
	private Personal id_personal;
	private Cliente id_cliente;
	private Local id_local;
	private Comprobante id_comprobante;
	private String numero_venta;
	private Date fecha_venta;
	private String descripcion_venta;
	private double total_venta;

	public Venta(){}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public Personal getId_personal() {
		return id_personal;
	}

	public void setId_personal(Personal id_personal) {
		this.id_personal = id_personal;
	}

	public Cliente getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Cliente id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Local getId_local() {
		return id_local;
	}

	public void setId_local(Local id_local) {
		this.id_local = id_local;
	}

	public String getNumero_venta() {
		return numero_venta;
	}

	public void setNumero_venta(String numero_venta) {
		this.numero_venta = numero_venta;
	}

	public Date getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public String getDescripcion_venta() {
		return descripcion_venta;
	}

	public void setDescripcion_venta(String descripcion_venta) {
		this.descripcion_venta = descripcion_venta;
	}

	public double getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(double total_venta) {
		this.total_venta = total_venta;
	}

	public Comprobante getId_comprobante() {
		return id_comprobante;
	}

	public void setId_comprobante(Comprobante id_comprobante) {
		this.id_comprobante = id_comprobante;
	}
	
	
}
