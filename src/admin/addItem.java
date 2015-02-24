package admin;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DB.DBConnect;
/**
 * Servlet implementation class addItem
 */
public class addItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "uploads";
    public String message = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addItem() {
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
		String proname = "";
		String units = "";
		String description = "";
		String filename="";
		String workingDir = request.getContextPath();
		   System.out.println("Current working directory : " + workingDir);
	 
//		File file = new File(UPLOAD_DIRECTORY); 
//		file.mkdirs();
//		System.out.println( file.getPath());
		Map<String, String> paraMap = new HashMap<>();
        if(ServletFileUpload.isMultipartContent(request)){
            try {

                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        filename = name;
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                    else
                    {
                    	paraMap.put(item.getFieldName(), item.getString());
                    }
                }

               //File uploaded successfully
              message = "File Uploaded Successfully" +units;
            } catch (Exception ex) {
               message = "File Upload Failed due to " + ex;
            }          
         
        }else{
            message = "Sorry this Servlet only handles file upload request";
        }
        System.out.println(paraMap.get("name")+"11111111");
        proname = paraMap.get("name");
        units = paraMap.get("units");
        description = paraMap.get("description");
        DBConnect currentConnection = new DBConnect("project");
		Connection conn = currentConnection.getConnection();
		PreparedStatement stment;
		try {
			stment = conn.prepareStatement("insert into product(name,units,description,image,price) values(?,?,?,?,?)");
	    	stment.setString(1, proname);
	    	stment.setInt(2, Integer.parseInt(units));
	    	stment.setString(3,description);
	    	stment.setString(4,filename);
	    	stment.setInt(5,1000);
	    	stment.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter(); 
	    out.println(message);
//        request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
