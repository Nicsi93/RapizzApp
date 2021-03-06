package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAOConnector {
	
	public Statement statement;
	public Connection conn;
	public static DAOConnector db;

	private DAOConnector()
	{
		String url = "jdbc:mysql://159.180.243.29:3306/";
		String dbName = "rapizz";
		String driver = "com.mysql.cj.jdbc.Driver";
		Properties properties = new Properties();
		properties.setProperty("user", "mysql");
		properties.setProperty("password", "RapizzBDD./00");
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");
		
		try {
	            Class.forName(driver).newInstance();
	            this.conn = (Connection)DriverManager.getConnection(url+dbName,properties);
	        }
	        catch (Exception sqle) {
	            sqle.printStackTrace();
	        }
	 }
	
	public static synchronized DAOConnector getConnection() {
        if ( db == null ) {
            db = new DAOConnector();
        }
        return db;
    }
	
	public ResultSet query(String query) throws SQLException{
		
		statement = db.conn.createStatement();
		
		ResultSet res = statement.executeQuery(query);
		
		return res;
	}

	public int insert(String insertQuery) throws SQLException{
		statement = db.conn.createStatement();
		int result = statement.executeUpdate(insertQuery);
		return result;
	}
	
}
	

