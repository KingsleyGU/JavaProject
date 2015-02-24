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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import DB.DBConnect;

/**
 * Servlet implementation class UserList
 */
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
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
		try {
			stment = conn.prepareStatement("SELECT * FROM user");
		    ResultSet rs = stment.executeQuery();
			JSONArray jArray = new JSONArray();
			while(rs.next())
			{
				JSONObject styleJSON = new JSONObject();
	            styleJSON.put("username",rs.getString("username"));
	            styleJSON.put("password", rs.getString("password"));
	            styleJSON.put("balance", rs.getInt("balance"));
	            styleJSON.put("banned", rs.getBoolean("banned"));
	            jArray.add(styleJSON);			
			}
			response.getWriter().write(jArray.toString());
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
