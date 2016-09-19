package property.parcel;

import java.util.Date;

import property.main.Property;
import property.main.PropertyException;

public class Parcel extends Property {
	private static final int PROPERTY_TYPE_ID = 10;
	private boolean isRegulated;
	private boolean hasElectricity;
	private boolean hasWater;

	public Parcel(int listingID, int city, String address, int price, int area, Date creationDate, int user,
			boolean isRegulated, boolean hasElectricity, boolean hasWater, String description)
			throws PropertyException {
		super(listingID, PROPERTY_TYPE_ID, city, address, price, area, creationDate, user, description);

		this.isRegulated = isRegulated;
		this.hasElectricity = hasElectricity;
		this.hasWater = hasWater;
	}

	public boolean isRegulated() {
		return isRegulated;
	}

	public boolean hasElectricity() {
		return hasElectricity;
	}

	public boolean hasWater() {
		return hasWater;
	}

	@Override
	public String toString() {
		return super.toString() + "Parcel [isRegulated=" + isRegulated + ", hasElectricity=" + hasElectricity
				+ ", hasWater=" + hasWater + "]";
	}

}
