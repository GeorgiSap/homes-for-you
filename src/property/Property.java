package property;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import agency.Agency;
import user.Guest;
import user.RegisteredUser;

public abstract class Property {
	private String propertyType;
	private String city;
	private String address;
	private double price;
	private double area;
	private Guest user;
	private LocalDateTime creationDate;
	private final int listingID; 
	private static Integer nextID = 0;
	private String description;

	public Property(Agency agency, String propertyType, String city, 
			String address, double price, double area, Guest user) throws WrongPropertyException {
		creationDate = LocalDateTime.now();
		this.propertyType = propertyType;
		this.city = city;
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
	
	public Property(Agency agency, String propertyType, String city, 
			String address, double price, double area, Guest user, String description) throws WrongPropertyException {
		this(agency, propertyType, city, address, price, area, user);
		if (description != null && description.length() > 1) {
			this.description = description;
		}
		
	}

	public String getCity() {
		return this.city;
	}

	public double getPrice() {
		return this.price;
	}

	public double getArea() {
		return this.area;
	}
	
	public String getPropertyType() {
		return this.propertyType;
	}

	public LocalDateTime getCreationDate() {
		return this.creationDate;
	}

	@Override
	public String toString() {
		return "Property [propertyType=" + propertyType + ", region=" + city + ", address=" + address + ", price="
				+ price + ", area=" + area + ", user=" + user + ", creationDate=" + creationDate + ", listingID="
				+ listingID + "]";
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
