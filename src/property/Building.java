package property;

import agency.Agency;
import user.Guest;

public class Building extends Property {
	private int floor;
	private String contructionType;
	private boolean hasTEC;
	private boolean isFurnished;

	public Building(String city, String address, double price, double area, Guest user,
			String propertyType, int floor, String contructionType, boolean hasTEC, boolean isFurnished)
			throws WrongPropertyException {
		super(propertyType, city, address, price, area, user);
		this.floor = floor;
		this.contructionType = contructionType;
		this.hasTEC = hasTEC;
		this.isFurnished = isFurnished;
	}

	public Building(String city, String address, double price, double area, Guest user,
			String propertyType, int floor, String contructionType, boolean hasTEC, boolean isFurnished,
			String description) throws WrongPropertyException {
		super(propertyType, city, address, price, area, user, description);
		this.floor = floor;
		this.contructionType = contructionType;
		this.hasTEC = hasTEC;
		this.isFurnished = isFurnished;
	}

	@Override
	public String toString() {
		return super.toString() + ", floor=" + floor + ", contructionType=" + contructionType + ", hasTEC=" + hasTEC
				+ ", isFurnished=" + isFurnished + "]\n";
	}

}
