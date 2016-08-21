package property;
import agency.Agency;
import user.Guest;

public class Land extends Property{
	private LandType landType;
	private int category;
	
	public Land(Agency agency, ListingType listingType, Region region, String address, double price, double area,
			Guest user, LandType landType, int category) throws WrongPropertyException {
		super(agency, listingType, PropertyType.LAND, region, address, price, area, user);
		this.landType = landType;
		this.category = category;
	}

}
