package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Comision;
import pe.edutec.app.mapper.ComisionMapper;

public class ComisionDao implements IDaoCrud<Comision> {

	@Override
	public Comision insert(Comision o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el comision
			String sql = "insert into tb_comision(id_comision_tipo,cantidad_comision"
							+ ") values(?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_comision_tipo().getId_comision_tipo());
			pstm.setDouble(2, o.getCantidad_comision());
			pstm.executeUpdate();
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
	public Comision query(String id) {
		Connection cn = null;
		Comision obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comision,cantidad_comision,t.id_comision_tipo as id_comision_tipo ,t.nombre_comision_tipo as nombre_comision_tipo" +
						 " from tb_comision c inner join tb_comision_tipo t on t.id_comision_tipo=c.id_comision_tipo " + 
						 " where id_comision = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, Integer.valueOf(id));
			ResultSet rs = pstm.executeQuery();
			ComisionMapper mapper = new ComisionMapper();
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
	public List<Comision> query() {
		Connection cn = null;
		List<Comision> lista = new ArrayList<Comision>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comision,cantidad_comision,t.id_comision_tipo as id_comision_tipo ,t.nombre_comision_tipo as nombre_comision_tipo" +
					" from tb_comision c inner join tb_comision_tipo t on t.id_comision_tipo=c.id_comision_tipo";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ComisionMapper mapper = new ComisionMapper();
			while (rs.next()) {
				Comision obj = mapper.mapRow(rs);
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
	public List<Comision> query(Comision o) {
		Connection cn = null;
		List<Comision> lista = new ArrayList<Comision>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_comision,cantidad_comision,t.id_comision_tipo as id_comision_tipo ,t.nombre_comision_tipo as nombre_comision_tipo" +
						"  from tb_comision c inner join tb_comision_tipo t on t.id_comision_tipo=c.id_comision_tipo "
							+ " where id_comision_tipo like ? "
							+ " or cantidad_comision like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + String.valueOf(o.getId_comision_tipo())+ "%");
			pstm.setString(2, "%" + String.valueOf(o.getCantidad_comision()) + "%");
			ResultSet rs = pstm.executeQuery();
			ComisionMapper mapper = new ComisionMapper();
			while (rs.next()) {
				Comision obj = mapper.mapRow(rs);
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
	public void update(Comision o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el comision
			String sql = "update tb_comision set "
							+ " id_comision_tipo = ?, "
							+ " cantidad_comision = ? "
							+ " where id_comision = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_comision_tipo().getId_comision_tipo());
			pstm.setDouble(2, o.getCantidad_comision());
			pstm.setInt(3, o.getId_comision());
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
			String sql = "delete from tb_comision where id_comision= ?";
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
