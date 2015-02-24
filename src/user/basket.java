package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DBConnect;

/**
 * Servlet implementation class basket
 */
public class basket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public basket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnect currentConnection = new DBConnect("project");
		Connection conn = currentConnection.getConnection();
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return;
		String userId = "";
		for(Cookie cook : cookies){
	/* 		  userId = userId+"111111"+cook.getName() + " " + cook.getValue(); */
	         if("username".equals(cook.getName())){
		        userId = cook.getValue();
		   }
		}
		DatabaseMetaData meta;
		try {
			meta = conn.getMetaData();
			ResultSet res = meta.getTables(null, null, userId, null);
			Statement stmt = conn.createStatement();
			if (!res.next()) {
			    String sql = "CREATE TABLE " +userId +
		                   " (id INTEGER NOT NULL AUTO_INCREMENT, " +
		                   " product VARCHAR(30), " + 
		                   " totalPrice INTEGER, " + 
		                   " units INTEGER, " + 
		                   " PRIMARY KEY ( id ))"; 

		      stmt.executeUpdate(sql);
		      System.out.println("Created table in given database...");
			}
			String proname = request.getParameter("itemName");
			int amount = Integer.parseInt(request.getParameter("amount"));
			int price = Integer.parseInt(request.getParameter("price"));
			PreparedStatement stment;
			stment = conn.prepareStatement("SELECT * FROM "+userId+" WHERE product = ?");
			stment.setString(1, proname);
			ResultSet rs = stment.executeQuery();
			if(rs.next())
			{
				    int units = rs.getInt("units");
					 stment = conn.prepareStatement("UPDATE "+userId+" SET units = ?,totalPrice =? WHERE product = ?");
					 stment.setInt(1, amount+units);
					 stment.setInt(1, (amount+units)*price);
					 stment.setString(3, proname);
					 stment.execute();
			}
			else
			{
			stment = conn.prepareStatement("insert into "+userId+"(product,units,totalPrice) values(?,?,?)");
	    	stment.setString(1, proname);
	    	stment.setInt(2, amount);
	    	stment.setInt(3, amount*price);
	    	stment.execute();
			}
         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
