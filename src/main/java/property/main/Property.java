package property.main;
import java.util.Date;

public abstract class Property {
	private int listingId;
	private int propertyTypeId;
	private int cityId;
	private String address;
	private int price;
	private int area;
	private int userId;
	private Date creationDate;
	private String description;

	public Property(int listingID, int propertyTypeId, int cityId, String address, int price, int area, Date creationDate, int userId, String description) throws PropertyException {
		if (listingID > 0) {
			this.listingId = listingID;
		}
		if (creationDate != null) {
		this.creationDate = creationDate;
		} else {
			throw new PropertyException("Wrong date");
		}
		if (cityId != 0) {
			this.cityId = cityId;
		} else {
			throw new PropertyException("Wrong city");
		}
		if (propertyTypeId != 0) {
			this.propertyTypeId = propertyTypeId;
		} else {
			throw new PropertyException("Wrong property type");
		}
	
		if (address != null) {
			this.address = address;
		} else {
			throw new PropertyException("Wrong address");
		}
		if (price > 0) {
			this.price = price;
		} else {
			throw new PropertyException("Wrong price");
		}
		if (area > 0) {
			this.area = area;
		} else {
			throw new PropertyException("Wrong area");
		}
		if (userId != 0) {
			this.userId = userId;
		} else {
			throw new PropertyException("Wrong user");
		}
		if (description != null && description.length() > 1) {
			this.description = description;
		}
	}
	
	

	public int getCityId() {
		return this.cityId;
	}

	public double getPrice() {
		return this.price;
	}

	public double getArea() {
		return this.area;
	}
	
	public int getPropertyTypeId() {
		return this.propertyTypeId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Property [propertyType=" + propertyTypeId + ", region=" + cityId + ", address=" + address + ", price="
				+ price + ", area=" + area + ", user=" + userId + ", creationDate=" + creationDate + ", listingID="
				+ listingId + "]";
	}

	public int getListingID() {
		return listingId;
	}

	@Override
	public int hashCode() {
		return listingId;
	}

	@Override
	public boolean equals(Object obj) {
		return this.listingId == ((Property) obj).getListingID();

	}

}
