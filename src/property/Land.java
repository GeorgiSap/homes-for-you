package property;
import agency.Agency;
import user.Guest;

public class Land extends Property{
	private static final String PROPERTY_TYPE = "LAND";
	private String landType;
	private int category;
	
	
	public Land(Agency agency, String city, String address, double price, double area,
			Guest user, String landType, int category) throws WrongPropertyException {
		super(agency, PROPERTY_TYPE, city, address, price, area, user);
		this.landType = landType;
		this.category = category;
	}
	
	public Land(Agency agency, String city, String address, double price, double area,
			Guest user, String landType, int category, String description) throws WrongPropertyException {
		super(agency, PROPERTY_TYPE, city, address, price, area, user, description);
		this.landType = landType;
		this.category = category;
	}

}
