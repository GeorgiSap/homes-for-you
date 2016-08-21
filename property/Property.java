package property;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import agency.Agency;
import user.Guest;
import user.RegisteredUser;

public abstract class Property {
	private ListingType listingType;
	private PropertyType propertyType;
	private Region region;
	private String address;
	private double price;
	private double area;
	private Guest user;
	private LocalDateTime creationDate;
	private final int listingID; 
	private static Integer nextID = 0;

	public Property(Agency agency, ListingType listingType, PropertyType propertyType, Region region, 
			String address, double price, double area, Guest user) throws WrongPropertyException {
		creationDate = LocalDateTime.now();
		this.listingType = listingType;
		this.propertyType = propertyType;
		this.region = region;
		if (address != null) {
			this.address = address;
		} else {
			throw new WrongPropertyException("Wrong address");
		}
		if (price > 0) {
			this.price = price;
		} else {
			throw new WrongPropertyException("Wrong price");
		}
		if (area > 0) {
			this.area = area;
		} else {
			throw new WrongPropertyException("Wrong area");
		}
		synchronized (Property.nextID) {
			this.listingID = ++nextID;
		}
		if (user != null) {
			this.user = user;
		} else {
			throw new WrongPropertyException("Wrong user");
		}
		agency.addProperty(this);
		if (user instanceof RegisteredUser) {
			((RegisteredUser) user).addProperty(this);
		}
	}

	public Region getRegion() {
		return region;
	}

	public double getPrice() {
		return price;
	}

	public double getArea() {
		return area;
	}
	
	public PropertyType getPropertyType() {
		return propertyType;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "Property [listingType=" + listingType + ", propertyType=" + propertyType + ", region=" + region
				+ ", address=" + address + ", price=" + price + ", area=" + area + ", user=" + user + ", creationDate="
				+ creationDate + ", listingID=" + listingID + "]";
	}

	public int getListingID() {
		return listingID;
	}

	@Override
	public int hashCode() {
		return listingID;
	}

	@Override
	public boolean equals(Object obj) {
		return this.listingID == (Integer) obj;

	}
	
	


	
	
	
	

}
