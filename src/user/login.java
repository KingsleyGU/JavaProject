package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DBConnect currentConnection = new DBConnect("project");
		Connection conn = currentConnection.getConnection();
		PreparedStatement stment;
		try {
			stment = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
		    stment.setString(1, username);
		    ResultSet rs = stment.executeQuery();
		    String currentPwd = "asdcjacjascndkcsad";
		    if(rs.next())
		       currentPwd = rs.getString("password");
		    if(password.equals(currentPwd))
		    {
		    	Cookie cookie = new Cookie("username", username);
		    	response.addCookie(cookie);
		    	cookie = new Cookie("password",password);
		    	response.addCookie(cookie);
		    	request.getRequestDispatcher("display.jsp").forward(request, response);
		    }
		    else
		    {
		    	request.getRequestDispatcher("login.jsp").forward(request, response);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		PrintWriter out = response.getWriter(); 
//	    out.println(username + " " + password);
	}

}
