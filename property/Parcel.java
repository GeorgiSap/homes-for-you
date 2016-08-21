package property;
import agency.Agency;
import user.Guest;

public class Parcel extends Property{
	private boolean isRegulated;
	private boolean hasElectricity;
	private boolean hasWater;
	
	public Parcel(Agency agency, ListingType listingType, Region region, String address, double price, 
			double area, Guest user, boolean isRegulated, boolean hasElectricity, boolean hasWater) throws WrongPropertyException {
		super(agency, listingType, PropertyType.PARCEL, region, address, price, area, user);
		this.isRegulated = isRegulated;
		this.hasElectricity = hasElectricity;
		this.hasWater = hasWater;
	}
	

	


	
	
	
	
}
