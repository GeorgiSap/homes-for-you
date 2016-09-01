package property;
import agency.Agency;
import user.Guest;

public class Parcel extends Property{
	private static final String PROPERTY_TYPE = "PARCEL";
	private boolean isRegulated;
	private boolean hasElectricity;
	private boolean hasWater;
	
	public Parcel(Agency agency, String city, String address, double price, 
			double area, Guest user, boolean isRegulated, boolean hasElectricity, boolean hasWater) throws WrongPropertyException {
		super(agency, PROPERTY_TYPE, city, address, price, area, user);
		this.isRegulated = isRegulated;
		this.hasElectricity = hasElectricity;
		this.hasWater = hasWater;
	}
	
	public Parcel(Agency agency, String city, String address, double price, 
			double area, Guest user, boolean isRegulated, boolean hasElectricity, boolean hasWater, String description) throws WrongPropertyException {
		super(agency, PROPERTY_TYPE, city, address, price, area, user, description);
		this.isRegulated = isRegulated;
		this.hasElectricity = hasElectricity;
		this.hasWater = hasWater;
	}
	

	


	
	
	
	
}
