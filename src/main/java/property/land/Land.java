package property.land;
import java.util.Date;

import property.city.City;
import property.main.Property;
import property.main.PropertyException;
import property.type.PropertyType;
import user.Guest;

public class Land extends Property{
	private static final int PROPERTY_TYPE_ID = 9;
	private String landType;
	private int category;
	
	public Land(int listingID, int cityId, String address, int price, int area, Date creationDate,
			int userId, String landType, int category, String description) throws PropertyException {
		super(listingID, PROPERTY_TYPE_ID, cityId, address, price, area, creationDate, userId, description);
		this.landType = landType;
		this.category = category;
	}

}
