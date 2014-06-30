package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.ComisionTipo;
import pe.edutec.app.mapper.ComisionTipoMapper;

public class ComisionTipoDao implements IDaoCrud<ComisionTipo> {

	@Override
	public ComisionTipo insert(ComisionTipo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el ComisionTipo
			String sql = "insert into tb_comision_tipo(nombre_comision_tipo"
							+ ") values(?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, o.getNombre_comision_tipo());
			pstm.executeUpdate();
			
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_comision_tipo(generatedKeys.getInt(1));
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
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public ComisionTipo query(String id) {
		Connection cn = null;
		ComisionTipo obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comision_tipo,nombre_comision_tipo"
							+ " from tb_comision_tipo "
							+ " where id_comision_tipo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			ComisionTipoMapper mapper = new ComisionTipoMapper();
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
	public List<ComisionTipo> query() {
		Connection cn = null;
		List<ComisionTipo> lista = new ArrayList<ComisionTipo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comision_tipo,nombre_comision_tipo "
							+ " from tb_comision_tipo ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ComisionTipoMapper mapper = new ComisionTipoMapper();
			while (rs.next()) {
				ComisionTipo obj = mapper.mapRow(rs);
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
	public List<ComisionTipo> query(ComisionTipo o) {
		Connection cn = null;
		List<ComisionTipo> lista = new ArrayList<ComisionTipo>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comision_tipo,nombre_comision_tipo "
							+ " from tb_comision_tipo "
							+ " where nombre_comision_tipo like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_comision_tipo() + "%");
			ResultSet rs = pstm.executeQuery();
			ComisionTipoMapper mapper = new ComisionTipoMapper();
			while (rs.next()) {
				ComisionTipo obj = mapper.mapRow(rs);
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
	public void update(ComisionTipo o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el ComisionTipo
			String sql = "update tb_comision_tipo set "
							+ " nombre_comision_tipo = ? "
							+ " where id_comision_tipo = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, o.getNombre_comision_tipo());
			pstm.setInt(2, o.getId_comision_tipo());
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
			String sql = "delete from tb_comision_tipo where id_comision_tipo= ?";
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
