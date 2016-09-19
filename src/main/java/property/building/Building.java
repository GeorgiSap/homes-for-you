package property.building;

import java.util.Date;

import property.main.Property;
import property.main.PropertyException;

public class Building extends Property {
	private int floor;
	private int contructionTypeId;
	private boolean hasTEC;
	private boolean isFurnished;

	public Building(int listingID, int cityId, String address, int price, int area, Date creationDate, int userId,
			int propertyTypeId, int floor, int contructionTypeId, boolean hasTEC, boolean isFurnished,
			String description) throws PropertyException {
		super(listingID, propertyTypeId, cityId, address, price, area, creationDate, userId, description);

		this.floor = floor;
		this.contructionTypeId = contructionTypeId;
		this.hasTEC = hasTEC;
		this.isFurnished = isFurnished;
	}

	public int getFloor() {
		return floor;
	}

	public int getContructionTypeId() {
		return contructionTypeId;
	}

	public boolean hasTEC() {
		return hasTEC;
	}

	public boolean isFurnished() {
		return isFurnished;
	}

	@Override
	public String toString() {
		return super.toString() + ", floor=" + floor + ", contructionTypeId=" + contructionTypeId + ", hasTEC=" + hasTEC
				+ ", isFurnished=" + isFurnished + "]\n";
	}

}
