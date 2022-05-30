import java.sql.*;

public class DAO {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/db_sisterial";
	private  String username = "root";
	private  String password = "root";
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			
		}
	}
}
