package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection connection;
	
	public static DatabaseConnection getInstance() {
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
	
	private DatabaseConnection() {
		
	}
	
	public void connectToDatabase() {
		try {
			String username = "root";
			String password = "";
			String databaseName = "appchat";
			String url = "jdbc:mysql://localhost/" + databaseName;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Kết nối thành công vs database " + databaseName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
