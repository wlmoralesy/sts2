package pe.edutec.app.dao;

import java.util.List;

public interface IDaoCrud<T> {

	T insert(T o);

	T query(String id);

	List<T> query();

	List<T> query(T o);

	void update(T o);

	void delete(String id);
}
