package pe.edutec.app.model;

import java.sql.Date;
import java.util.List;

import pe.edutec.app.dao.IReporte;
import pe.edutec.app.dao.VentaDao;
import pe.edutec.app.entity.VentaDetalle;
import pe.edutec.app.entity.VentaDetalleReporte;
import pe.edutec.app.entity.VentaReporte;

public class VentaReporteModel {

	private IReporte dao = new VentaDao();
	
	public List<VentaReporte> reporte(Date fecha, int local) {
		return dao.reporte(fecha, local);
	}
	
	public List<VentaDetalleReporte> reporteDetalle(Date fecha, int local) {
		return dao.reporteDetalle(fecha, local);
	}
	
	
}
