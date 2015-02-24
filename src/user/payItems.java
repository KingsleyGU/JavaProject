package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DBConnect;

/**
 * Servlet implementation class payItems
 */
public class payItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payItems() {
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
		String error="";
		for(Cookie cook : cookies){
	/* 		  userId = userId+"111111"+cook.getName() + " " + cook.getValue(); */
	         if("username".equals(cook.getName())){
		        userId = cook.getValue();
		   }
		}
		int totalMoney = Integer.parseInt(request.getParameter("totalPrice"));
		PreparedStatement stment;
		try {
			stment = conn.prepareStatement("SELECT * FROM "+userId);
			ResultSet rs = stment.executeQuery();
			while(rs.next())
			{
				String productName = rs.getString("product");
				int units = rs.getInt("units");
				stment = conn.prepareStatement("SELECT * FROM product WHERE name= ?" );
				stment.setString(1, productName);
				ResultSet rs1 = stment.executeQuery();
				int allUnits = 0;
				if(rs1.next())
				{
				  allUnits = rs1.getInt("units");	
				}
				if(allUnits<units)
				{
					error = "No enough " + productName;
					break;
				}
				else
				{
					stment = conn.prepareStatement("UPDATE product SET units=? WHERE name= ?" );
					stment.setInt(1,(allUnits-units));
					stment.setString(2, productName);
					 stment.execute();
					stment = conn.prepareStatement("DELETE FROM " +userId +" WHERE product= ?" );
					stment.setString(1, productName);
					stment.execute();
				}
			}
			if(error.equals(""))
			{
				stment = conn.prepareStatement("SELECT * FROM user WHERE username= ?" );
				stment.setString(1, userId);
				ResultSet rs2 = stment.executeQuery();
				int currentBalance = 0;
				if(rs2.next())
				{
					currentBalance = rs2.getInt("balance");
				}
				stment = conn.prepareStatement("UPDATE user SET balance=? WHERE username= ?" );
				stment.setInt(1,currentBalance - totalMoney);
				stment.setString(2, userId);
				stment.execute();	
				
			}

				response.getWriter().write(error);
			
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
