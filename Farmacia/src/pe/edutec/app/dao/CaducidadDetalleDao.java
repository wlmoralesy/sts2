package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.CaducidadDetalle;
import pe.edutec.app.mapper.CaducidadDetalleMapper;

public class CaducidadDetalleDao implements IDaoCrud<CaducidadDetalle> {

	@Override
	public CaducidadDetalle insert(CaducidadDetalle o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Registrar el CaducidadDetalle
			String sql = "insert into tb_caducidad_detalle(id_producto,id_caducidad,cantidad_caducidad_detalle"
							+ ") values(?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, o.getId_producto().getId_producto());
			pstm.setInt(2, o.getId_caducidad());
			pstm.setInt(3, o.getCantidad_caducidad_detalle());			
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_caducidad_detalle(generatedKeys.getInt(1));
			}
			
			pstm.close();
			// Confirmar Tx
			cn.commit();
			
			String sql2 = "update tb_producto set "	+ 
					" stock_producto = stock_producto - ? "	+ 
					" where id_producto = ? ";
			PreparedStatement pstm2 = cn.prepareStatement(sql2);			
			pstm2.setInt(1, o.getCantidad_caducidad_detalle());
			pstm2.setInt(2, o.getId_producto().getId_producto());
			pstm2.executeUpdate();
			pstm2.close();
			// Confirmar Tx
			cn.commit();
			
			return o;
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public CaducidadDetalle query(String id) {
		Connection cn = null;
		CaducidadDetalle obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_caducidad_detalle,id_producto,id_caducidad,cantidad_caducidad_detalle "
							+ " from tb_caducidad_detalle "
							+ " where id_caducidad_detalle = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			CaducidadDetalleMapper mapper = new CaducidadDetalleMapper();
			if (rs.next()) {
				obj = mapper.mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return obj;
	}

	@Override
	public List<CaducidadDetalle> query() {
		Connection cn = null;
		List<CaducidadDetalle> lista = new ArrayList<CaducidadDetalle>();
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_caducidad_detallle,id_producto,id_caducidad,cantidad_caducidad_detalle "
							+ " from tb_caducidad_detalle ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			CaducidadDetalleMapper mapper = new CaducidadDetalleMapper();
			while (rs.next()) {
				CaducidadDetalle obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public List<CaducidadDetalle> query(CaducidadDetalle o) {
		Connection cn = null;
		List<CaducidadDetalle> lista = new ArrayList<CaducidadDetalle>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_caducidad_detalle,id_caducidad,cantidad_caducidad_detalle, "
							+ " p.id_producto as id_producto, p.nombre_producto as nombre_producto "
							+ " from tb_caducidad_detalle cd"
							+ " inner join tb_producto p on cd.id_producto = p.id_producto "
							+ " where id_caducidad = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_caducidad());
			ResultSet rs = pstm.executeQuery();
			CaducidadDetalleMapper mapper = new CaducidadDetalleMapper();
			while (rs.next()) {
				CaducidadDetalle obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public void update(CaducidadDetalle o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el CaducidadDetalle
			String sql = "update tb_caducidad_detalle "
							+ "id_producto = ?,"
							+ "id_caducidad = ?,"
							+ "cantidad_caducidad_detalle = ?"
							+ "where id_caducidad_detalle = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_producto().getId_producto());
			pstm.setInt(1, o.getId_caducidad());
			pstm.setInt(1, o.getCantidad_caducidad_detalle());
			pstm.executeUpdate();
			pstm.close();
			// Confirmar Tx
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(String id) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			String sql = "delete from tb_caducidad_detalle where id_caducidad_detalle= ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.executeUpdate();
			cn.commit();
			pstm.close();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} finally{
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		
		
		
	}
}
