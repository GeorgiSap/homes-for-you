package property;
import agency.Agency;
import user.Guest;

public class Building extends Property{
	private int floor;
	private ContructionType contructionType;
	private boolean hasTEC;
	private boolean isFurnished;
	


	public Building(Agency agency, ListingType listingType, Region region, String address, double price, double area,
			Guest user, PropertyType propertyType, int floor, ContructionType contructionType, boolean hasTEC,
			boolean isFurnished) throws WrongPropertyException {
		super(agency, listingType, propertyType, region, address, price, area, user);
		this.floor = floor;
		this.contructionType = contructionType;
		this.hasTEC = hasTEC;
		this.isFurnished = isFurnished;
	}

	@Override
	public String toString() {
		return super.toString() + ", floor=" + floor + ", contructionType=" + contructionType
				+ ", hasTEC=" + hasTEC + ", isFurnished=" + isFurnished + "]\n";
	}




	
	

	
	
}
