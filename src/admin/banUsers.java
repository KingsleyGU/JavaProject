package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DBConnect;

/**
 * Servlet implementation class banUsers
 */
public class banUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public banUsers() {
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
		PreparedStatement stment;
		String username = request.getParameter("username");
		try {
			stment = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
			stment.setString(1,username);
		    ResultSet rs = stment.executeQuery();
		    Boolean banned = false;
		    if(rs.next())
		    {
		    	if(rs.getBoolean("banned"))
		    	{
		    		banned = true;
		    	}
		    }
			stment = conn.prepareStatement("UPDATE user SET banned=? WHERE username=?");
			stment.setBoolean(1, !banned);
			stment.setString(2, username);
		    stment.execute();
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
