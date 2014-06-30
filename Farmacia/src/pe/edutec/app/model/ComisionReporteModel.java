package pe.edutec.app.model;

import java.sql.Date;
import java.util.List;

import pe.edutec.app.dao.IReporte;
import pe.edutec.app.dao.VentaDao;
import pe.edutec.app.entity.ComisionDetalleReporte;
import pe.edutec.app.entity.ComisionReporte;

public class ComisionReporteModel {

	private IReporte dao = new VentaDao();
	
	public List<ComisionReporte> reporte(Date fecha1, Date fecha2, int local) {
		return dao.reporte(fecha1, fecha2, local);
	}
	
	public List<ComisionDetalleReporte> reporteDetalle(Date fecha1, Date fecha2, int local) {
		return dao.reporteDetalle(fecha1, fecha2, local);
	}
	
	
}
