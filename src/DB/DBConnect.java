package DB;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
	private String DBName;
	public DBConnect(String DB)
	{
		this.DBName = DB;
	}
	public Connection getConnection()
	{
		   final String DB_URL = "jdbc:mysql://localhost:3206/" + this.DBName;
		   final String user = "root";
		   final String password = "";
		   Connection conn = null;
		   try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
		   System.out.println("There is something wrong with the Database Driver");
		}
			try {
				conn = DriverManager.getConnection(DB_URL, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
	}
	

}
