

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * 
 * @author hamza
 * Json Controller class  RESTFul routes 
 * for add
 * update
 * delete
 * add and update in JSON
 *
 */
 
 
public class JsonController {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		final VehicleDAO dao = new VehicleDAO();
		
		// GET operation
		try {
			// Set on port 8000
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
			final Gson gson = new Gson();
			// list vehicle
			 server.createContext("/api", new HttpHandler() {
				
				@Override
				public void handle(HttpExchange he) throws IOException {
					// TODO Auto-generated method stub
					System.out.println("Showing vehicles");
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					ArrayList<Vehicle> vehicleList= new ArrayList<Vehicle>();
					try {
						vehicleList = dao.getAllVehicles();
					} catch (SQLException e) { 
						
						e.printStackTrace();
					}
					
					System.out.println(vehicleList);
					System.out.print(gson.toJson(vehicleList));
					out.write(gson.toJson(vehicleList)); 

					he.sendResponseHeaders(200, 0);// send 200 code to mean everything is ok
					
					out.close();
					//close the context
					
				}
			});
			
			 
			    
			 					//POST REST route
			 server.createContext("/add_vehicle",new HttpHandler() {
					// route to add vehicle
					@Override
					public void handle(HttpExchange he) throws IOException {
						System.out.println("adding new vehicle into database");
						HashMap<String,String> post = new HashMap<String,String>();
						// using gson to go from json to strin
						Gson gson = new Gson();
						//read the request body
						BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
						String line = "";
						String request = "";
						while((line = in.readLine()) != null) {
							request = request + line;
						}
						System.out.println("-------- "+request);
						//individual key=value pairs are delimited by ampersands.
						String[] pairs = request.split("&");					
						for(int i=0;i<pairs.length;i++) {
							//each key=value pair is separated by an equals, and both halves require URL decoding.
							String pair = pairs[i];
							post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
						}
						
						// using gson to get the json from the server and insert a new vehicle
						Vehicle v = gson.fromJson(post.get("json"), Vehicle.class);
						
						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));					
						try { 
							// inserting new vehicle using v
							dao.insertVehicle(v); 
							he.sendResponseHeaders(200, 0); //200 code means everything ok
							out.write("New vehicle added");
							out.close();
						}
						catch(SQLException se) {
							 he.sendResponseHeaders(500, 0); //error 500
							 out.write("Error");
							 out.close();
						}
					}
					
				});
			 
			                             //PUT REST route
			 server.createContext("/update_vehicle",new HttpHandler() {
					//route for updating vehicle
					@Override
					public void handle(HttpExchange he) throws IOException {
						System.out.println("updating vehicle");
						HashMap<String,String> post = new HashMap<String,String>();
						// again using gson
						Gson gson = new Gson();
						//read the request body
						BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
						String line = "";
						String request = "";
						while((line = in.readLine()) != null) {
							request = request + line;
						}
						System.out.println("------ "+request);
						//individual key=value pairs are delimited by ampersands
						String[] pairs = request.split("&");					
						for(int i=0;i<pairs.length;i++) {
							//each key=value pair is separated by an equals, and both halves require URL decoding.
							String pair = pairs[i];
							post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
						}
						
						// USING gson to get the json and update the vehicle
						Vehicle v = gson.fromJson(post.get("json"), Vehicle.class);
						
						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));					
						try { 
							if(dao.updateVehicle(v)) { 
							he.sendResponseHeaders(200, 0); // 200 everything ok
							out.write("Success Vehicle UPDATED");
							out.close();}
							else {he.sendResponseHeaders(500, 0); // 500 error
							out.write("ERROR");
							out.close();
								
							}
						}
						catch(SQLException se) {
							 he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
							 out.write("Error UPDATING Vehicle");
							 out.close();
						}
					}
					
				});
				
			 
			                        //DELETE rest route
			 server.createContext("/delete_vehicle",new HttpHandler() {
					//route for delete
					@Override
					public void handle(HttpExchange he) throws IOException {
						System.out.println("deleteing vehicle");
						HashMap<String,String> post = new HashMap<String,String>();
						//read the request body
						BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
						String line = "";
						String request = "";
						while((line = in.readLine()) != null) {
							request = request + line;
						}
						System.out.println("------- "+request);
						//individual key=value pairs are delimited by ampersands. Tokenize.
						String[] pairs = request.split("&");					
						for(int i=0;i<pairs.length;i++) {
							//each key=value pair is separated by an equals, and both halves require URL decoding.
							String pair = pairs[i];
							post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
						}
						
						
						
						
						
						//int vehicle_id = Integer.valueOf(post.get("vehicle_id"));
						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));					
						try { 
							
						
							dao.deleteVehicle(Integer.parseInt(post.get("vehicle_id")));
							he.sendResponseHeaders(200, 0); //HTTP 200 (OK)
							out.write("Success! Vehicle Deleted");
							out.close();
						}
						catch(SQLException se) {
							 he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
							 out.write("Error deleting Vehicle");
							 out.close();
						}
					}
					
				});
			 
			// actually start the server
			server.start();

		}

		catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage() + "  " + ioe.getStackTrace());
		}


		
	}
	
 }
	    
	




