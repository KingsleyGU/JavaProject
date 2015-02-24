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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import DB.DBConnect;

/**
 * Servlet implementation class customerInfo
 */
public class customerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customerInfo() {
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
	    try {
			PreparedStatement stment = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
			stment.setString(1, userId);
			 ResultSet rs = stment.executeQuery();
			 if(rs.next())
			 {
				    int balance = rs.getInt("balance");
					request.setAttribute("balance", balance+"");
					request.getRequestDispatcher("cusInfo.jsp").forward(request, response);
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
