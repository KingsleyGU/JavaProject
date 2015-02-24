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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class basketList
 */
public class basketList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public basketList() {
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
			if (res.next()) {
				PreparedStatement stment = conn.prepareStatement("SELECT * FROM "+userId);
			    ResultSet rs = stment.executeQuery();
				JSONArray jArray = new JSONArray();
				while(rs.next())
				{
					JSONObject styleJSON = new JSONObject();
		            styleJSON.put("product",rs.getString("product"));
		            styleJSON.put("units", rs.getInt("units"));
		            styleJSON.put("totalPrice", rs.getInt("totalPrice"));
		            jArray.add(styleJSON);			
				}
				response.getWriter().write(jArray.toString());
			}
		}catch (SQLException e) {
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
