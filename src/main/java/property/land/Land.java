package property.land;

import java.util.Date;

import property.main.Property;
import property.main.PropertyException;

public class Land extends Property {
	private static final int PROPERTY_TYPE_ID = 9;
	private int landTypeId;
	private int category;

	public Land(int listingID, int cityId, String address, int price, int area, Date creationDate, int userId,
			int landTypeId, int category, String description) throws PropertyException {
		super(listingID, PROPERTY_TYPE_ID, cityId, address, price, area, creationDate, userId, description);

		this.landTypeId = landTypeId;
		this.category = category;
	}

	public int getLandTypeId() {
		return landTypeId;
	}

	public int getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return super.toString() + "Land [landTypeId=" + landTypeId + ", category=" + category + "]";
	}

}
