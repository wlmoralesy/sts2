package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Caducidad;
import pe.edutec.app.mapper.CaducidadMapper;

public class CaducidadDao implements IDaoCrud<Caducidad> {

	@Override
	public Caducidad insert(Caducidad o) {
		Connection cn = null;
		
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el personal
			String sql = "insert into tb_caducidad(id_personal,id_caducidad_estado,id_local,descripcion_caducidad,"
							+ "fecha_creacion_caducidad)"
							+ " values(?,?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, o.getId_personal().getId_personal());
			pstm.setInt(2, o.getId_caducidad_estado().getId_caducidad_estado());
			pstm.setInt(3, o.getId_local().getId_local());
			pstm.setString(4, o.getDescripcion_caducidad());
			pstm.setDate(5, new java.sql.Date(o.getFecha_creacion_caducidad().getTime()));
			pstm.executeUpdate();
		
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_caducidad(generatedKeys.getInt(1));
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
			} catch (Exception e) {}
		}
	}

	@Override
	public Caducidad query(String id) {
		Connection cn = null;
		Caducidad obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_caducidad,descripcion_caducidad,fecha_creacion_caducidad,"
							+ " fecha_limite_caducidad," 
							+ " p.id_personal as id_personal, p.dni_personal as dni_personal, p.nombre_personal as nombre_personal,p.apellido_personal as apellido_personal,"
							+ " e.id_caducidad_estado as id_caducidad_estado, e.nombre_caducidad_estado as nombre_caducidad_estado," 
							+ " l.id_local as id_local, l.nombre_local as nombre_local "
							+ " from tb_caducidad c inner join tb_personal p on p.id_personal = c.id_personal inner join"
							+ " tb_caducidad_estado e on e.id_caducidad_estado=c.id_caducidad_estado inner join tb_local l on l.id_local = p.id_local "
							+ " where id_caducidad = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			CaducidadMapper mapper = new CaducidadMapper();
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
	public List<Caducidad> query() {
		Connection cn = null;
		List<Caducidad> lista = new ArrayList<Caducidad>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_caducidad,descripcion_caducidad,fecha_creacion_caducidad,"
							+ " fecha_limite_caducidad," 
							+ " p.id_personal as id_personal, p.dni_personal as dni_personal, p.nombre_personal as nombre_personal,p.apellido_personal as apellido_personal,"
							+ " e.id_caducidad_estado as id_caducidad_estado, e.nombre_caducidad_estado as nombre_caducidad_estado," 
							+ " l.id_local as id_local, l.nombre_local as nombre_local "
							+ " from tb_caducidad c inner join tb_personal p on p.id_personal = c.id_personal inner join"
							+ " tb_caducidad_estado e on e.id_caducidad_estado=c.id_caducidad_estado inner join tb_local l on l.id_local = p.id_local ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			CaducidadMapper mapper = new CaducidadMapper();
			while (rs.next()) {
				Caducidad obj = mapper.mapRow(rs);
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
	public List<Caducidad> query(Caducidad o) {
		Connection cn = null;
		List<Caducidad> lista = new ArrayList<Caducidad>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select dni_personal,nombre_personal,"
							+ "apellido_personal,telefono_personal,celular_personal,"
							+ "from tb_personal"
							+ "where nombre_personal like ? "
							+ "or apellido_personal like ? "
							+ "or dni_personal like ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
//			pstm.setString(1, "%" + o.getNombre_personal() + "%");
//			pstm.setString(2, "%" + o.getApellido_personal() + "%");
//			pstm.setString(3, "%" + o.getDni_personal() + "%");
			ResultSet rs = pstm.executeQuery();
			CaducidadMapper mapper = new CaducidadMapper();
			while (rs.next()) {
				Caducidad obj = mapper.mapRow(rs);
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
	public void update(Caducidad o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el personal
			String sql = "update tb_caducidad set "
							+ " id_personal = ?,"
							+ " id_caducidad_estado = ?,"
							+ " id_local = ?,"
							+ " descripcion_caducidad = ?,"
							+ " fecha_creacion_caducidad = ?,"
							+ " fecha_limite_caducidad = ?"
							+ " where id_caducidad = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_personal().getId_personal());
			pstm.setInt(2, o.getId_caducidad_estado().getId_caducidad_estado());
			pstm.setInt(3, o.getId_local().getId_local());
			pstm.setString(4, o.getDescripcion_caducidad());
			pstm.setDate(5, (Date) o.getFecha_creacion_caducidad());
			pstm.setDate(6, (Date) o.getFecha_limite_caducidad());
			pstm.setInt(7, o.getId_caducidad());
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
			String sql = "delete from tb_caducidad where id_caducidad= ?";
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
