package pe.edutec.app.entity;

import java.util.Date;

public class VentaDetalleReporte {

	public Local local;
	public Personal personal;
	public Comprobante comprobante;
	public Date fecha;
	public String turno;
	public int cantidad;
	public double total;
	
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public Personal getPersonal() {
		return personal;
	}
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	public Comprobante getComprobante() {
		return comprobante;
	}
	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
