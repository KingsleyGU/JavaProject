package user;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class register
 */
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnect currentConnection = new DBConnect("project");
		Connection conn = currentConnection.getConnection();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			PreparedStatement stment = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
		    stment.setString(1, username);
		    ResultSet rs = stment.executeQuery();
		    if(rs.next())
		    {
		    	PrintWriter out = response.getWriter(); 
			    out.println(username + " " + password);	    	
		    }
		    else
		    {
		    	stment = conn.prepareStatement("insert into user(username,password) values(?,?)");
		    	stment.setString(1, username);
		    	stment.setString(2,password);
		    	stment.execute();
		    	Cookie cookie = new Cookie("username", username);
		    	response.addCookie(cookie);
		    	cookie = new Cookie("password",password);
		    	response.addCookie(cookie);
		    	PrintWriter out = response.getWriter(); 
		    	Cookie[] cookies = request.getCookies();

		    	String userId = "";
		    	for(Cookie cook : cookies){
		    		  userId = userId+"111111"+cook.getName() + " " + cook.getValue();
//		    	    if(username.equals(cook.getName())){
//		    	        userId = cook.getValue();
//		    	    }
		    	}
		    	  out.println(userId); 
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
