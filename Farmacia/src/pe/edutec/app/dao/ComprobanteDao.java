package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Comprobante;
import pe.edutec.app.mapper.ComprobanteMapper;

public class ComprobanteDao implements IDaoCrud<Comprobante> {

	@Override
	public Comprobante insert(Comprobante o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el comprobante
			String sql = "insert into tb_comprobante(nombre_comprobante,formato_comprobante,"
							+ "correlativo_primario_comprobante,correlativo_secundario_comprobante"
							+ ") values(?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_comprobante());
			pstm.setString(2, o.getFormato_comprobante());
			pstm.setInt(3, o.getCorrelativo_primario_comprobante());
			pstm.setInt(4, o.getCorrelativo_secundario_comprobante());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_comprobante(generatedKeys.getInt(1));
			}
			
			pstm.close();
			// Confirmar Tx
			cn.commit();
			
			return o;
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("Problemas de insercion del comprobante.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public Comprobante query(String id) {
		Connection cn = null;
		Comprobante obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = " select id_comprobante,nombre_comprobante,formato_comprobante, "
							+ " correlativo_primario_comprobante,correlativo_secundario_comprobante "
							+ " from tb_comprobante "
							+ " where id_comprobante = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			ComprobanteMapper mapper = new ComprobanteMapper();
			if (rs.next()) {
				obj = mapper.mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar el comprobante.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return obj;
	}

	@Override
	public List<Comprobante> query() {
		Connection cn = null;
		List<Comprobante> lista = new ArrayList<Comprobante>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comprobante,nombre_comprobante,formato_comprobante,"
							+ "correlativo_primario_comprobante,correlativo_secundario_comprobante"
							+ " from tb_comprobante ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ComprobanteMapper mapper = new ComprobanteMapper();
			while (rs.next()) {
				Comprobante obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los comprobantees.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public List<Comprobante> query(Comprobante o) {
		Connection cn = null;
		List<Comprobante> lista = new ArrayList<Comprobante>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comprobante,nombre_comprobante,formato_comprobante,"
							+ "correlativo_primario_comprobante,correlativo_secundario_comprobante"
							+ "from tb_comprobante"
							+ " where nombre_comprobante like ? "
							+ "or formato_comprobante like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_comprobante() + "%");
			pstm.setString(2, "%" + o.getFormato_comprobante() + "%");
			ResultSet rs = pstm.executeQuery();
			ComprobanteMapper mapper = new ComprobanteMapper();
			while (rs.next()) {
				Comprobante obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los comprobantees.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public void update(Comprobante o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el comprobante
			String sql = " update tb_comprobante set  "
							+ " nombre_comprobante = ?,"
							+ " formato_comprobante = ?, "
							+ " correlativo_primario_comprobante = ?, "
							+ " correlativo_secundario_comprobante = ? "
							+ " where id_comprobante = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_comprobante());
			pstm.setString(2, o.getFormato_comprobante());
			pstm.setInt(3, o.getCorrelativo_primario_comprobante());
			pstm.setInt(4, o.getCorrelativo_secundario_comprobante());
			pstm.setInt(5, o.getId_comprobante());
			pstm.executeUpdate();
			pstm.close();
			// Confirmar Tx
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("Problemas con la actualización del comprobante.");
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
			String sql = "SELECT COUNT(*) cont FROM tb_comprobante "
							+ "WHERE id_comprobante = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs  = pstm.executeQuery();
			rs.next();
			long cont = rs.getLong("cont");
			rs.close();
			pstm.close();
			if(cont > 0){
				throw new RuntimeException("No se puede eliminar por que tiene cuentas.");
			}
			sql = "delete from tb_comprobante where id_comprobante= ?";
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
