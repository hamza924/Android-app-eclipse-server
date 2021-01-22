import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleDAO {
	
	
	/**
     * Makes database connectoin
     * @return connection object
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

/** 
	 * Gets all the vehicles from the vehicle table.
	 * 
	 * @return vehicle ArrayList called Vehicle
	 * @throws SQLException O
	 */
//private static final ArrayList<Vehicle> Vehicle = null;

public ArrayList<Vehicle> getAllVehicles() throws SQLException{
	Connection dbConnection = null;
	Statement statement = null;
	ResultSet resultset = null;
	String query = "SELECT * FROM vehicles;";
	ArrayList<Vehicle> Vehicles = new ArrayList<>();

	try {

		dbConnection = getDBConnection();
		statement = dbConnection.createStatement();
		System.out.println(query);
		// execute SQL query
		ResultSet resultset1=null;
		resultset1 = statement.executeQuery(query);
		while (resultset1.next()) {

		int id=resultset1.getInt("vehicle_id");
		String make=resultset1.getString("make");
		String model=resultset1.getString("model");
		int year=resultset1.getInt("year");
		int price=resultset1.getInt("price");
		String license_number=resultset1.getString("license_number");
		String colour=resultset1.getString("colour");	
		int number_door=resultset1.getInt("number_doors");
		String transmission= resultset1.getString("transmission");
		int mileage=resultset1.getInt("mileage");	
		String fuel_type =resultset1.getString("fuel_type");
		int engine_size= resultset1.getInt("engine_size");
		String body_style=resultset1.getString("body_style");
		String condition = resultset1.getString("condition");
		String notes=resultset1.getString("notes");	
		Vehicles.add(new Vehicle(id,make,model,year,price,license_number,colour,number_door,transmission,mileage,fuel_type,engine_size,body_style,condition,notes));

		}
		
	} catch (SQLException e) {
		System.out.println(  e);
	} 
	// close connection
	finally {
		if (resultset != null) {
			resultset.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
	return Vehicles;
}

public  boolean insertVehicle(Vehicle v) throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
	// ResultSet resultset = null;
	String query = "INSERT INTO vehicles (vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes) VALUES ("+v.getVehicle_id() + ",'"
	+v.getMake() + "','"+v.getModel() + "',"+v.getYear() + ","+v.getPrice() + ",'" +v.getLicense_number() + "','" +v.getColour() + "',"+v.getNumber_doors() + ",'"+v.getTransmission() + "',"+v.getMileage() + ",'"+v.getFuel_type() + "',"
			+v.getEngine_size() + ",'"+v.getBody_style() + "','"+v.getCondition() + "','"+v.getNotes() + "')" ;
	
																																											//"	make='"+vehicle.getMake()+"',"+
	boolean B = false;

	try {		
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			statement.executeUpdate(query);
			B=true;

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} 
	
	finally {

		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
		}
		
	}
	return B;
	
}


public Vehicle getVehicle(int vehicleID)
{
	Vehicle newV=null;
	Connection dbConnection = null;
	Statement statement = null;
	ResultSet resultset = null;
	
	String query= "SELECT * FROM vehicles WHERE vehicle_id = " +vehicleID + ";";


	try {
		dbConnection = getDBConnection();
		statement = dbConnection.createStatement();
		System.out.println("DBQuery:"  +query);
		// execute SQL query
		resultset = statement.executeQuery(query);
		while (resultset.next()) {


			int vehicle_id = resultset.getInt("vehicle_id");
			String make = resultset.getString("make");
			String model = resultset.getString("model");
			int year = resultset.getInt("year");
			int price = resultset.getInt("price");
			String license_number = resultset.getString("license_number");
			String colour = resultset.getString("colour");
			int number_doors = resultset.getInt("number_doors");
			String transmission = resultset.getString("transmission");
			int mileage = resultset.getInt("mileage");
			String fuel_type = resultset.getString("fuel_type");
			int engine_size = resultset.getInt("engine_size");
			String body_style = resultset.getString("body_style");
			String condition = resultset.getString("condition");
			String notes = resultset.getString("notes");
			newV = new Vehicle(vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes);

		}

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	return newV;
	
	
}

public boolean deleteVehicle(int vehicle_id) throws SQLException {

	String query = "DELETE FROM vehicles WHERE vehicle_id = " + vehicle_id + ";" ;

	Connection dbConnection = null;
	Statement statement = null;
	// ResultSet resultset = null;

	try {
		dbConnection = getDBConnection();
		statement = dbConnection.createStatement();
		System.out.println(query);
		// execute SQL query
		statement.executeUpdate(query);
		return true;

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} finally {

		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
	return false;
}

public Boolean updateVehicle(Vehicle vehicle) throws SQLException {

	String query = "UPDATE vehicles"
			+ " SET vehicle_id = " +vehicle.getVehicle_id()+","+
			  
			"	make='"+vehicle.getMake()+"',"+
			"	model='"+vehicle.getModel()+"',"+
			"	year= " +vehicle.getYear()+","+
			"	price= "+ vehicle.getPrice()+","+
			"	license_number='"+ vehicle.getLicense_number()+"',"+
			"	colour= '"+ vehicle.getColour()+"',"+
			"	number_doors= "+ vehicle.getNumber_doors()+","+
			"	transmission = '"+vehicle.getTransmission()+"',"+ 
			"	mileage= "+ vehicle.getMileage()+","+
			"	fuel_type = '"+ vehicle.getFuel_type()+"',"+
			"	engine_size = "+ vehicle.getEngine_size()+","+
			"	body_style = '"+ vehicle.getBody_style()+"',"+
			"	condition = '"+ vehicle.getCondition()+"',"+
			"	 notes = ' "+vehicle.getNotes()+"' "+
	    	 " WHERE vehicle_id= "+vehicle.getVehicle_id()+";";
	

	Connection dbConnection = null;
	Statement statement = null;
	

	try {
		dbConnection = getDBConnection();
		statement = dbConnection.createStatement();
		System.out.println(query);
		// execute SQL query
		statement.executeUpdate(query);

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} finally {

		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
	return true;
}

}



