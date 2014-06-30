package pe.edutec.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edutec.app.database.AccesoDB;
import pe.edutec.app.entity.Personal;
import pe.edutec.app.mapper.PersonalMapper;

public class PersonalDao implements IDaoCrud<Personal> {

	@Override
	public Personal insert(Personal o) {
		Connection cn = null;
		
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			// Registrar el personal
			String sql = "insert into tb_personal(id_cargo,id_local,dni_personal,nombre_personal,"
							+ "apellido_personal,telefono_personal,celular_personal)"
							+ " values(?,?,?,?,?,?,?)";
			PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, o.getId_cargo().getId_cargo());
			pstm.setInt(2, o.getId_local().getId_local());
			pstm.setString(3, o.getDni_personal());
			pstm.setString(4, o.getNombre_personal());
			pstm.setString(5, o.getApellido_personal());
			pstm.setString(6, o.getTelefono_personal());
			pstm.setString(7, o.getCelular_personal());
			pstm.executeUpdate();
		
			ResultSet generatedKeys = null;
			generatedKeys = pstm.getGeneratedKeys();	
			if (generatedKeys.next()) {
				o.setId_personal(generatedKeys.getInt(1));
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
			throw new RuntimeException("Problemas de inserción del personal.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {}
		}
	}

	@Override
	public Personal query(String id) {
		Connection cn = null;
		Personal obj = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_personal,dni_personal,nombre_personal, "
							+ "apellido_personal,telefono_personal,celular_personal, "
							+ "c.id_cargo as id_cargo,c.nombre_cargo as nombre_cargo, "
							+ "l.id_local as id_local,l.nombre_local as nombre_local "
							+ "from tb_personal p inner join tb_cargo c on c.id_cargo = p.id_cargo inner join tb_local l on l.id_local = p.id_local "
							+ "where id_personal = ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			PersonalMapper mapper = new PersonalMapper();
			if (rs.next()) {
				obj = mapper.mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar el personal.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return obj;
	}

	@Override
	public List<Personal> query() {
		Connection cn = null;
		List<Personal> lista = new ArrayList<Personal>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id_personal,dni_personal,nombre_personal, "
							+ "apellido_personal,telefono_personal,celular_personal, "
							+ "c.id_cargo as id_cargo,c.nombre_cargo as nombre_cargo, "
							+ "l.id_local as id_local,l.nombre_local as nombre_local "
							+ "from tb_personal p inner join tb_cargo c on c.id_cargo = p.id_cargo inner join tb_local l on l.id_local = p.id_local";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			PersonalMapper mapper = new PersonalMapper();
			while (rs.next()) {
				Personal obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los personales.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public List<Personal> query(Personal o) {
		Connection cn = null;
		List<Personal> lista = new ArrayList<Personal>();
		try {
			cn = AccesoDB.getConnection();
			String sql = "select dni_personal,nombre_personal,"
							+ "apellido_personal,telefono_personal,celular_personal,"
							+ "from tb_personal"
							+ "where nombre_personal like ? "
							+ "or apellido_personal like ? "
							+ "or dni_personal like ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, "%" + o.getNombre_personal() + "%");
			pstm.setString(2, "%" + o.getApellido_personal() + "%");
			pstm.setString(3, "%" + o.getDni_personal() + "%");
			ResultSet rs = pstm.executeQuery();
			PersonalMapper mapper = new PersonalMapper();
			while (rs.next()) {
				Personal obj = mapper.mapRow(rs);
				lista.add(obj);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			throw new RuntimeException("No se puede consultar los personales.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	@Override
	public void update(Personal o) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Actualizar el personal
			String sql = "update tb_personal set "
							+ "id_cargo = ?,"
							+ "id_local = ?,"
							+ "dni_personal = ?,"
							+ "nombre_personal = ?,"
							+ "apellido_personal = ?,"
							+ "telefono_personal = ?,"
							+ "celular_personal = ? "
							+ "where id_personal = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, o.getId_cargo().getId_cargo());
			pstm.setInt(2, o.getId_local().getId_local());
			pstm.setString(3, o.getDni_personal());
			pstm.setString(4, o.getNombre_personal());
			pstm.setString(5, o.getApellido_personal());
			pstm.setString(6, o.getTelefono_personal());
			pstm.setString(7, o.getCelular_personal());
			pstm.setInt(8, o.getId_personal());
			pstm.executeUpdate();
			pstm.close();
			// Confirmar Tx
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("Problemas al actualizar del personal.");
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
			String sql = "delete from tb_personal where id_personal= ?";
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
