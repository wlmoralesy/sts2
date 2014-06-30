package pe.edutec.app.dao;

import java.sql.Date;
import java.util.List;

import pe.edutec.app.entity.ComisionDetalleReporte;
import pe.edutec.app.entity.ComisionReporte;
import pe.edutec.app.entity.VentaDetalleReporte;
import pe.edutec.app.entity.VentaReporte;

public interface IReporte {

	List<VentaReporte> reporte(Date fecha, int local);
	List<VentaDetalleReporte> reporteDetalle(Date fecha, int local);
	
	List<ComisionReporte> reporte(Date fecha1, Date fecha2, int local);
	List<ComisionDetalleReporte> reporteDetalle(Date fecha1, Date fecha2, int local);
	
}
