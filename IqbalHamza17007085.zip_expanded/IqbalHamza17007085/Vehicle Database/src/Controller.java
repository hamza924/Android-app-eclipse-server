import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
	console menu
 *  @author  Hamza Iqbal 17007085
 *  
 */
public class Controller {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		int option;
		
		
		System.out.println("-----------------");
		System.out.println("Vehicle Inventory System");
		System.out.println("Choose from these options");
		System.out.println("-----------------");
		System.out.println("1 - Retrieve all vehicles");
		System.out.println("2 - Search for Vehicle ");
		System.out.println("3 - Insert new vehicle into database");
		System.out.println("4 - Update Existing Vehicle detail ");
		System.out.println("5 - Delete Vehicle from databse");
		System.out.println("6- Exit ");
		System.out.println("Enter choice >");

			Scanner in= new Scanner(System.in);
	
			option=in.nextInt();
			
			
	VehicleDAO dao = new VehicleDAO();
	ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();


// Getting all vehicles
	if(option==1) {
		System.out.println("Showing all vehicles");	
		if(vehiclesList != null)
			  vehiclesList = dao.getAllVehicles();	
		
		for (Vehicle v : vehiclesList){		     
		    System.out.println(v);
		}
	}
	// Getting a vehicle using id
	else if (option==2)
		{
			Scanner get = new Scanner(System.in);
			System.out.println("Enter vehicle id ");
			int vehicleID =get.nextInt();
		
			Vehicle getVehicle=dao.getVehicle(vehicleID);
			System.out.println(getVehicle.getMake());
			System.out.println(getVehicle.getModel());
			System.out.println(getVehicle.getYear());
			System.out.println(getVehicle.getPrice());
			System.out.println(getVehicle.getLicense_number());
			System.out.println(getVehicle.getColour());
			System.out.println(getVehicle.getNumber_doors());
			System.out.println(getVehicle.getTransmission());
			System.out.println(getVehicle.getMileage());
			System.out.println(getVehicle.getFuel_type());
			System.out.println(getVehicle.getEngine_size());
			System.out.println(getVehicle.getBody_style());
			System.out.println(getVehicle.getCondition());
			System.out.println(getVehicle.getNotes());


			dao.getVehicle(vehicleID);
			
		}
			// option 3 insert new car
		else if(option==3)
		{
			
			System.out.println(" Enter details ");
			
			
			Scanner input = new Scanner (System.in);
			System.out.println("Enter vehicle id : ");
			int vehicle_id= input.nextInt();
			System.out.println("Enter vehicle make : ");
			String make= input.next();
			System.out.println("Enter vehicle model : ");
			String model= input.next();
			System.out.println("Enter vehicle year : ");
			int year= input.nextInt();
			System.out.println("Enter vehicle price : ");
			int price= input.nextInt();
			System.out.println("Enter vehicle license number : ");
			String license_number= input.next();
			System.out.println("Enter vehicle colour : ");
			String colour= input.next();
			System.out.println("Enter number of doors : ");
			int number_of_doors= input.nextInt();
			System.out.println("Enter transmission type (manual/automatic) : ");
			String transmission= input.next();
			System.out.println("Enter vehicle mileage : ");
			int mileage= input.nextInt();
			System.out.println("Enter Fuel Type");
			String fuel_type= input.next();
			System.out.println("Enter Engine Size (cc) : ");
			int engine_size= input.nextInt();
			System.out.println("Enter body style : ");
			String body_style= input.next();
			System.out.println("Enter condition: ");
			String condition= input.next();
			System.out.println("Enter notes : ");
			String notes= input.next();
			
		
			Vehicle insert=new Vehicle(vehicle_id,make,model,year,price,license_number,colour,number_of_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes);
			dao.insertVehicle(insert);
		}
	
	//Updating an Existing Vehicle
	
		else if(option==4)
		{
			System.out.println("Enter Vehicle ID ");
			System.out.println("Enter Vehicle ID ");

			Scanner op4 = new Scanner (System.in);
			int updateID=op4.nextInt();
			
			System.out.println("Enter vehicle make : ");
			String make= op4.next();
			System.out.println("Enter vehicle model : ");
			String model= op4.next();
			System.out.println("Enter vehicle year : ");
			int year= op4.nextInt();
			System.out.println("Enter vehicle price : ");
			int price= op4.nextInt();
			System.out.println("Enter vehicle license number : ");
			String license_number= op4.next();
			System.out.println("Enter vehicle colour : ");
			String colour= op4.next();
			System.out.println("Enter number of doors : ");
			int number_of_doors= op4.nextInt();
			System.out.println("Enter transmission type: ");
			String transmission= op4.next();
			System.out.println("Enter vehicle mileage : ");
			int mileage= op4.nextInt();
			System.out.println("Enter Fuel Type: ");
			String fuel_type= op4.next();
			System.out.println("Enter Engine Size: ");
			int engine_size= op4.nextInt();
			System.out.println("Enter body style: ");
			String body_style= op4.next();
			System.out.println("Enter condition (like new,fair.good) : ");
			String condition= op4.next();
			System.out.println("Enter notes : ");
			String notes= op4.next();
			
			
		
			
			Vehicle v=new Vehicle( updateID, make,model,year,price,license_number,colour,number_of_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes);

			dao.updateVehicle(v);
			
			
		}
			//option 5 delete a vehicle
	
		else if (option==5)
		{
			System.out.println("Enter ID to delete ");
			Scanner deleteV= new Scanner (System.in);
			int Vid=deleteV.nextInt();
			boolean done = dao.deleteVehicle(Vid);
			
			if(done) {
				System.out.println("deleted successfully");
			}
			
			else
			{
				System.out.println("unsucessful");
			}
			
			
		}
		// exit the menu
	
		else if (option==6)
		{
			System.out.println("Program is being closed.... ");
			System.out.println("Program Closed. ");
		}
	
	}
	}
