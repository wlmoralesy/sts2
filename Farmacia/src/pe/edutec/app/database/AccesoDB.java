package pe.edutec.app.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class AccesoDB {

	private AccesoDB() {
	}

	public static Connection getConnection() {

		Connection cn = null;

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/farmacia";
			cn = DriverManager.getConnection(url, "root", "@es5k@");

		} catch (Exception e) {

			throw new RuntimeException("Error de acceso a la base de datos.");

		}
		return cn;
	}
}
