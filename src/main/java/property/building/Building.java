package property.building;

import java.util.Date;

import property.city.City;
import property.main.Property;
import property.main.PropertyException;
import property.type.PropertyType;
import user.Guest;

public class Building extends Property {
	private int floor;
	private String contructionType;
	private boolean hasTEC;
	private boolean isFurnished;

	public Building(int listingID, int cityId, String address, int price, int area, Date creationDate, int userId,
			int propertyTypeId, int floor, String contructionType, boolean hasTEC, boolean isFurnished,
			String description) throws PropertyException {
		super(listingID, propertyTypeId, cityId, address, price, area, creationDate, userId, description);
		this.floor = floor;
		this.contructionType = contructionType;
		this.hasTEC = hasTEC;
		this.isFurnished = isFurnished;
	}

	public int getFloor() {
		return floor;
	}

	public String getContructionType() {
		return contructionType;
	}

	public boolean isHasTEC() {
		return hasTEC;
	}

	public boolean isFurnished() {
		return isFurnished;
	}

	@Override
	public String toString() {
		return super.toString() + ", floor=" + floor + ", contructionType=" + contructionType + ", hasTEC=" + hasTEC
				+ ", isFurnished=" + isFurnished + "]\n";
	}

}
