package user;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class display
 */
public class display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public display() {
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
			stment = conn.prepareStatement("SELECT * FROM product");
			ResultSet rs = stment.executeQuery();
			JSONArray jArray = new JSONArray();
			while(rs.next())
			{
				 JSONObject styleJSON = new JSONObject();
	                styleJSON.put("name",rs.getString("name"));
	                styleJSON.put("units", rs.getInt("units"));
	                styleJSON.put("description", rs.getString("description"));
	                styleJSON.put("price", rs.getInt("price"));
	                styleJSON.put("image", rs.getString("image"));
	                jArray.add(styleJSON);			
			}
//			PrintWriter out = response.getWriter();
//			out.println(jArray);
			response.getWriter().write(jArray.toString());
//			request.setAttribute("productList", jArray);
//			request.getRequestDispatcher("/display.jsp").forward(request, response);
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
