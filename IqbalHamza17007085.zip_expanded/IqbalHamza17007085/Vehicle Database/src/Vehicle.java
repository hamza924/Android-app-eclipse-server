
/**
 *This is the vehicle class with its specific constructor which initialises
 *all the variables.
 *@author Hamza Iqbal 17007085
 *@version 1.0
 */


public class Vehicle {
	
	private int vehicle_id;
	private String make;
	private String model;
	private int year;
	private int price;
	private String license_number;
	private String colour;
	private int number_doors;
	private String transmission;
	private int mileage;
	private String fuel_type;
	private int engine_size;
	private String body_style;
	private String condition;
	private String notes;
	
	/**
	 * This constructs the the vehicle class with the specified parameters
	 * @param vehicle_id the vehicle id
	 * @param make the make of the vehicle
	 * @param model the model 
	 * @param year the year the vehicle was released
	 * @param price price of vehicle
	 * @param license_number license number
	 * @param colour the colour of vehicle 
	 * @param number_doors number of doors the vehicle has
	 * @param transmission type of transmission(automatic or manual)
	 * @param mileage mileage of the vehicle
	 * @param fuel_type the fuel type(petrol or diesel)
	 * @param engine_size the engine size of vehicle in cc
	 * @param body_style the type of style eg sports, hatchback
	 * @param condition new or used or old
	 * @param notes too fast too slow
	 */
	
	public Vehicle(int vehicle_id, String make, String model,
					int year, int price, String license_number,
					String colour, int number_doors, String transmission,
					int mileage , String fuel_type, int engine_size,
					String body_style, String condition, String notes) {
		
		this.vehicle_id = vehicle_id;
		this.make= make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.license_number = license_number;
		this.colour = colour;
		this.number_doors = number_doors;
		this.transmission = transmission;
		this.mileage = mileage;
		this.fuel_type = fuel_type;
		this.engine_size = engine_size;
		this.body_style = body_style;
		this.condition = condition;
		this.notes = notes;
		
		
	}

	/**
	 * 
	 * @return vehicle id
	 */
	public int getVehicle_id() {
		return vehicle_id;
	}
	


	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	
	/**
	 * 
	 * @return make
	 */

	public String getMake() {
		return make;
	}
	

	public void setMake(String make) {
		this.make = make;
	}

	
	/**
	 * 
	 * @return model
	 */
	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * 
	 * @return year
	 */

	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * 
	 * @return price
	 */

	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 
	 * @return license number
	 */
	
	
	public String getLicense_number() {
		return license_number;
	}


	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	/**
	 * 
	 * @return colour
	 */

	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * 
	 * @return number of doors
	 */
	public int getNumber_doors() {
		return number_doors;
	}


	public void setNumber_doors(int number_doors) {
		this.number_doors = number_doors;
	}

	/**
	 * 
	 * @return transmission
	 */
	public String getTransmission() {
		return transmission;
	}


	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	
	/**
	 * 
	 * @return mileage
	 */

	public int getMileage() {
		return mileage;
	}


	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * 
	 * @return fuel type
	 */
	public String getFuel_type() {
		return fuel_type;
	}


	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	/**
	 * 
	 * @return engine size
	 */
	public int getEngine_size() {
		return engine_size;
	}


	public void setEngine_size(int engine_size) {
		this.engine_size = engine_size;
	}

	/**
	 * 
	 * @return body style
	 */
	public String getBody_style() {
		return body_style;
	}


	public void setBody_style(String body_style) {
		this.body_style = body_style;
	}

	/**
	 * 
	 * @return condition
	 */
	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * @return to string method with all intialised instance variables
	 */
	public String toString() 
	{
		String s= "Vehicle " + this.vehicle_id+ " Make " + this.make
				+" Model "+this.model+" Year "+this.year+" Price "+this.price+
				" License "+this.license_number+" Colour "+this.colour+" Doors "+
				this.number_doors+" Transmission "+this.transmission+" Milage "+
				this.mileage+" Fuel Type "+this.fuel_type+" Engine Size "+this.engine_size+
				" Body Style "+this.body_style+" Condition "+this.condition+" Notes "+this.notes;
		return s;
		
		
	}
	
	
	

}
