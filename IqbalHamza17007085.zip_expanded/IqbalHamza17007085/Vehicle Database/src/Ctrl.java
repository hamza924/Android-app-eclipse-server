import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ctrl class to show database on a website with login and table of all
 * cars
 * @author Hamza Iqbal 17007085
 *@version 1.0
 */
public class Ctrl {
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080); //listen on port 8080        
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);
		handler.addServletWithMapping(LoginServlet.class, "/*");
		server.start();       
		server.join();	

	}
	
	/**
	 * 
	 * @return database connection to use in ctrl class
	 */
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return dbConnection;
	}
	
	
	@SuppressWarnings("serial")
	
	public static class LoginServlet extends HttpServlet  { 

	@Override
	/*
	 * (non-Javadoc)
	 * do get method apart of jetty servlets
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException
	{   
		// set the type that the content is
		response.setContentType("text/html");
		// setting the status to http code 200 or jetty equivalent 
		response.setStatus(HttpServletResponse.SC_OK);

		PrintWriter out = response.getWriter();
		// start of the html
		out.println("<html><body>");
		out.println("<h1>Log in </h1>");            
		out.println("<form action=\"Login\" method=\"post\" >");
		out.println("Enter username: <input type=\"text\" name=\"username\"> <br>");
		out.println(" Enter password: <input type=\"password\" name=\"password\"> <br>");
		out.println("<input type=\"submit\" value=\"Login\">");
		out.println("</form>");            
		out.println("</body></html>"); 
		// end of html

	}

	
	/*
	 * do post method
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// vehicle dao object
		VehicleDAO dao = new VehicleDAO();
		// getting the value from the username text box
		String uname = request.getParameter("username");
		// getting value of password from password text box
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		// array list of vehicles
		ArrayList<Vehicle> AllVehicle = null;
		try {
			AllVehicle = dao.getAllVehicles();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection dbConnection = null;
		Statement myStmt = null;
		ResultSet result = null;
		String query = "select * from users where username = ? and  password = ?;";
		
		try {
			dbConnection = getDBConnection();
			
			myStmt = dbConnection.createStatement();
			System.out.println("Query is "+ query);
			result = myStmt.executeQuery(query);
			
			
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (myStmt != null) {
				try {
					myStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		if (uname.equals("admin")&& password.equals("kings123"))
		{
			//Dashboard   			
			out.println("<html><body>");
			// start of table displaying vehicles on the website
			out.println("<table border = 1><tr><th>Vehicle ID</th><th>Make</th>" + 
					"<th>Model</th><th>Year</th><th>Price</th><th>License Number</th><th>Colour</th>" + 
					" <th>Number of Doors</th><th>Transmission</th><th>Mileage</th><th>Fuel type</th>" + 
					" <th>Engine Size</th><th>Body Style</th><th>Condition</th><th>Notes</th><th>update</th><th>delete</th></tr>"  
					);
			
			
			for (Vehicle v : AllVehicle) {
				out.println("<tr><td>" + v.getVehicle_id() + "</td><td>" + v.getMake()+"</td><td>"+ v.getModel() +"</td>" +"<td>"+v.getYear()+"</td>"+
							"<td>" + v.getPrice()+"</td>" +"<td>"+v.getLicense_number()+"</td>" + "<td>"+v.getColour()+"</td>"+"<td>"+v.getNumber_doors()+"</td>"+
							"<td>"	+v.getTransmission()+"</td>" +"<td>"+v.getMileage()+"</td>" + "<td>"+v.getFuel_type()+"</td>"+"<td>"+v.getEngine_size()+"</td>"+
							"<td>"+v.getBody_style()+"</td>"+"<td>"+v.getCondition()+"</td>"+"<td>"+v.getNotes()+"</td>" + "<td><form action=\"UpdateServlet\" > <input type=\"submit\" value=\"Update\"/> </form> </td>"+ "<td><form action=\"DeleteServlet\" > <input type=\"submit\" value=\"Delete\"/></form></td>"+"</tr>");
			}
			// end of table
			out.println("</table>");
			// logout form
			out.println("<form action=\"LogoutServlet\" >");
			out.println("<input type=\"submit\" value=\"Logout\"/>");
			out.println("</form>");    
			out.println("</body></html>");    			

		}
		else {
			doGet(request, response);
		}

	}
}
	/*
	 * logout method
	 */
	public static class LogoutServlet extends HttpServlet
	{      
		@Override
		protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
		{        // sending user back to main page     	      	
			response.sendRedirect("http://localhost:8080/");            
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doGet(request, response);

		}    	

}
	
	
	
}
}


